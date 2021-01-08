# This program contains the model for database

from sqlalchemy import Column, Integer, String, ForeignKey, Boolean, Float
from sqlalchemy.types import DateTime
from sqlalchemy.orm import relationship
from .database import Base
from geoalchemy2 import Geometry

from sqlalchemy.dialects import postgresql
from sqlalchemy.sql import func


# The Base means that these classes extend database.py's Base object to create db models
class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True, index=True)
    username = Column(String, unique=True, index=True)
    hashed_password = Column(String)
    is_active = Column(Boolean, default=True)

    #this connects to the Items table and returns the items that this user created
    items = relationship("Item", back_populates="owner")


# Producer: User can identify a store (can be a static list),
# commodity (can be static list), quantity (could be an enumeration,
# like “None”, “A few”, “Many”) and a note.
class Item(Base):
    __tablename__ = "items"

    id = Column(Integer, primary_key=True, index=True)  # The id of the item
    owner_id = Column(Integer, ForeignKey("users.id"))  # The id of the person posted the item
    store_id = Column(Integer, ForeignKey("stores.id", onupdate='CASCADE', ondelete='CASCADE'))

    time = Column(DateTime)  # time

    commodity = Column(String, index=True)  # The name of the item (commodity)
    
    # store = Column(String, index=True)  # the store to purchase from
    # store_name = Column(String, ForeignKey("stores.name"))

    quantity = Column(Integer)  # “None”, “A few”, “Many”
    description = Column(String)  # The note attached to the post, Optional

    store_rel = relationship("Store", back_populates="item_rel")#connects to Store table to get what store the item belongs to 
    owner = relationship("User", back_populates="items")#connects to user to get what user put in this item


class Store(Base):
    __tablename__ = "stores"

    id = Column(Integer, primary_key=True, index=True)  # The id of the store

    name = Column(String, index=True)  # the name of the store
    description = Column(String)  # The note attached to the post, Optional

    long = Column(Float)
    lat = Column(Float)

    streetAddress = Column(String)
    zipcode = Column(Float)
    city = Column(String)
    state = Column(String)





    item_rel = relationship('Item', back_populates='store_rel')#connects to item to see what items a store has