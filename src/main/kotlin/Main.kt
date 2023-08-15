import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import config.MongoConfig
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import routes.BlogRouter
import routes.ShareRouter
import routes.UserRouter

@Serializable
data class UserDto(
    var name: String? = null,
)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    install(ContentNegotiation){
        json()
    }
    install(RequestValidation)
    install(StatusPages)

    MongoConfig()


    //val client = KMongo.createClient(ConnectionString("mongodb://localhost:27017"))
    //val database = client.getDatabase(("ktor_blog_db"))
    //val userCollection = mongoConfig.userCollection // database.getCollection<User>("user")
    //val blogCollection = mongoConfig.userCollection //database.getCollection<Blog>("blog")

    //val t = userCollection::class.java.typeName


    install(Routing){
        route("/api") {
            UserRouter().run { register() }
            BlogRouter().run { registerBlog() }
            ShareRouter().run { registerShare() }
        }

    }


    routing {
        //val userRouter = UserRouter(userCollection)


        get("/") {
            call.respondText("Hello, world!")
        }
        get("/api") {
            call.respondText("Hello API!")
        }
        /*post("/user") {
            val data = call.receive<User>()
            val user = userCollection.insertOne(data)
            call.respond(user)
        }*/
    }
}
/*
fun Application.configureRouting(){
    routing {
        get("/test") {
            call.respond("je")
        }
    }
}
/*
fun Application.configureSerialization(){
    install(ContentNegotiation){
        json()
    }
}
*/