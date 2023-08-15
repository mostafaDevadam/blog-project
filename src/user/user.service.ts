import { USER, USER_INPUT } from '@src/common/types'
import { UserModel } from './user.model'

const create = async (data: USER_INPUT) => {
  const user = await (await UserModel.create(data)).save()
  return user
}

const getOne = async (_id: string) => {
  const user = await UserModel.findById(_id).populate('blogs')
  return user
}

const getOneByEmail = async (email: string) => {
  return await UserModel.findOne({ email: email })
}

export const UserService = {
  create,
  getOne,
  getOneByEmail,
}
