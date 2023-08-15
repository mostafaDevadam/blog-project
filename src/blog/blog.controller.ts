import { BlogService } from './blog.service'

const createBlogByUserID = async (req: any, res: any) => {
  // req.params.user_id
  const result = await BlogService.create(req.params.user_id, req.body)
  res.send(result)
}

const getOneBlogById = async (req: any, res: any) => {
  // req.params._id
  const result = await BlogService.getOne(req.params._id)
  res.send(result)
}

const getAllBlogs = async (req: any, res: any) => {
  const result = await BlogService.getAll()
  res.send(result)
}

const getAllBlogsByUserID = async (req: any, res: any) => {
  // req.params.user_id
  const result = await BlogService.getAllByUserID(req.params.user_id)
  res.send(result)
}

const updateOneBlogById = async (req: any, res: any) => {
  // req.params._id
  const result = await BlogService.updateOneById(req.params._id, req.body)
  res.send(result)
}

const removeOneBlogById = async (req: any, res: any) => {
  // req.params._id
  const result = await BlogService.removeOneById(req.params._id)
  res.send(result)
}

export const BlogController = {
  createBlogByUserID,
  getOneBlogById,
  getAllBlogs,
  getAllBlogsByUserID,
  updateOneBlogById,
  removeOneBlogById,
}
