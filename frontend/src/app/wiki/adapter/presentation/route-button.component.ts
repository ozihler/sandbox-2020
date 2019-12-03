import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-route-button',
  template: `
      <div>
          <a class="btn btn-dark" routerLink="/{{link}}">{{buttonText}}</a>
      </div>`
})
export class RouteButtonComponent {
  @Input() buttonText: string;
  @Input() link: string;
}
