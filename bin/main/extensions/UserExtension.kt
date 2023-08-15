package extensions

import DTOs.ShareDTO
import DTOs.UserDTO
import models.User
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.id.toId

fun User.toDto(): UserDTO =
    UserDTO(
        id = this.id.toString(),
        name = this.name,
        shares = this.shares.toString(),
        //shareIds = this.shareIds
    )

fun UserDTO.toUser() : User =
    User(
        name = this.name,
        shares = ObjectId(this.shares).toId() //this.shares.toString()
        //shares = this.shares,
    )
fun User.toShareDto(): ShareDTO =
    ShareDTO(
        shares = this.shares.toString()
    )

fun ShareDTO.toUser(): User =
    User(
        name = null,
        shares = ObjectId(this.shares).toId(),
        shareIds = listStringToListObjectIds(this.shareIds as List<String>)

    )




fun listStringToListObjectIds(list: List<String>): ArrayList<Id<User>> {
    val list_: ArrayList<Id<User>> = ArrayList<Id<User>>()

    for(item in list) {
        list_.add(ObjectId(item).toId())
    }
    return list_
}