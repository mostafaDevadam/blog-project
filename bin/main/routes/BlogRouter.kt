package routes

import DTOs.BlogDTO
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id
import extensions.toBlog
import extensions.toDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import models.Blog
import models.User
import org.bson.types.ObjectId
import org.litote.kmongo.id.toId
import responses.ErrorResponse
import services.BlogService

class BlogRouter() {
    private val blogService = BlogService()

    public fun Route.registerBlog(){
        post("/blog/{userid}"){
            val userid_ = call.parameters["userid"].toString()
            val req = call.receive<BlogDTO>()
            val blog = req.toBlog()

            blogService.createBlogByUserID(userid_, blog)
                ?.let {  blogId ->
                    call.response.headers.append("Blog-Id", blogId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }

        get("/blog/{id}"){
            val id = call.parameters["id"].toString()
            blogService.getBlogById(id)
                ?.let { blog -> call.respond(blog.toDto()) }
                ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
        }

        get("/blog/all"){
            call.respond(blogService.findAll().map(Blog::toDto))
        }


        get("/blog/all/user/{userid}"){
            val userid = call.parameters["userid"].toString()
            call.respond(blogService.getAllBlogsBYUserID(userid).map(Blog::toDto))
        }



    }
}