# schemas.py controls the pydantic models within FastAPI
# All pydantic schemas are stored here
# Using pydantic, the program can "guess" the type of each field in the object

from datetime import datetime
from pydantic import BaseModel, validator
from typing import Optional, List

from geoalchemy2 import Geometry


# Base classes store common attributes while creating or reading data.
class ItemBase(BaseModel):
    time: datetime
    description: Optional[str] = None

    commodity: str
    quantity: int


class ItemCreate(ItemBase):
    store_id: int


class Item(ItemBase):
    id: int  # ID of the item (post)
    owner_id: int  # ID of the person who posted it

    # tell the program to read the data even if it is not a dict, but an ORM model
    class Config:
        orm_mode = True


# Base classes store common attributes while creating or reading data.
class UserBase(BaseModel):
    username: str  # the username

    class Config:
        orm_mode = True


# Inherit from UserBase, and add the password
# Previously UserCreate
class UserCreate(UserBase):
    password: str


# User can log in with username and password
# The schema for a user object contains a username and a password
class User(UserBase):
    id: int  # the unique ID of the user
    is_active: bool  # is the user active
    hashed_password: str

    items: List[Item] = []  # the list of items created by the user


class Token(BaseModel):
    access_token: str
    token_type: str


class TokenData(BaseModel):
    username: Optional[str] = None


# Base classes store common attributes while creating or reading data.
class StoreBase(BaseModel):
    name: str  # the store name

    long: float
    lat: float

    class Config:
        orm_mode = True


class StoreCreate(StoreBase):
    pass


class Store(StoreBase):
    id: int  # the unique ID of the store
    description: Optional[str] = None

    items: List[Item] = []  # the list of items created by the user
