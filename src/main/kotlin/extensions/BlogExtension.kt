package extensions

import DTOs.BlogDTO
import models.Blog

fun Blog.toDto(): BlogDTO =
    BlogDTO(
        id=this.id.toString(),
        title = this.title,
        userid=this.userid.toString()
    )

fun BlogDTO.toBlog() : Blog =
    Blog(
        title = this.title
    )