package com.example.demo.handling

import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class HandleConvertingData {


    fun listStringToListObjectIds(list: List<String>): ArrayList<ObjectId> {
        val list_: ArrayList<ObjectId> = ArrayList<ObjectId>()

        for(item in list) {
            list_.add(ObjectId(item))
        }
        return list_
    }

    fun listObjectIdsToListStrings(list: List<ObjectId>): ArrayList<String> {
        val list_: ArrayList<String> = ArrayList<String>()

        for(item in list) {
            list_.add(item.toString())
        }
        return list_
    }
}