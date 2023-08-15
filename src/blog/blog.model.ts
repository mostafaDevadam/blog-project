import { Schema, model } from 'mongoose'

const BlogSchema = new Schema(
  {
    title: { type: String, required: true },
    content: { type: String },
    owner: { type: Schema.Types.ObjectId, ref: 'User', required: true },
  },
  {
    timestamps: {
      createdAt: 'created_at',
      updatedAt: 'updated_at',
    },
  },
)

export const BlogModel = model('Blog', BlogSchema)
