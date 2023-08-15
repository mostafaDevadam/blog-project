import { SchemaFactory, Prop, } from "@nestjs/mongoose";
import mongoose from 'mongoose'



/*
export type UserDocument = User & Document
export class User {
    @Prop({ type: String, required: true })
    name: String;
}
export const UserSchema = SchemaFactory.createForClass(User)
*/

export const UserSchema = new mongoose.Schema({
    name: { type: String, required: true },
    shares: { type: [mongoose.Schema.Types.ObjectId], ref: 'Blog' }
})
export const UserModel = mongoose.model('User', UserSchema)
