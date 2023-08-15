from schema import Blog, User
import asyncio
from bson import ObjectId
from typing import Optional
from pydantic import BaseModel, Field
import json
from bson import json_util
from .helpers import user_helper, blog_helper_, blog_helper


class BlogService:

    def start(self):
        print("start blog service")

    def create_blog(self, title: str, user_id: str) -> blog_helper:
        blog = Blog(title=title, user_id=user_id).save()
        return blog_helper_(blog)

    def find_blog(self, id: str) -> blog_helper:
        blog = Blog.objects.get(id=id)
        return blog_helper_(blog)

    async def find_blogs(self):
        blogs = []
        docs = Blog.objects()
        for d in docs:
            print("d:", d.user_id)
            if (d.user_id):
                h_ = blog_helper_(d)
                blogs.append(h_)
            else:
                h_ = blog_helper(d)
                blogs.append(h_)

        # docs_ = docs.to_json(indent=2)
        # print("blogs:", docs_)

        return blogs

    async def find_blogs_by_userID(self, user_id):
        blogs = []
        blogsByUserID__ = Blog.objects(user_id=user_id)
        for b in blogsByUserID__:
            s_ = b.to_json(indent=2)
            id = str(b.user_id)
            h_ = blog_helper_(b)
            print("h_:", h_)
           # j = json.loads(json_util.dumps(id))
            # print("j:", ObjectId(id))
            print("s_:", s_, b.id, b.user_id, id)
            h = blog_helper(b)
            print("h:", h)
            blogs.append(h_)

        return blogs
        # blogs = Blog.objects.get(user_id=user_id)
