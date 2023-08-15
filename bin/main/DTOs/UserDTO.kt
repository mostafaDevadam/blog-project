package DTOs

import kotlinx.serialization.Serializable
import org.litote.kmongo.Id

@Serializable
data class UserDTO(
    val id: String? = null,
    val name: String? = null,
    val shares: String? = null, //List<String>, //ArrayList<Id<String>>? = null
    //val shareIds: List<Id<String>>? = null,

    )

@Serializable
data class ShareDTO(
    val shares: String? = null,
    val shareIds: List<String>? = null,
    val add_blogs: List<String>? = null,
    val remove_blogs: List<String>? = null,
)