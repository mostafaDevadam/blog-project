import { UserController } from './user.controller';
import { UserModel, UserSchema } from './user.model';
import { UserService } from './user.service';
import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';

@Module({
    imports: [
        MongooseModule.forFeature([{ name: 'User', schema: UserSchema }]),

    ],
    controllers: [
        UserController
    ],
    providers: [
        UserService
    ],
    exports: [
        UserService
    ]
})
export class UserModule { }
