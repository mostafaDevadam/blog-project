import { Router } from 'express'
import { BlogController } from './blog.controller'

const router = Router()
router.get('/all', BlogController.getAllBlogs)
router.post('/:user_id', BlogController.createBlogByUserID)
router.get('/:_id', BlogController.getOneBlogById)
router.get('/all/user/:user_id', BlogController.getAllBlogsByUserID)
router.patch('/:_id', BlogController.updateOneBlogById)
router.delete('/:_id', BlogController.removeOneBlogById)

export const BlogRouter = router
