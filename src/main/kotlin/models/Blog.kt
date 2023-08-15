package models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

//@Serializable
data class Blog (
    @BsonId
    val id: Id<Blog>?= null,
    var title: String,

    @BsonId
    var userid: Id<Blog>?= null,
)