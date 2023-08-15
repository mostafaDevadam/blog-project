import { Ishare } from "./share.interface";

export interface IUser {
    _id?: String,
    name?: String,
    shares?: Ishare,
}
