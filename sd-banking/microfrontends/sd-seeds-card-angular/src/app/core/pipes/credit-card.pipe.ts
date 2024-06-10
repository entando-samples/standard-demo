import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'creditCard',
})
export class CreditCardPipe implements PipeTransform {
  transform(value: string): any {
    if (!value) return '';

    let obfuscatedValue = '...';
    if (value.length >= 4) {
      obfuscatedValue += value.substring(value.length - 4, value.length);
    }
    return obfuscatedValue;
  }
}
