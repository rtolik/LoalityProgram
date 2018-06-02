import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ImagePipe} from "./pipes/image.pipe";
import {NullOrUndefinedPipe} from "./pipes/null-or-undefined-pipe.pipe";
import { DatePipe } from './pipes/date.pipe';

@NgModule({
  declarations: [
    ImagePipe,
    NullOrUndefinedPipe,
    DatePipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ImagePipe,
    DatePipe,
    NullOrUndefinedPipe
  ]
})
export class PipeModule {

}
