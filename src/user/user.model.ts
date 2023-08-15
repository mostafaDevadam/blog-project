import { Schema, model } from 'mongoose'

const UserSchema = new Schema(
  {
    email: { type: String, unique: true, required: true, lowercase: true },
    password: { type: String, unique: true, required: true },
    blogs: { type: [Schema.Types.ObjectId], ref: 'Blog' },
    //share: { type: [Schema.Types.ObjectId], ref: 'Blog' },
  },
  {
    timestamps: {
      createdAt: 'created_at',
      updatedAt: 'updated_at',
    },
  },
)
/**
 UserSchema.pre('save', async function (next): Promise<void> {
  const user: any = this
  if (!user.isModified('password')) {
    return next()
  }
  try {
    const salt: string = await bcrypt.genSalt(10)
    const hash: string = await bcrypt.hash(user.password, salt)
    user.password = hash
    user.isUser = true
    // send email to user with confirm link

    next()
  } catch (error: any) {
    return next(error)
  }
})
 */

export const UserModel = model('User', UserSchema)
