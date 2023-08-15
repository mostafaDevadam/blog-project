package com.example.demo.models

import lombok.Data
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user", value = "user")
data class User (
    @BsonId
    val id: ObjectId? = null,
    var name: String,
    @BsonId
    var shares: List<ObjectId>? = null, //List<String>, //List<Id<String>>? = null

    @BsonId
    var shareIds: List<ObjectId>? =null,
)