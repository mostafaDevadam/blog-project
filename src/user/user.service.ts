import { Injectable } from '@nestjs/common';
import { UserModel } from './user.model';
import { Model } from 'mongoose';
import { InjectModel } from '@nestjs/mongoose';
import { UserDTO } from 'src/DTOs/user.dto';
import { ShareDTO } from 'src/DTOs/share.dto';
import { Ishare } from 'src/interfaces/share.interface';

@Injectable()
export class UserService {
    constructor(@InjectModel('User') private userModel: typeof UserModel) { }

    async create(user: any): Promise<any> {
        console.log("user dto:", user)
        const new_user = await (await this.userModel.create(user)).save()
        console.log("new_user:", new_user)
        return new_user
    }

    async findOneByID(_id: String): Promise<any> {
        const user = await this.userModel.findById(_id)//.populate("shares")
        return user
    }

    async updateShare(_id: String, share: ShareDTO) {
        console.log("shares dto: ", share)

        if (share.remove_blogs.length > 0) {
            const user = await this.userModel.updateOne({ _id }, {
                $pullAll: { "shares": share.remove_blogs },
            }, { multi: true, new: true })

            console.log("shares pull all user: ", user)

        }

        if (share.add_blogs) {
            const user = await this.userModel.updateOne({ _id }, {
                $addToSet: { "shares": share.add_blogs }
            }, { multi: true, new: true })

            console.log("shares user: ", user)


        }
    }

    async getSharesByUserID(_id: String) {
        const user = await this.userModel.findById(_id, { _id: 0, __v: 0, name: 0 }).populate("shares")
        return user
    }

    async findAllShares(): Promise<any> {
        const users = await this.userModel.find({}, { _id: 0, __v: 0, name: 0 }).populate("shares")

        const shares = users?.map((m) => m.shares).flat()
        //const flat = shares?.flat()
        //console.log('flat:', flat)
        return shares




    }

    async findAll(): Promise<any> {
        const users = await this.userModel.find()//{}, { _id: 0, __v: 0, name: 0 }).populate("shares")
        return users
    }


}
