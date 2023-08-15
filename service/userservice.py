from schema import Blog, User
from .helpers import user_helper


class UserService:

    def start(self):
        print("start user service")

    def create_user(self, name: str):
        user = User(name=name).save()
        return user_helper(user)

    def create_user_(self, name: str) -> user_helper:
        user = User(name=name).save()
        return user_helper(user)

    def find_user(self, id: str) -> user_helper:
        user = User.objects.get(id=id)
        return user_helper(user)
