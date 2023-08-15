import { Schema, model } from 'mongoose'

const ShareSchema = new Schema(
  {
    owner: { type: Schema.Types.ObjectId, ref: 'User' },
    //friend: { type: Schema.Types.ObjectId, ref: 'User', required: false },
    //share_type: { type: String, enum: ['public', 'private'], default: 'public' },
    blogs: { type: [Schema.Types.ObjectId], ref: 'Blog' },
  },
  {
    timestamps: {
      createdAt: 'created_at',
      updatedAt: 'updated_at',
    },
  },
)

export const ShareModel = model('Share', ShareSchema)
