import { Router } from 'express'
import { UserRouter } from './user/user.router'
import { BlogRouter } from './blog/blog.router'
import { ShareRouter } from './share/share.router'

const router = Router()

router.use('/user', UserRouter)
router.use('/blog', BlogRouter)
router.use('/share', ShareRouter)

export const AppRouter = router
