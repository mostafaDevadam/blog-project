package com.example.demo.DTOs

import com.example.demo.extensions.toDto
import com.example.demo.models.User
import com.example.demo.services.UserService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired

class BlogDTO (
    val id: String? = null,
    var title: String? = null,
    var userid: String? = null,
    var user: UserDTO? = null,
)


