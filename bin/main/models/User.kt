package models

import org.bson.codecs.pojo.annotations.BsonId

import org.litote.kmongo.Id

//@Serializable
data class User(
    @BsonId
    val id: Id<User>? = null,
    var name: String?,

    @BsonId
    var shares: Id<User>? = null, //List<String>, //List<Id<String>>? = null

    @BsonId
    var shareIds: List<Id<User>>? =null,

)