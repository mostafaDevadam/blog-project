package com.example.demo.repositories

import com.example.demo.DTOs.BlogDTO
import com.example.demo.models.Blog
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate



@Repository
interface BlogRepository: MongoRepository<Blog, ObjectId> {

    @Query(value = "{'userid': ?0}")
    fun findAllByUserId(@Param("userid") userid : ObjectId): List<Blog>


}

//fun findAllByUserId(userid: ObjectId): List<Blog>
