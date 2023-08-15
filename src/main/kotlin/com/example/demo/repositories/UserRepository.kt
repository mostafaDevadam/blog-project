package com.example.demo.repositories

import com.example.demo.models.User
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.Update
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.data.mongodb.repository.Update as UpdateRepository
import org.springframework.data.mongodb.core.MongoTemplate


@Repository
interface UserRepository: MongoRepository<User, ObjectId>  {

    //val mongoTemplate: MongoTemplate


    //@Query(value = "{ 'id': ?0 }")
    //@Update(value = "{ '\$set': { name: ?0 } }")
    //fun updateCode(@Param("id") id: ObjectId, @Param("name") name: String): User


    //@Query(value ="{'id' : ?0}")
    //@Update(value = "{ '$set' : {'user':'?1'}")
    //fun updateName(@Param("id") id: ObjectId,@Param("name") name: String)

    //@UpdateRepository
    //@Query(value = "{'id': ?0}")
    //fun updateShare(@Param("id") id: ObjectId): Unit
}