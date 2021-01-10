# The CRUD utils for the web app
# It can create, read, update, and delete items and users
# Also some password utils

from sqlalchemy.orm import Session
from sqlalchemy.sql import text
from datetime import datetime
from sqlalchemy.dialects import postgresql
from typing import Optional
from . import models, schemas

from passlib.context import CryptContext

pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")


# Verify if the password is really hashed
def verify_password(plain_password, hashed_password):
    return pwd_context.verify(plain_password, hashed_password)


# Hash the password
def get_password_hash(password: str):
    return pwd_context.hash(password)


# Get the user with a user ID
def get_user(db: Session, user_id: int):
    return db.query(models.User).filter(models.User.id == user_id).first()


# Get the user with the username
def get_user_by_username(db: Session, username: str):
    return db.query(models.User).filter(models.User.username == username).first()


# Get all users
def get_users(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.User).offset(skip).limit(limit).all()


# Create a user and add the user to the database
def create_user(db: Session, user: schemas.UserCreate):
    # Need to hash the password using passlib in production
    # fake_hashed_password = user.password + "notreallyhashed"
    hashed_password = get_password_hash(user.password)
    db_user = models.User(username=user.username, hashed_password=hashed_password)

    # Add the user to the database
    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user


# Get the item from the database
def get_items(db: Session, skip: int = 0, limit: int = 100):
    # (this step is same for both SQLite and postgresql,
    # b/c sqlalchemy provides a uniform interface
    return db.query(models.Item).offset(skip).limit(limit).all()


# Get the user with a user ID
def get_item(db: Session, item_id: int):
    return db.query(models.Item).filter(models.Item.id == item_id).first()

# Get the item from the database, need to add a zipcode component to this
def search_items_by_zipcode(zipcode: int, item: str, db: Session,  skip: int = 0, limit: int = 100):
    query="""SELECT items.*, stores.name as storeName, stores."streetAddress", stores.zipcode, stores.city, stores.state 
                FROM items INNER JOIN stores 
                ON items.store_id = stores.id
                WHERE items.commodity=:item AND stores.zipcode=:zipcode"""
    return db.execute(query, {"item": item, "zipcode":zipcode}).fetchall()

# Create an item for a user
# NOT PASSING the test
def create_user_item(db: Session, item: schemas.ItemCreate, user_id: int):
    db_item = models.Item(**item.dict(), owner_id=user_id)
    db.add(db_item)
    db.commit()
    db.refresh(db_item)
    return db_item


def get_stores(db: Session, skip: int = 0, limit: int = 100, q: Optional[str] = None):
    if q:
        # Search for the store which has the searched item
        # - Find which store(s) has the item
        # - (In the future) rank the stores, the closest goes first
        # - return the store items
        # return db.query(models.Store).filter(models.Store.item_rel.commodity == q)
        return db.query(models.Store).join(models.Store.item_rel).filter(models.Item.commodity.contains(q)).all()
    # Return all stores here
    return db.query(models.Store).offset(skip).limit(limit).all()


# Get the user with a user ID
def get_store(db: Session, store_id: int):
    return db.query(models.Store).filter(models.Store.id == store_id).first()


def get_store_by_store_name(db: Session, store_name: str):
    return db.query(models.Store).filter(models.Store.name == store_name).first()


def create_store(db: Session, store: schemas.StoreCreate):
    db_store = models.Store(name=store.name, long=store.long, lat=store.lat)
    db.add(db_store)
    db.commit()
    db.refresh(db_store)
    return db_store