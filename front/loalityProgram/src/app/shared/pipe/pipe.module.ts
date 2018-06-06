import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ImagePipe} from "./pipes/image.pipe";
import {NullOrUndefinedPipe} from "./pipes/null-or-undefined-pipe.pipe";
import {IsoDatePipe} from './pipes/date.pipe';

@NgModule({
  declarations: [
    ImagePipe,
    NullOrUndefinedPipe,
    IsoDatePipe,

  ],
  imports: [
    CommonModule
  ],
  exports: [
    ImagePipe,
    IsoDatePipe,
    NullOrUndefinedPipe
  ]
})
export class PipeModule {

}
