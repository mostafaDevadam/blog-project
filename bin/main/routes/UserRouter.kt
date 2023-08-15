package routes

import DTOs.ShareDTO
import DTOs.UserDTO
import extensions.toDto
import extensions.toUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import models.User
import org.litote.kmongo.json
import responses.ErrorResponse
import services.UserService

class UserRouter(prefix: String = "/user") {
    private val userService = UserService()

    public fun Route.register(){
        get(  "/user/{id}") {
            val id = call.parameters["id"].toString()
            userService.getUser(id)
                ?.let { foundUser -> call.respond(foundUser.toDto()) }
                ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
        }


        get("/user/all") {
           val usersList = userService.findAll().map(User::toDto)
            println("userList: $usersList")
            call.respond(userService.findAll().map(User::toDto))
            //call.respondText("users")
        }


        post("/user") {
            val req = call.receive<UserDTO>()
            val user= req.toUser()

            println("req: $req")
            println("user: $user")

            userService.createUser(user)
                ?.let { userId ->
                    call.response.headers.append("My-User-Id-Header", userId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)

        }

        patch("/user/share/{user_id}"){
            val userID = call.parameters["user_id"].toString()
           // val blogId = call.parameters["blog_id"].toString()
            val req = call.receive<ShareDTO>()
            val user = req.toUser()
            println("req: $req")
            println("user: $user")
            userService.shareByUserIDBlogId(userID, req)
            call.respondText("share using blogId")
        }
        patch("/user/share/{user_id}"){
           call.respondText("share using blogIds")
        }
    }

    // 2 times
    // Erste mit blogid -> shares
        // take userid and blogid (params) -> shares
        // get user by id
        // push blogs in shares in user
        // pull blogs in shares in user

    // Zweite mit blogids -> shareIds
        // take userid(param) and blogids(in body) -> shareIds
        // get user by id
        // addEachToset blogs in shares in user
        // pullAll blogs in shares in user

    // get blogs using ids in shares in user
    // get all blogs using shares each user


}

