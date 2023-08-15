package services

import DTOs.ShareDTO
import DTOs.UserDTO
import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import config.MongoConfig
import extensions.listStringToListObjectIds
import extensions.toDto
import kotlinx.coroutines.runBlocking
import models.User
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

class UserService() {
     val col = MongoConfig().createCollection<User>("user")

    fun createUser(user: User): Id<User>? {
        println("user create:  $user")
        col.insertOne(user)
        println(" user created id: $user.id")
        return user.id
    }

    fun getUser(id: String): User? {
        //user?.json.also { data = it}
        val bsonId: Id<User> = ObjectId(id).toId()
        val user = col.findOne(User::id eq  bsonId)
        println("user by id **: ${user}")
        // update
        /*
        val blogId: Id<User> = ObjectId("64d34b2a983e135f2684c871").toId()

        val Ids: ArrayList<String> = ArrayList<String>()
        Ids.add("64d34b2a983e135f2684c871")
        Ids.add("64d34b42983e135f2684c872")
        Ids.add("64d34b46983e135f2684c873")

        val list: ArrayList<Id<User>> = ArrayList<Id<User>>()

        for(item in Ids) {
            list.add(ObjectId(item).toId())
        }
        println("static ids: $list")
        println("list ids: $list")


        //
        val result = col.findOne(User::id eq  bsonId)
            ?.let { user ->
                println("user: $user")
                val updateResult = col.replaceOne(user.copy(name = "zazol",
                    shares = blogId, shareIds = list ))
                updateResult.modifiedCount == 1L
            } ?: false
       */


        //
        return user
    }

    fun findAll(): List<User> {
        val users = col.find()
        print("users : $users")
        var list: ArrayList<User> = ArrayList<User>()

        val all = col.find()

        for ( i in all){
            list.add(i)
            println("user i: $i")
        }
        return list

    }




    fun shareByUserIDBlogId(userID: String, user: ShareDTO){
        val bsonUserId: Id<User> = ObjectId(userID).toId()
        //val bsonBlogId: Id<User> = ObjectId(blogId).toId()
       // col.findOneAndUpdate(User::id eq bsonUserId,User::name eq "beso")
        //col.updateOne(User::id eq bsonUserId, set())
        //col.updateOne(User::id eq bsonUserId, pull())
        //col.updateOne(User::id eq bsonUserId, pullAll())
        //col.updateOne(User::id eq bsonUserId, push())
        //col.updateOne(User::id eq bsonUserId, pushEach())

        if(user.remove_blogs?.isNotEmpty() == true){
            col.updateOne(User::id eq bsonUserId,
                pullAll(User::shareIds,
                    listStringToListObjectIds(user.remove_blogs as List<String>))
                )
        }

        if(user.add_blogs?.isNotEmpty() == true){
            col.updateOne(User::id eq bsonUserId,
                addEachToSet(User::shareIds ,
                    listStringToListObjectIds(user.add_blogs as List<String>)))
        }






        //Both are equivalent
        //col.updateOne(friend::name eq "Paul", setValue(friend::name, "John"))
        //col.updateOne(friend::name eq "Paul", Friend::name setTo "John")
        //Multi fields update
        //col.updateOne(friend::name eq "Paul", set( Friend::name setTo "John", Friend::age setTo 25))
        //other operations are supported
        //col.updateOne(Friend::name eq "John", pull(Friend::tags, "t2"))

        println("result: $user")


    }


}