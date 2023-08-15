package com.example.demo.controllers

import com.example.demo.DTOs.BlogDTO
import com.example.demo.DTOs.ShareDTO
import com.example.demo.extensions.toDto
import com.example.demo.extensions.toUser
import com.example.demo.handling.HandleConvertingData
import com.example.demo.models.Blog
import com.example.demo.models.User
import com.example.demo.repositories.BlogRepository
import com.example.demo.repositories.UserRepository
import com.example.demo.services.ShareService
import com.mongodb.BasicDBObject
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.where
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.collections.ArrayList


@RestController
@RequestMapping("/share")
class ShareController(@Autowired val shareService: ShareService
    )
{




    @PatchMapping("/user/{userid}")
    fun UpdateAddInShare(@PathVariable userid: String, @RequestBody share: ShareDTO): String{
        return shareService.UpdateAddInShare(userid, share)

    }

    @GetMapping("/all/user/{userid}")
    fun findAllSharesByUserID(@PathVariable userid: String): ArrayList<BlogDTO> {
      return shareService.findAllSharesByUserID(userid)

    }


    @GetMapping("/all")
    fun findAllShares(): List<BlogDTO> {
     return shareService.findAllShares()

    }



}