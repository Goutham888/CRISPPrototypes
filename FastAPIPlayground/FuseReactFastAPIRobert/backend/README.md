# CRISP Demo: Backend

## Intro

This is the backend component for the CRISP Demo app. It is built with `FastAPI`, a Python web framework and `PostgreSQL`, a relational database.

## Backend: set up

- First edit the `SQLALCHEMY_DATABASE_URL` variable in `db.py` - change to your username, port, password, and database name
- Then, in the root directory, `/nsf_crisp_2019_web_app`, run the following command: `uvicorn backend.main:app --reload`
- Then, navigate to http://127.0.0.1:8000/docs#. The API doc will be there!

### Sample Output
```shell
$ uvicorn backend.main:app --reload  # Run this command in the root directory, /nsf_crisp_2019_web_app.
INFO:     Uvicorn running on http://127.0.0.1:8000 (Press CTRL+C to quit)
INFO:     Started reloader process [6424] using statreload
```

## Requirements/Features

- User can log in with username and password
- Producer: User can identify a store (can be a static list), commodity (can be static list), quantity (could be an enumeration, like “None”, “A few”, “Many”) and a note.
- Consumer: User can choose a store and commodity and see reports
- Should be able to support mobile interface.
- Can write to minimal database (ie, sqlite3) or flat files for this iteration.


## Project Structure

```shell script
└── backend
    ├── __init__.py  # An empty file. No need to modify it
    ├── crud.py  # Perform the basic CRUD (create, read, update, and delete) functions. Also includes passwords utils 
    ├── db.py  # Connects to the SQL database backend
    ├── main.py  # The main app file --  all the route info and user auth functions
    ├── models.py  # The model file for the SQL database. This specifies the type of data to be entered into PostgreSQL.
    ├── schemas.py  # The schema file for the web API. This specifies thetype of data to be submitted to FastAPI.
    └── test_main.py  # The test file for the main.py
```
