package DTOs

import kotlinx.serialization.Serializable

@Serializable
data class BlogDTO (
    val id: String? = null,
    val title: String,
    val userid: String? = null,
)