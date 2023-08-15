package com.example.demo.services

import com.example.demo.DTOs.BlogDTO
import com.example.demo.DTOs.ShareDTO
import com.example.demo.extensions.toDto
import com.example.demo.handling.HandleConvertingData
import com.example.demo.models.Blog
import com.example.demo.models.User
import com.example.demo.repositories.BlogRepository
import com.example.demo.repositories.UserRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Service
class ShareService(@Autowired val userRepo: UserRepository,
                   @Autowired val blogRepo: BlogRepository,
                   @Autowired val blogService: BlogService,
                   @Autowired val userService: UserService,
                   @Autowired val handlingCovertingData: HandleConvertingData,
                   @Autowired private val mongoTemplate: MongoTemplate? = null
    ) {

    //@Autowired
    //private val mongoTemplate: MongoTemplate? = null
    fun UpdateAddInShare( userid: String,  share: ShareDTO): String{
        println("req body share: ${share.shares} ${share.add_blogs} ${share.remove_blogs}")
        val user = userRepo.findById(ObjectId(userid)).get()
        /*
        val updateUser = userRepo.save(
            user.apply {
                name = "sebo"
                this.shareIds = share.toUser().shareIds
            }
        )*/
        val query = Query()
        query.addCriteria(Criteria.where("id").`is`(userid))
        val qFindUser: User? = mongoTemplate!!.findOne(query, User::class.java)
        println("qFindUser: $qFindUser")

        if (share.remove_blogs?.isNotEmpty() == true) {

            for(r in share.remove_blogs){

                val update4 = Update().pull("shares", ObjectId(r))
                val tryUpdate4 = mongoTemplate.updateFirst(
                    query,
                    update4,
                    User::class.java
                )
                println("tryUpdate4: ${tryUpdate4.toString()}")
            }

        }
        if (share.add_blogs?.isNotEmpty() == true) {
            // save(), saveAll(...array)
            val update2 = Update().AddToSetBuilder("shares").each(share.add_blogs.let { handlingCovertingData.listStringToListObjectIds(it) })
            val tryUpdate2 = mongoTemplate.updateFirst(
                query,
                update2,
                User::class.java
            )
            println("tryUpdate2: $tryUpdate2")
            /*
                 for(l in share.add_blogs!!){

                     val update = Update().addToSet("shareIds",   ObjectId(l)).set("name", "mosa2")
                     val tryUpdate = mongoTemplate.updateFirst(
                         //where(User::id).`is`(ObjectId(userid)).javaClass).let {
                         query,
                         update,
                         User::class.java
                     )
                     println("tryUpdate: $tryUpdate")
                 }
             */
        }
        return "done"
    }

    fun findAllSharesByUserID( userid: String): ArrayList<BlogDTO> {
        var list : ArrayList<BlogDTO> = ArrayList<BlogDTO>()
        // get user by id: ObjectId(userid)
        val user: User = userService.findOne(userid) as User // userRepo.findById(ObjectId(userid)).get()
        print("user: ${user.toDto()}")
        // get shares from user
        if(user.shares?.isNotEmpty() == true){
            val shares = user.shares
            for(id in shares!!){
                println("share id: $id")
                val blog: Blog = blogService.findOne(id.toString()) as Blog //blogRepo.findById(id).get()
                println("share blog: ${blog}")
                list.add(blog.toDto())
            }
            println("share list blogs: ${list}")
        }
        // schleife auf shares
        // get blog using each item_id and push it in list: list.add(blog)
        return list
        // return "all shares by user"
    }


    fun findAllShares(): List<BlogDTO> {
        // list
        var list : ArrayList<BlogDTO> = ArrayList<BlogDTO>()
        // get users
        val users = userService.findAll() //userRepo.findAll()
        // schleife auf users
        for(user in users){
            if(!user.shares.isNullOrEmpty()){
                val shares = user.shares
                for(id in shares!!){
                    println("all shares: $id")
                    val blog = blogService.findOne(id.toString()) as Blog //blogRepo.findById(id).get()
                    println("share blog: ${blog}")
                    list.add(blog.toDto())
                }
            }
        }
        return list
        // get shares from each user
        // schleife auf shares
        // get blog using each item_id and push it in list: list.add(blog)
        // return the list
    }
}