from service import UserService
from fastapi import APIRouter, Body, Response, Request, params
from pydantic import BaseModel, Field
from mongoengine import ObjectIdField
import json
from fastapi.encoders import jsonable_encoder
from .response_model import ResponseModel
from .user_schema import UserSchema


class Item(BaseModel):
    name: str


class ResponseItem(BaseModel):
    id: ObjectIdField = Field(default_factory=ObjectIdField, alias="_id")
    name: str

    class Config:
        allow_population_by_field_name = True
        arbitrary_types_allowed = True
        json_encoders = {ObjectIdField: str}


class UserRouter:

    def __init__(self, app):
        self.app = app
        self.service = UserService()
        self.router = APIRouter(prefix="/user", tags=["user"], )
        # self.router.add_api_route( "/search", self.search, methods=["GET", "POST"])
        app.include_router(self.router)

    def start(self):
        self.service.start()
        print("start user router")

        @self.app.post("/user",  response_model=None)
        async def createUser(user: UserSchema = Body(...)) -> ResponseItem:
            print(user)
            u_ = self.service.create_user_(name=user.name)
            return ResponseModel(u_, "User addedd successfully")
            # new_user = self.service.create_user(name=user.name)
            # doc = new_user.to_json(indent=2)
            # id = new_user.id
            # name = new_user.name
            # print(new_user.id, doc, id, name)
            # res: ResponseItem = {"id": id, "name": name}
            # page = {"id": BsonObjectId(), "name": str}
            # page["id"] = new_user.id
            # page["name"] = new_user.name
            # print(page)
            #
            # u = jsonable_encoder(user)
            # print("u: ", u["name"])

            # page_: ResponseItem = {"id": new_user.id, "name": new_user.name}
            # print(page_)
            # res.id = "sd"
            # res.name = "asd"
            # response = json.loads(json_util.dumps(page))
            # json_compatible_item_data = jsonable_encoder(doc)
            # print("json-comp: ", json_compatible_item_data)

            # return response

        @self.app.get("/user/{_id}")
        async def getUser(_id):
            user = self.service.find_user(id=_id)
            # user.to_json(indent=2)
            return ResponseModel(user, "User found successfully")
