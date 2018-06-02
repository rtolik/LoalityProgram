import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'DatePipe'
})
export class DatePipe implements PipeTransform {

  transform(value: any, args?: any): any {
    return value.split('-').reverse().join('.');
  }

}
