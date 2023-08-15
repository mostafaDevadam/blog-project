import { IUser } from "./user.interface";


export interface IBLOG {
    _id?: String,
    title?: String,
    user?: String | IUser
}
