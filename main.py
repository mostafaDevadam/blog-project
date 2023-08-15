import uvicorn

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


app = FastAPI()
router = APIRouter(prefix="/test", tags=["test"])

db_config()

# r_ = APIRouter(prefix="/test", tags=["test"])
# @r_.get("/")
# async def test():
#    return "test..."


# -----------------------------
appRouter = AppRouter(app, APIRouter)
r1 = appRouter.start()
print(r1)

# ---------------


# ---------


# ------------


if __name__ == "__main__":
    uvicorn.run("main:app", host="127.0.0.1", port=5000, reload=True)
