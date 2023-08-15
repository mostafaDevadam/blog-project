import { UserService } from './user.service'

const createUser = async (req: any, res: any) => {
  const result = await UserService.create(req.body)
  res.send(result)
}

const getUserByID = async (req: any, res: any) => {
  const result = await UserService.getOne(req.params._id)
  res.send(result)
}

export const UserController = {
  createUser,
  getUserByID,
}
