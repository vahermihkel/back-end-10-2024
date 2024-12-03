import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[autosize]',
  standalone: true
})
export class AutosizeDirective {

  constructor(private elementRef: ElementRef) {
    this.elementRef.nativeElement.style.width = 190 + 'px';
  }

  @HostListener("input")
  adjust() {
    const element = this.elementRef.nativeElement;
    element.style.overflow = "hidden";
    element.style.height = "auto";
    element.style.height = element.scrollHeight + "px";
  }

}
