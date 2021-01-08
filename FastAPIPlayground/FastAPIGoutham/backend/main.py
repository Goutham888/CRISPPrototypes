from typing import List, Optional
from fastapi import Depends, FastAPI, HTTPException, status, Query, Form
from fastapi.middleware.cors import CORSMiddleware

from sqlalchemy.orm import Session

from . import crud, models, schemas
from .database import SessionLocal, engine

from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm
from jose import JWTError, jwt
from datetime import datetime, timedelta

models.Base.metadata.create_all(bind=engine)

#information on the secretkey, algorithm, and when the jwt should expire
SECRET_KEY = "09d25e094faa6ca2556c818166b7a9563b93f7099f6f0f4caa6cf63b88e8d3e7"
ALGORITHM = "HS256"
ACCESS_TOKEN_EXPIRE_MINUTES = 30

#define the app
app = FastAPI()
#set the route for where the uname and pass should come from
oauth2_scheme = OAuth2PasswordBearer(tokenUrl="token")

#enable origins from all of these routes
origins = [
    "http://localhost:8000",
    "http://localhost:3000",
    "http://localhost:****",
    "http://172.26.108.108/"
]

#enable cors so all origins are allowed
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


# Dependency: get database connection
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

#get user from the db, if they exist and password can be verified, return the user
def authenticate_user(db, username: str, password: str):
    user = crud.get_user_by_username(db, username)
    if not user:
        return False
    #its weird that verifying passwords is a crud command but ok
    if not crud.verify_password(password, user.hashed_password):
        return False
    return user


async def get_current_user(token: str = Depends(oauth2_scheme), db: Session = Depends(get_db)):
    #just defining a credentials exception
    credentials_exception = HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Could not validate credentials",
        headers={"WWW-Authenticate": "Bearer"},
    )#end of definition
    try:
        #decode the token
        payload = jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM])
        #pull out the username
        username: str = payload.get("sub")
        #if there is no user, raise exception
        if username is None:
            raise credentials_exception
        #if there is a user, create token_data
        token_data = schemas.TokenData(username=username)
    except JWTError:
        raise credentials_exception
    #get user by username using token data, why do we need token data, why can't we just use the username?
    user = crud.get_user_by_username(db, username=token_data.username)
    if user is None:
        raise credentials_exception
    return user


async def get_current_active_user(current_user: schemas.User = Depends(get_current_user)):
    # if current_user.disabled:
    #     raise HTTPException(status_code=400, detail="Inactive user")
    return current_user

#create access token with data and timedelta
def create_access_token(data: dict, expires_delta: Optional[timedelta] = None):
    #copy the information given
    to_encode = data.copy()
    #if a timedelta is given, use it to create an expiry field
    if expires_delta:
        expire = datetime.utcnow() + expires_delta
    #if it is not given, then use 15 min
    else:
        expire = datetime.utcnow() + timedelta(minutes=15)
    #add an expiry field to the data
    to_encode.update({"exp": expire})
    #create a jwt from the data
    encoded_jwt = jwt.encode(to_encode, SECRET_KEY, algorithm=ALGORITHM)
    return encoded_jwt

#provides a route for uname and password to come in
@app.post("/token", response_model=schemas.Token)
async def login_for_access_token(form_data: OAuth2PasswordRequestForm = Depends(), db: Session = Depends(get_db)):
    # async def login_for_access_token(form_data: schemas.UserCreate, db: Session = Depends(get_db)):
    #check if the user with this username and password exists in the db
    user = authenticate_user(db, form_data.username, form_data.password)
    #if they don't, raise an exception
    if not user:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Incorrect username or password",
            headers={"WWW-Authenticate": "Bearer"},
        )
    #create expiration limit
    access_token_expires = timedelta(minutes=ACCESS_TOKEN_EXPIRE_MINUTES)
    #create access token by sending in the username and the expiry time delta
    access_token = create_access_token(
        data={"sub": user.username}, expires_delta=access_token_expires
    )
    #return the token
    return {"access_token": access_token, "token_type": "bearer"}


# Create users according to the schemas in the schema file
@app.post("/users/", response_model=schemas.User)
def create_user(user: schemas.UserCreate, db: Session = Depends(get_db)):
    db_user = crud.get_user_by_username(db, username=user.username)
    if db_user:
        raise HTTPException(status_code=400, detail="username already registered")
    return crud.create_user(db=db, user=user)


@app.get("/users/me", response_model=schemas.User)
async def read_users_me(current_user: schemas.User = Depends(get_current_active_user)):
    return current_user


@app.get("/users/me/items/")
async def read_own_items(current_user: schemas.User = Depends(get_current_active_user)):
    return [{"item_id": "Foo", "owner": current_user.username}]



# Create an item (record of the commodity) for me (the current user)
@app.post("/users/me/items/", response_model=schemas.Item)
def create_item_for_me(
        item: schemas.ItemCreate, db: Session = Depends(get_db),
        current_user: schemas.User = Depends(get_current_active_user)
):
    return crud.create_user_item(db=db, item=item, user_id=current_user.id)


# read a user from the database using the user ID
@app.get("/users/{user_id}", response_model=schemas.User)
def read_user(user_id: int, db: Session = Depends(get_db)):
    db_user = crud.get_user(db, user_id=user_id)
    if db_user is None:
        raise HTTPException(status_code=404, detail="User not found")
    return db_user


# Create an item (record of the commodity) for the user
@app.post("/users/{user_id}/items/", response_model=schemas.Item)
def create_item_for_user(
        user_id: int, item: schemas.ItemCreate, db: Session = Depends(get_db)
):
    return crud.create_user_item(db=db, item=item, user_id=user_id)


# get users from the database
@app.get("/users/", response_model=List[schemas.User])
def read_users(skip: int = 0, limit: int = 100, db: Session = Depends(get_db)):
    users = crud.get_users(db, skip=skip, limit=limit)
    return users


# Read item from the database
@app.get("/items/{item_id}", response_model=schemas.Item)
def read_item(item_id: int, db: Session = Depends(get_db)):
    db_item = crud.get_item(db, item_id=item_id)
    if db_item is None:
        raise HTTPException(status_code=404, detail="Item not found")
    return db_item

# Read item from the database
@app.post("/searchItems/", response_model=List[schemas.Item])
async def search_items(zipcode: int = Form(...), item: str = Form(...), skip: int = 0, limit: int = 100, db: Session = Depends(get_db)):
    items = crud.search_items(item, db, skip=skip, limit=limit) 
    return items

# Read item from the database
@app.get("/items/", response_model=List[schemas.Item])
def read_items(skip: int = 0, limit: int = 100, db: Session = Depends(get_db)):
    items = crud.get_items(db, skip=skip, limit=limit)
    return items


# read a user from the database using the user ID
@app.get("/stores/{store_id}", response_model=schemas.Store)
def read_store_by_id(store_id: int, db: Session = Depends(get_db)):
    db_store = crud.get_store(db, store_id=store_id)
    if db_store is None:
        raise HTTPException(status_code=404, detail="Store not found")
    return db_store


# Create users according to the schemas in the schema file
@app.post("/stores/", response_model=schemas.Store)
def create_store(store: schemas.StoreCreate, db: Session = Depends(get_db)):
    db_store = crud.get_store_by_store_name(db, store_name=store.name)
    if db_store:
        raise HTTPException(status_code=400, detail="username already registered")
    return crud.create_store(db, store)


# Read item from the database
@app.get("/stores/", response_model=List[schemas.Store])
def read_stores(skip: int = 0, limit: int = 100,
                db: Session = Depends(get_db),
                q: Optional[str] = Query(None, max_length=50)):
    stores = crud.get_stores(db, skip=skip, limit=limit, q=q)
    return stores