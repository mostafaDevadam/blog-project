import { Router } from 'express'
import { ShareController } from './share.controller'

const router = Router()

router.get('/all', ShareController.getAllShares)
router.post('/owner/:user_id', ShareController.createOneShareByOwnerId)
router.get('/all/owner/:user_id', ShareController.getAllSharesByOwnerId)
router.get('/:_id', ShareController.getOneShareById)
router.patch('/:_id', ShareController.updateOneShareById)
router.delete('/:_id', ShareController.removeOneShareById)

export const ShareRouter = router
