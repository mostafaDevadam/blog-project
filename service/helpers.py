
def user_helper(user) -> dict:
    return {
        "id": str(user["id"]),
        "name": user["name"]
    }


def blog_helper(blog) -> dict:
    return {
        "id": str(blog["id"]),
        "title": blog["title"],
        "user_id": str(blog["user_id"]),
    }


def blog_helper_(blog):
    return {
        "id": str(blog["id"]),
        "title": blog["title"],
        "user_id": user_helper(blog["user_id"]),
    }


def share_helper(share):
    return {

    }
