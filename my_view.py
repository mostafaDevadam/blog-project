
from fastapi import FastAPI, APIRouter
from fastapi_class import View, endpoint

app = FastAPI()


@View(router=app, path='/view')
class MyView:

    @endpoint()
    async def get(self):
        return "get item"

    @endpoint(methods=["GET",], path="/one/:_id")
    async def get(self):
        return "get item by id"

    @endpoint()
    async def post(self):
        return "post items"

    @endpoint()
    async def patch(self):
        return "patch items *"


# app.include_router(r1)
