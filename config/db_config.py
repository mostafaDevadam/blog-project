from mongoengine import connect
import motor.motor_asyncio


def db_config():
    connect(db='py_blogdb', host='mongodb://localhost:27017/')
    print("db is connected")


def db_motor_config():
    client = motor.motor_asyncio.AsyncIOMotorClient(
        "mongodb://localhost:27017/")
    database = client.students

    student_collection = database.get_collection("students_collection")
