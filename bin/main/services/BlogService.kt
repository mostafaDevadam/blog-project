package services

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import models.Blog
import config.MongoConfig
import org.bson.BsonValue
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.id.toId
import org.litote.kmongo.util.idValue

class BlogService() {
     val col = MongoConfig().createCollection<Blog>("blog")

    fun createBlogByUserID(userID: String, blog: Blog): Id<Blog>? {
        blog.userid = ObjectId(userID).toId()
        col.insertOne(blog)
        return blog.id
    }

    fun getBlogById(id: String): Blog? {
        val bsonId: Id<Blog> = ObjectId(id).toId()
        val blog = col.findOne(Blog::id eq bsonId)
        println("blog: $blog")
        return blog
    }

    fun getAllBlogsBYUserID(userID: String): List<Blog> {
        val bsonId: Id<Blog> = ObjectId(userID).toId()
        val blogs = col.find(Blog::userid eq bsonId)
        var list: ArrayList<Blog> = ArrayList<Blog>()
        for(i in blogs){
            list.add(i)
        }
        return list
    }

    fun findAll(): List<Blog> {
        var list: ArrayList<Blog> = ArrayList<Blog>()
        val all = col.find()
        for(i in all){
            list.add(i)
        }
        return list
    }


}