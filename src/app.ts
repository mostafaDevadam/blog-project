import express from 'express'
import cors from 'cors'
import bodyParser from 'body-parser'
import Dotenv from 'dotenv'
import { DBConfig } from './config/db.config'
import { AppRouter } from './app.router'

Dotenv.config()
DBConfig(`${process.env.MONGO_URL}`)
const port = process.env.PORT
const app = express()

app.use(cors())
app.use(express.json())

app.use('/api/v0', AppRouter)

app.listen(port, () => {
  console.log('server is running on ', port)
})
