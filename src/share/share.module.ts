import { ShareService } from './share.service';
import { ShareController } from './share.controller';
/*
https://docs.nestjs.com/modules
*/

import { Module } from '@nestjs/common';

@Module({
    imports: [],
    controllers: [
        ShareController,],
    providers: [
        ShareService,],
})
export class ShareModule { }
