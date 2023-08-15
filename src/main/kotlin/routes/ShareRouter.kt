package routes

import DTOs.ShareDTO
import com.sun.net.httpserver.HttpPrincipal
import extensions.toDto
import extensions.toUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.Blog
import responses.ErrorResponse
import services.ShareService

class ShareRouter {
    private val shareService = ShareService()

    public fun Route.registerShare(){
        patch("/share/user/{user_id}") {
            val userID = call.parameters["user_id"].toString()
            val req = call.receive<ShareDTO>()
            val user = req.toUser()
            println("req: $req")
            println("user: $user")
            shareService.shareByUserIDBlogId(userID, req)
            call.respondText("create share and update shares in user")

        }
        get("/share/all/user/{user_id}"){
            val userID = call.parameters["user_id"].toString()
            println("userID: $userID")
            call.respond(shareService.getSharesByUserIDasBlogs(userID).map(Blog::toDto))
                //?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
                // call.respondText("user shares as blog")
        }

        get("/share/all"){
            val userID = call.parameters["user_id"].toString()
            println("userID: $userID")
            call.respond(shareService.getAllSharesAsBlogs().map(Blog::toDto))
            //?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
            // call.respondText("user shares as blog")
        }
    }

}