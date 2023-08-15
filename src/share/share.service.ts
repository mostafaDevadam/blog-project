import { ShareModel } from './share.model'
import { cleanObject } from '../common/handleObject'
import { SimpleConsoleLogger } from 'typeorm'

const createOneByOwnerId = async (owner_id: any, data: any) => {
  data['owner'] = owner_id
  data = cleanObject(data)
  console.log('share data:', data)
  const one_share = await ShareModel.findOne({ owner: owner_id })
  console.log('one_share:', one_share)

  if (!one_share) {
    const new_share = await (
      await ShareModel.create({ owner: data.owner })
    ).save()
    const addBlogsIds = await updateOneById(new_share?._id, {
      add_share_blogs: data.blogs,
    })

    return new_share
  }
  const addBlogsIds = await updateOneById(one_share?._id, {
    add_share_blogs: data.blogs,
  })
  return addBlogsIds
}

const getOneById = async (_id: string) => {
  const one = await ShareModel.findById(_id)
  return one
}

const getAll = async () => await ShareModel.find()

const getAllByOwnerId = async (userID: string) => {
  const all = await ShareModel.find({ owner: userID })
  return all
}

const updateOneById = async (_id: any, data: any) => {
  data = cleanObject(data)
  const shareDoc = await ShareModel.findById(_id)

  if (!shareDoc) {
    return false
  }
  const updated1 = await ShareModel.updateOne(
    { _id },
    {
      $pullAll: { blogs: data.remove_share_blogs },
    },
    { multi: true, new: true },
  )

  const updated2 = await ShareModel.updateOne(
    { _id },
    {
      $addToSet: { blogs: data.add_share_blogs },
    },
    { multi: true, new: true },
  )
  return updated2
}

const removeOneById = async (_id: string) => {
  const removed = await ShareModel.findByIdAndDelete(_id)
  return removed
}

export const ShareService = {
  createOneByOwnerId,
  getOneById,
  getAll,
  getAllByOwnerId,
  updateOneById,
  removeOneById,
}
