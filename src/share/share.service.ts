/*
https://docs.nestjs.com/providers#services
*/

import { Injectable } from '@nestjs/common';
import { ShareDTO } from 'src/DTOs/share.dto';

@Injectable()
export class ShareService {
    constructor() { }

    async addAndUpdate(userID: String, share: ShareDTO): Promise<any> {

    }

    async findAllByUserID(userID: String): Promise<any> {

    }

    async findAll(): Promise<any> {

    }
}
