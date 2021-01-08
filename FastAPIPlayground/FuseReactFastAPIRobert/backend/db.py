import os

from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

# The URL to the database
# SQLALCHEMY_DATABASE_URL = "sqlite:////Users/robertbao/Documents/GitHub/fastAPI/database/demo.db"
SQLALCHEMY_DATABASE_URL = "postgresql://postgres:PostgreSQL_2230?@localhost:5433/CRISPSpringMVCPrototype"

# engine = create_engine(
#     SQLALCHEMY_DATABASE_URL, connect_args={"check_same_thread": False}
# )

# Create the engine for the database
engine = create_engine(SQLALCHEMY_DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()
