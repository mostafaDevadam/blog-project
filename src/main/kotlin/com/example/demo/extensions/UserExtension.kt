package com.example.demo.extensions

import com.example.demo.DTOs.UserDTO
import com.example.demo.handling.HandleConvertingData
import com.example.demo.models.User
import org.bson.types.ObjectId

val handleConvertingData = HandleConvertingData()


fun User.toDto(): UserDTO =
    UserDTO(
        id = this.id.toString(),
        name = this.name,
        shares = this.shares.let { handleConvertingData.listObjectIdsToListStrings(it as List<ObjectId>) }

    )

fun UserDTO.toUser() : User =
    User(
        name = this.name.toString()
    )

