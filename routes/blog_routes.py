
from service import BlogService
from .response_model import ResponseModel
from pydantic import BaseModel, Field
from fastapi import APIRouter, Body, Response, Request, params

from .blog_schema import BlogSchema
# schema for request body
import asyncio

# --


class BlogRouter:

    def __init__(self, app) -> None:
        self.app = app
        self.service = BlogService()

    def start(self):
        print("start Blog router")

        @self.app.post("/blog/user/{user_id}")
        async def createBlog(user_id, blog_: BlogSchema = Body(...)):
            # create a new blog using user_id
            blog = self.service.create_blog(title=blog_.title, user_id=user_id)
            return ResponseModel(blog, "Blog created")  # "create a new blog"

        @self.app.get("/blog/all")
        async def getBlogs():
            blogs = self.service.find_blogs()
            # asyncio.run(blogs)
            b = await blogs
            print("b:", b)

            return ResponseModel(b, "blogs list")  # "get blogs"

        @self.app.get("/blog/all/user/{user_id}")
        async def getBlogsByUserID(user_id):
            # get blogs where user_id
            blogs = self.service.find_blogs_by_userID(user_id=user_id)
            blogs_ = await blogs
            return ResponseModel(blogs_, "blogs list by user")

        @self.app.get("/blog/{_id}")
        async def getBlog(_id):
            # get blog using _id
            blog = self.service.find_blog(id=_id)
            return ResponseModel(blog, "blog by id")  # "get Blog"
