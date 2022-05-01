import { Component } from '@angular/core';
import { Product } from './test/Product';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'appointment-dashboard';
  products!: Product[];
  selectedProduct1!: Product;
}
