package com.example.demo.controllers

import com.example.demo.DTOs.UserDTO
import com.example.demo.extensions.toDto
import com.example.demo.models.User
import com.example.demo.repositories.UserRepository
import com.example.demo.services.UserService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
                      @Autowired val userService: UserService,

    ) {


    @PostMapping
    fun create(@RequestBody user: User): String {
        return userService.create(user)
    }

    @GetMapping("/{id}")
	fun findOne(@PathVariable id: String): UserDTO? {
       return userService.findOne(id)?.toDto()
    }

    @GetMapping("/all")
    fun findAll(): List<UserDTO> {
        return userService.findAll()
    }

}