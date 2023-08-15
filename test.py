from future import __annotations__
from mongoengine import (connect)
import sys
import os

from schema import Blog, User
from service import UserService, BlogService
from app_routes import AppRouter
from config import db_config
from routes import UserRouter, BlogRouter, ShareRouter

db_config()

# connect(db='py_blogdb', host='mongodb://localhost:27017/')
#
# router = AppRouter()
# r1 = router.start()
# print(r1)


##
# user = user_service.find_user('64cf7321b4975172ec0beaa8')
# user_ = user.to_json(indent=2)
# print(user_)

#
# blogs = Blog.objects()
# blogs_ = blogs.to_json(indent=2)
# print(blogs_)

# add user_id in some of blogs
blogs_list = ["64ce8cb9ac98325c32bb9845",
              "64ce8ce2303d77c2854fa8f9", "64ce8cf0f9aac9508b9ccc58"]

# user = User.objects.get(id="64ce8c37e3ad9900fdc35ddc")
# user_ = user.to_json(indent=2)
# print("user:", user_, user.id)
#
# User(name='John').save()
# User(name='Bob').save()

# pipeline


pipeline = [
    {"$sort": {"name": -1}},
    {"$project": {"_id": 0, "name": {"$toUpper": "$name"}}}
]

# data = User.objects().aggregate(pipeline)
# assert data == [{'name': 'BOB'}, {'name': 'JOHN'}]


"""
for b in blogs_list:
    b1 = Blog.objects(id=b).update_one(user_id=user.id)
    b2 = Blog.objects(id=b)
    b2_ = b2.to_json(indent=2)
    print(b2_)
"""

# user = User(name="saadi").save()
# blog1 = Blog(title="life_1", user_id=user).save()

# blog2 = Blog(title = "life_2")
# blog3 = Blog(title = "life_3")
# user = User.objects.get(id='64cf7321b4975172ec0beaa8')
# user_ = user.to_json(indent=2)
# print(user_)

# blog_update = Blog.objects(id='64cf7321b4975172ec0beaa9').update_one(push__blogs=user)

# blog_ = blog.to_json(indent=2)
# print(blog_)

# user = User.objects(id='64cf7321b4975172ec0beaa8').update_one(push__blogs=blog)
# user = User.objects(id='64cf7321b4975172ec0beaa8').update_one(pull__blogs=blog)

# user = User.objects(id='64cf7321b4975172ec0beaa8').update_one(push__shares=blog)
# user = User.objects(id='64cf7321b4975172ec0beaa8').update_one(pull__shares=blog)

# blogsByUserID = blog_service.find_blogs_by_userID('64ce8c25ab4c1167dd47aaca')
# blogsByUserID__ = Blog.objects(user_id='64ce8c25ab4c1167dd47aaca')
# blogsByUserID_ = blogsByUserID__.to_json()
# print("blogs by user: ", blogsByUserID_)

# for b in Blog.objects(user_id='64cf7321b4975172ec0beaa8'):
#    print("b:", b.to_json(indent=2))

add_blogs = ["64d10d6e633d6da0ffb94413", "64d10db7e15f28115330a430",
             "64d10dc5e15f28115330a431", "64d10d6e633d6da0ffb94413"]

remove_blogs = ["64d10dc5e15f28115330a431", "64d10d6e633d6da0ffb94413"]

# user = User.objects(id="64ce8c25ab4c1167dd47aaca").update_one( pull_all__shares=remove_blogs)


# user = User.objects(id="64ce8c25ab4c1167dd47aaca").update_one( add_to_set__shares=add_blogs)
# user_sh = user.to_json()
# print("user_sh:", user_sh)

"""
user__ = User.objects.get(id='64ce8c25ab4c1167dd47aaca')
user_ = user__.to_json(indent=2)
print(user_)
#
share_blogs = []
for u in user__.shares:
    u_ = u.to_json(indent=2)
    # share_blogs.append(u)
    print("u_:", u_)
"""

# nested schleife
"""
users = User.objects()

for u1 in users:
    if (u1.shares):
        for u2 in u1.shares:
            u2_ = u2.to_json(indent=2)
            print("u2_: ", u2_)
"""
