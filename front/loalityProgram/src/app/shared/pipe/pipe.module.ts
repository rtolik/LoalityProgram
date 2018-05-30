import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ImagePipe} from "./pipes/image.pipe";
import {NullOrUndefinedPipe} from "./pipes/null-or-undefined-pipe.pipe";

@NgModule({
  declarations: [
    ImagePipe,
    NullOrUndefinedPipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ImagePipe,
    NullOrUndefinedPipe
  ]
})
export class PipeModule {

}
