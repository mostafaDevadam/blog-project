import { UserDTO } from "./user.dto"

export class BlogDTO {
    title: String
    user: String | UserDTO
}
