import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'shortener',
  standalone: true
})
export class NameShortenerPipe implements PipeTransform {

  transform(name: string, length: number): string {
    return name.length > length ? name.substring(0,length) + "..." : name;
  }

}
