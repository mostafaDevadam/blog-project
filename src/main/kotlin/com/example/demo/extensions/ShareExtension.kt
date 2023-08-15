package com.example.demo.extensions

import com.example.demo.DTOs.ShareDTO
import com.example.demo.models.User
import org.bson.types.ObjectId
import com.example.demo.handling.HandleConvertingData

//val handleConvertingData = HandleConvertingData()
fun User.toShareDto(): ShareDTO =
    ShareDTO(
        shares = this.shares.toString()
    )



fun ShareDTO.toUser(): User =
    User(
        name ="",
        //shares = ObjectId(this.shares),
        shareIds = HandleConvertingData().listStringToListObjectIds(this.add_blogs as List<String>)
    )

