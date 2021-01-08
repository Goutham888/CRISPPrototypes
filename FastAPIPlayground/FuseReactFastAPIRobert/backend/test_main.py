from fastapi import FastAPI
from fastapi.testclient import TestClient
from .main import app

client = TestClient(app)


@app.get("/")
async def read_main():
    return {"msg": "Hello World"}


def test_read_main():
    response = client.get("/")
    assert response.status_code == 200
    assert response.json() == {"msg": "Hello World"}


def test_create_user():
    response = client.post(
        "/users/",
        json={
            "username": "string8",
            "password": "string"
        }
    )
    assert response.status_code == 200
    assert response.json() == {
        "username": "string8",
        "id": 4,
        "is_active": True,
        "items": []
    }
