from schema import User, blog
from .helpers import blog_helper_


class ShareService:

    def start(self):
        print("start share service")
        #

    # def addInShares():
       # pass
    # create by userid , blogid
    # pre: push blogid in shares in user
    # push blogids in shares in user

    async def findAllShares(self):
        # get all shares from each user
        share_blogs = []
        users = User.objects()
        for u1 in users:
            if (u1.shares):
                for u2 in u1.shares:
                    u2_ = u2.to_json(indent=2)
                    print("u2_: ", u2_)
                    h_ = blog_helper_(u2)
                    share_blogs.append(h_)
        return share_blogs

    async def findAllSharesByUserID(self, user_id):
        # get all by userid
        # get shares from user
        # get blogs by ids in shares
        share_blogs = []
        user = User.objects.get(id=user_id)
        if (user.shares):
            for u in user.shares:
                h_ = blog_helper_(u)
                share_blogs.append(h_)

        return share_blogs

    async def updateInShares(self, user_id, remove_blogs, add_blogs):
        # update by userid, blogid
        # remove blogIds
        # push blogIds
        user1 = User.objects(id=user_id).update_one(
            pull_all__shares=remove_blogs)
        # user1.reload()
        user2 = User.objects(id=user_id).update_one(
            add_to_set__shares=add_blogs)
        # user2.reload()
        return True
