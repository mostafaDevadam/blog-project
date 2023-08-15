package com.example.demo.extensions

import com.example.demo.DTOs.BlogDTO
import com.example.demo.DTOs.UserDTO
import com.example.demo.models.Blog
import com.example.demo.models.User
import com.example.demo.repositories.UserRepository
import com.example.demo.services.UserService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired

// @Autowired val userService: UserService

fun Blog.toDto(): BlogDTO =
    BlogDTO(
        id = this.id.toString(),
        title = this.title,
        userid = this.userid.toString(),
        user = null //this.userid?.let { UserService().findUserById(it).toDto() } // { toRefAsUser(it) }
        //createdById = this.createdById.toString() //UserService().findUserById(this.createdById) as //this.createdById.toString()
    )

fun BlogDTO.toBlog(): Blog =
    Blog(
        title = this.title.toString(),
        userid = ObjectId(this.userid),
        //createdById = ObjectId(this.createdById),
    )


fun toRefAsUser(id: ObjectId): UserDTO {
    //val userRepo: UserRepository

    //val user = userRepo.findById(id)
    var user: UserDTO? = null
    user?.name = "asd"
    user?.custId = id.toString()
    return user as UserDTO
}