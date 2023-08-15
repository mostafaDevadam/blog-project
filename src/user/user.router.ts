import { Router } from 'express'
import { UserController } from './user.controller'

const router = Router()

router.post('/', UserController.createUser)
router.get('/:_id', UserController.getUserByID)
router.get('/all')

export const UserRouter = router
