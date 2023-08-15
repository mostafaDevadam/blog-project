package com.example.demo.services

import com.example.demo.DTOs.UserDTO
import com.example.demo.extensions.toDto
import com.example.demo.models.User
import com.example.demo.repositories.UserRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Service
class UserService(@Autowired val userRepo: UserRepository) {



    fun findUserById(id: ObjectId): User {
        //val Id = ObjectId(id)
        val user = userRepo.findById(id).get()
        println("find user by id: $user")
        return user
    }

    fun create(user: User): String {
        println("new user: $user")
        val new_user = userRepo.insert(user)
        println("new_user: $new_user")
        return "created!"
    }


    fun findOne( id: String): User? {
        val Id = ObjectId(id)
        val user = userRepo.findById(Id).get() //.toDto()
        println("find user by id: $user")
        return user
    }

    fun findAll(): List<UserDTO> {
        val users = userRepo.findAll().map(User::toDto)
        return users
    }

}