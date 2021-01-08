from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

#SQLALCHEMY_DATABASE_URL = "sqlite:///./sql_app.db"  ----for SQLite
SQLALCHEMY_DATABASE_URL = "postgresql://postgres:PostgreSQL_2230?@localhost:5433/CRISPFastAPIPrototype"

#create the engine with the specified url
#The connect_args={"check_same_thread": False} argument inside create_engine is only for sqlite
engine = create_engine(
    SQLALCHEMY_DATABASE_URL
)
#Each instance of this class will be a DB session
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

#you can inherit this base to create orm models like User and StoreItem 
Base = declarative_base()