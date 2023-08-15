package com.example.demo.controllers

import com.example.demo.DTOs.BlogDTO
import com.example.demo.DTOs.UserDTO
import com.example.demo.extensions.toBlog
import com.example.demo.extensions.toDto
import com.example.demo.models.Blog
import com.example.demo.repositories.BlogRepository
import com.example.demo.repositories.UserRepository
import com.example.demo.services.BlogService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.where
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
class BlogController(@Autowired val blogService: BlogService,
    ) {

    @PostMapping("/user/{userid}")
    fun create(@PathVariable userid: String,
               @RequestBody blog: Blog): String
    {
       return blogService.create(userid, blog)
    }


    @GetMapping("/{id}")
    fun findOne(@PathVariable id: String): BlogDTO? {
        return blogService.findOne(id)?.toDto()
    }

    @GetMapping("/all")
    fun findAll(): List<BlogDTO> {
       return blogService.findAll()
    }

    @GetMapping("/all/user/{userid}")
    fun findAllByUserID(@PathVariable userid: String): List<BlogDTO> {
       return blogService.findAllByUserID(userid)
    }

}