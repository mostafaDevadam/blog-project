from service import ShareService
from .response_model import ResponseModel
from fastapi import APIRouter, Body, Response, Request, params
from .share_schema import ShareSchema


class ShareRouter:

    def __init__(self, app) -> None:
        self.app = app
        self.service = ShareService()

    def start(self):
        self.service.start()
        print("start Share router")

        @self.app.get("/share/all")
        async def getAllShares():
            shares = self.service.findAllShares()
            b = await shares
            print("all shares b:", b)
            return b  # "get all shares"

        @self.app.get("/share/all/user/{user_id}")
        async def getAllSharesByUserID(user_id):
            # get user
            # get shares[..blogs_ids..] from user
            # get blogs using ids in share[..blogs_ids..]
            shares = self.service.findAllSharesByUserID(user_id)
            b = await shares
            print("all user shares b:", b)
            return b  # "get all shares by user"

        """
        @self.app.get("/share/user/:user_id/blog/:blog_id")
        async def getShareByUserID_BlogId():
            # get user
            # get shares [...]
            # get blog_id from shares [...]
            # get blog using blog_id from shares
            return "get share"
        """
        @self.app.patch("/share/user/{user_id}")
        async def updateShareByUserId_BlogId(user_id, share: ShareSchema = Body(...)):
            # add or remove blogs in shares in user collection
            updateShares = self.service.updateInShares(
                user_id=user_id, remove_blogs=share.remove_blogs, add_blogs=share.add_blogs)
            b = await updateShares
            print("all user shares b:", b)

            return b
