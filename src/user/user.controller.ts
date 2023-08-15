/*
https://docs.nestjs.com/controllers#controllers
*/

import { Body, Controller, Get, Param, Post, Patch, } from '@nestjs/common';
import { UserService } from './user.service';
import { UserDTO } from 'src/DTOs/user.dto';
import { ShareDTO } from 'src/DTOs/share.dto';
import { UserSchema } from './user.model';

@Controller("/user")
export class UserController {
    constructor(private userService: UserService) { }


    @Get("/share/all")
    async findAllSharesEachUser() {
        const shares = await this.userService.findAllShares()
        return shares
    }


    @Post()
    async create(@Body() userDto: any) {
        console.log("user dto:", userDto)
        const user = await this.userService.create(userDto)
        return user
    }

    @Get("/:_id")
    async findOne(@Param('_id') _id: any) {
        const user = await this.userService.findOneByID(_id)
        return user
    }

    @Get()
    async findall() {
        const users = await this.userService.findAll()
        return users
    }

    @Patch("/share/:_id")
    async updateShare(@Param('_id') _id: any, @Body() share: ShareDTO) {
        const user = await this.userService.updateShare(_id, share)
        return user
    }

    @Get("/share/:_id")
    async allSharesByUser(@Param('_id') _id: any) {
        const user = await this.userService.getSharesByUserID(_id)
        return user
    }







}
