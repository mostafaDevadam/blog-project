package com.example.demo.services

import com.example.demo.DTOs.BlogDTO
import com.example.demo.extensions.toBlog
import com.example.demo.extensions.toDto
import com.example.demo.models.Blog
import com.example.demo.repositories.BlogRepository
import com.example.demo.repositories.UserRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Service
class BlogService(@Autowired val blogRepo: BlogRepository,
                  @Autowired val userRepo: UserRepository,
                  @Autowired val userService: UserService,
    ) {


    fun create(userid: String, blog: Blog): String {
        val blogDto = blog.toDto()
        blogDto.userid = userid
        //blogDto.createdById = userid
        val newBlog = blogRepo.insert(blogDto.toBlog())
        println("new_blog: $newBlog")
        return "created"
    }

    fun findOne( id: String): Blog? {
        val Id = ObjectId(id)
        val blog = blogRepo.findById(Id).get()//.toDto()
        val user = userService.findUserById(blog.userid as ObjectId) // userRepo.findById(blog.userid as ObjectId).get()
        blog.toDto().user = user.toDto()
        //val u = blog.toDto().convertToUser()
        println("find blog by id : ${blog} , ${user} ")
        return blog
    }

    fun findAll(): List<BlogDTO> {
        val blogs = blogRepo.findAll().map(Blog::toDto)
        return blogs
    }


    fun findAllByUserID(userid: String): List<BlogDTO> {
        val userID = ObjectId(userid)
        //blogRepo.findAll()//where("userid").`is`(ObjectId(userid)))
        val blogs = blogRepo.findAllByUserId(userID).map(Blog::toDto)
        println("blogs by user: $blogs")
        //val data = blogs.map(Blog::toDto)
        //println("blogs by user data: $data")
        return blogs
    }
}