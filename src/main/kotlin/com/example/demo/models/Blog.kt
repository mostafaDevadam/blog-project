package com.example.demo.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.ReadOnlyProperty
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.DocumentReference

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "blog", value="blog")
data class Blog (
    @BsonId
    val id: ObjectId? = null,
    var title: String,
    @BsonId
    val userid: ObjectId? = null,
    // @CreatedBy
    // val createdById: ObjectId,
    //@Field("user")
    //@DocumentReference(lookup = "{ '_id' : ?#{#self.createdById} }")
    //private val user: User? = null

){
    // @ReadOnlyProperty
    // @DocumentReference(lookup =  "{ '_id' : ?# {#self.createdById} }")
    // val createdBy: User? = null
}