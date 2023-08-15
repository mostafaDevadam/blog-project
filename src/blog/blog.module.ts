import { MongooseModule } from '@nestjs/mongoose';
import { BlogController } from './blog.controller';
import { BlogService } from './blog.service';
/*
https://docs.nestjs.com/modules
*/

import { Module } from '@nestjs/common';
import { Blog, BlogSchema } from './blog.model';

@Module({
    imports: [
        MongooseModule.forFeature([{ name: 'Blog', schema: BlogSchema }]),

    ],
    controllers: [
        BlogController
    ],
    providers: [
        BlogService
    ],

})
export class BlogModule { }
