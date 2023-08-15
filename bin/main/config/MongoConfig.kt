package config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import models.Blog
import models.User
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

class MongoConfig {

    private lateinit var client: MongoClient
     lateinit var database: Any
    lateinit var userCollection: MongoCollection<User>
    lateinit var blogCollection: MongoCollection<Blog>

    data class Test<T>(var supplier: () -> Unit) {}
    init {
        client = KMongo.createClient()
        database = client.getDatabase(("ktor_blog_db"))
        userCollection = (database as MongoDatabase).getCollection<User>("user")
        //blogCollection = (database as MongoDatabase).getCollection<Blog>("blog")
    }

    inline fun <reified T : Any> createCollection(colName: String): MongoCollection<T> {
        //Test<T>({ MongoConfig<T>() })
        return (database as MongoDatabase).getCollection<T>(colName)
    }
}
