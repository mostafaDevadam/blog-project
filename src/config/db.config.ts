import mongoose from 'mongoose'

const URL = `${process.env.MONGO_URL}`

export const DBConfig = async (url: string) => {
  try {
    await mongoose.connect(url)
    console.log('db connected')
  } catch (error) {
    console.log(error)
  }
}
