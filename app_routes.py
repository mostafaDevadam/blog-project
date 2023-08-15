from routes import UserRouter, BlogRouter, ShareRouter
from fastapi import APIRouter


class AppRouter:

    def __init__(self, app, Router):
        self.userRouter = UserRouter(app)
        self.blogRouter = BlogRouter(app)
        self.shareRouter = ShareRouter(app)
        # self.router = APIRouter(prefix="/api")

    def start(self):
        print("AppRouter...")
        self.userRouter.start()
        self.blogRouter.start()
        self.shareRouter.start()

        return True
