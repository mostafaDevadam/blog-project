package services

import DTOs.ShareDTO
import extensions.listStringToListObjectIds
import models.Blog
import models.User
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

class ShareService {
    private val userService = UserService()
     private val userCollection = userService.col
    private val blogService = BlogService()
    private val blogCollection = blogService.col

    fun shareByUserIDBlogId(userID: String, user: ShareDTO) {
        val bsonUserId: Id<User> = ObjectId(userID).toId()
        //val bsonBlogId: Id<User> = ObjectId(blogId).toId()
        // col.findOneAndUpdate(User::id eq bsonUserId,User::name eq "beso")
        //col.updateOne(User::id eq bsonUserId, set())
        //col.updateOne(User::id eq bsonUserId, pull())
        //col.updateOne(User::id eq bsonUserId, pullAll())
        //col.updateOne(User::id eq bsonUserId, push())
        //col.updateOne(User::id eq bsonUserId, pushEach())

        if (user.remove_blogs?.isNotEmpty() == true) {
            userCollection.updateOne(
                User::id eq bsonUserId,
                pullAll(
                    User::shareIds,
                    listStringToListObjectIds(user.remove_blogs as List<String>)
                )
            )
        }

        if (user.add_blogs?.isNotEmpty() == true) {
            userCollection.updateOne(
                User::id eq bsonUserId,
                addEachToSet(
                    User::shareIds,
                    listStringToListObjectIds(user.add_blogs as List<String>)
                )
            )
        }


    }

    fun getSharesByUserIDasBlogs(userID: String): ArrayList<Blog> {
        //userCollection.aggregate<User>(sort(ascending(User::name)))
        // get user
        var list : ArrayList<Blog> = ArrayList<Blog>()
         val user = userService.getUser(userID)
             . let {
                 // get shares
                 if(it?.shareIds?.isNullOrEmpty() == false){
                     val shareIds = it.shareIds
                     println("shareIds: ${shareIds}")
                     // list of blogs
                     for(item in it.shareIds as List<Id<User>>){
                         //blogCollection.aggregate<Blog>(project(Blog::id eq item))
                         print("id item: $item")
                         //val blog = blogCollection.find(Blog::id eq item)
                         val blog = blogService.getBlogById(item.toString())
                         list.add(blog as Blog)
                     }

                 }

             }
        println("user shares blogs list: $list")
        return list
        // get shares
        // convert shares to list_of_objectIds
        // list of blogs
        // get blog by each objectId and push it in list
        // return the list
    }

    fun getAllSharesAsBlogs(): List<Blog> {
        // get users
        var list : ArrayList<Blog> = ArrayList<Blog>()
        val users = userService.findAll()
            .let {
                for(u in it){
                    if(!u.shareIds.isNullOrEmpty()){
                        val shareIds = u.shareIds
                        for(s in shareIds as List<Id<User>>){
                            println("id s: $s")
                            val blog = blogService.getBlogById(s.toString())
                            list.add(blog as Blog)
                        }
                    }
                }
            }
        println("all shares each user blogs list: $list")
        return list
        // schleife auf users
            // schleife auf shares each user
                // convert shares to list_of_objectIds
                // list of blogs
                // get blog by each objectId and push it in list
        // return the list
    }
}