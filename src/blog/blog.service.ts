import { BlogModel } from './blog.model'
import { BLOG_INPUT } from '../common/types'
import { UserModel } from '../user/user.model'
import { cleanObject } from '../common/handleObject'
import { ShareModel } from '../share/share.model'

const create = async (userID: string, data: BLOG_INPUT) => {
  // insert userID in owner in blog
  data.owner = userID
  // create a new blog and userID
  const blog = await BlogModel.create(data)
  // update user: push blog_id in blogs in user
  const blog_id = blog._id
  const user = await UserModel.findByIdAndUpdate(userID, {
    $addToSet: { blogs: blog_id },
  })
  return blog
}

const getOne = async (_id: string) => {
  return await BlogModel.findById(_id)
}

const getAll = async () => {
  const all = await BlogModel.find()
  return all
}

const getAllByUserID = async (userID: string) => {
  return await BlogModel.find({ owner: userID })
}

const updateOneById = async (_id: string, data: any) => {
  data = cleanObject(data)
  const updated = await BlogModel.findByIdAndUpdate(_id, data, { new: true })
  return updated
}

const removeOneById = async (_id: string) => {
  // get blog
  const blog = await BlogModel.findById(_id)
  //
  const user = await UserModel.findById(blog?.owner)
  if (blog && user) {
    const user = await UserModel.findByIdAndUpdate(blog?.owner, {
      $pull: { blogs: { $in: [_id] } },
    })
    const removeBlog = await BlogModel.findByIdAndDelete(_id)
    return removeBlog
  }
  // get ownerId, blogId
  // find all share docs using ownerId and blogId
  // delete-many: remove blog from share docs using ownerId and blogId
  const removeBlogsInShare = await ShareModel.updateOne(
    { owner: user?._id },
    {
      $pull: { blogs: { $in: [_id] } },
    },
  )
  //
  return false
}

export const BlogService = {
  create,
  getOne,
  getAll,
  getAllByUserID,
  updateOneById,
  removeOneById,
}
