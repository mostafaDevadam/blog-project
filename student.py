

from future import __annotations__
# import sys
# import os
from fastapi import FastAPI, APIRouter, Body

from app_routes import AppRouter
from config import db_config
from dotenv import load_dotenv

import motor.motor_asyncio
from pydantic import BaseModel, Field
from typing import Optional
from bson.objectid import ObjectId
from fastapi.encoders import jsonable_encoder
client = motor.motor_asyncio.AsyncIOMotorClient()
database = client.students

student_collection = database.get_collection("students_collection")

app = FastAPI()


def ResponseModel(data, message):
    return {
        "data": [data],
        "code": 200,
        "message": message
    }


class StudentSchema(BaseModel):
    name: str = Field(...)

    class Config:
        schema_extra = {
            "example": {
                "name": " mostafa adam"
            }
        }


def student_helper(student) -> dict:
    return {
        "id": str(student["_id"]),
        "name": student["name"]
    }


async def add_student(student_data: dict) -> dict:
    student = await student_collection.insert_one(student_data)
    new_st = await student_collection.find_one({"_id": student.inserted_id})
    return student_helper(new_st)


@app.post("/student")
async def createStudent(student: StudentSchema = Body(...)):
    st = jsonable_encoder(student)
    new_st = await add_student(st)
    return ResponseModel(new_st, "Student added successfully")
# ----------------
