/*
https://docs.nestjs.com/controllers#controllers
*/
import { Body, Controller, Get, Param, Post } from '@nestjs/common';
import { BlogService } from './blog.service';
import { BlogDTO } from 'src/DTOs/blog.dto';

@Controller("/blog")
export class BlogController {
    constructor(private blogService: BlogService) { }


    @Post("/:user_id")
    async create(@Param("user_id") user_id: any, @Body() blogDto: BlogDTO) {
        blogDto.user = user_id
        console.log("BlogDto dto:", blogDto)
        const user = await this.blogService.create(blogDto)
        return user
    }

    @Get("/one/:_id")
    async findOne(@Param('_id') _id: any) {
        const blog = await this.blogService.findOneById(_id)
        return blog
    }

    @Get()
    async findAll() {
        const blogs = await this.blogService.findAll()
        return blogs
    }

    @Get("/all/user/:user_id")
    async findAllByUser(@Param('user_id') user_id: any) {
        const blogs = await this.blogService.findAllByUserID(user_id)
        return blogs
    }
}
