import { Pipe, PipeTransform } from '@angular/core';
import {url} from "../../config/url";
import {isNullOrUndefined} from "util";

@Pipe({
  name: 'image'
})
export class ImagePipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if(!isNullOrUndefined(value))
    return url+value+'';
    else
      return ''
  }
}
