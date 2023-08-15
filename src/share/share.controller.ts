import { ShareService } from './share.service'

const createOneShareByOwnerId = async (req: any, res: any) => {
  const result = await ShareService.createOneByOwnerId(req.params.user_id, req.body)
  res.send(result)
}

const getOneShareById = async (req: any, res: any) => {
  const result = await ShareService.getOneById(req.params._id)
  res.send(result)
}

const getAllShares = async (req: any, res: any) => {
  const result = await ShareService.getAll()
  res.send(result)
}

const getAllSharesByOwnerId = async (req: any, res: any) => {
  const result = await ShareService.getAllByOwnerId(req.params.user_id)
  res.send(result)
}

const updateOneShareById = async (req: any, res: any) => {
  const result = await ShareService.updateOneById(req.params._id, req.body)
  res.send(result)
}

const removeOneShareById = async (req: any, res: any) => {
  const result = await ShareService.removeOneById(req.params._id)
  res.send(result)
}

export const ShareController = {
  createOneShareByOwnerId,
  getOneShareById,
  getAllShares,
  getAllSharesByOwnerId,
  updateOneShareById,
  removeOneShareById,
}
