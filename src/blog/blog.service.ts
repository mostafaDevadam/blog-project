import { Injectable } from '@nestjs/common';
import { Model } from 'mongoose';
import { InjectModel } from '@nestjs/mongoose';
import { Blog, BlogDocument, BlogModel } from './blog.model';
import { BlogDTO } from 'src/DTOs/blog.dto';
import { IBLOG } from 'src/interfaces/blog.interface';


@Injectable()
export class BlogService {
    constructor(@InjectModel('Blog') private readonly blogModel: typeof BlogModel) { }

    async create(blog: BlogDTO): Promise<String> {
        //blog.user = userID
        const new_blog = await this.blogModel.create(blog)
        return "Created"
    }

    async findOneById(_id: String): Promise<IBLOG> {
        const blog: IBLOG = await this.blogModel.findById(_id).populate("user")
        console.log("blog: ", blog)
        return blog

    }

    async findAll(): Promise<any> {
        const blogs = await this.blogModel.find()
        console.log("blogs: ", blogs)
        return blogs

    }

    async findAllByUserID(userID: String): Promise<any> {
        const blogsByUser = await this.blogModel.find({ user: userID })
        return blogsByUser

    }

    async updateOneById(_id: String, blog: BlogDTO): Promise<any> {
        const updated = await this.blogModel.findByIdAndUpdate(_id, blog, { new: true, multi: true })
        return updated
    }


}
