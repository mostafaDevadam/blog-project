import { SchemaFactory, Prop } from "@nestjs/mongoose";
import mongoose, { Schema, SchemaType } from "mongoose";

export type BlogDocument = Blog & Document

export class Blog {
    @Prop({ type: String, required: true })
    title: String;

    @Prop({ type: Schema.Types.ObjectId, ref: 'User', required: true })
    user: String | Schema.Types.ObjectId
}

// export const BlogSchema = SchemaFactory.createForClass(Blog)


export const BlogSchema = new mongoose.Schema({
    title: { type: String, required: true },
    user: { type: Schema.Types.ObjectId, ref: 'User', required: true }
})
export const BlogModel = mongoose.model('Blog', BlogSchema)
