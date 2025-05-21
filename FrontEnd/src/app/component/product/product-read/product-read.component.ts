import { Component, Input } from '@angular/core';
import { Product } from '../product.model';

@Component({
  selector: 'app-product-read',
  templateUrl: './product-read.component.html',
  styleUrls: ['./product-read.component.css']
})
export class ProductReadComponent {
  @Input() products: Product[] = [];

  displayedColumns = ['proId', 'proNome', 'proPrecoCusto', 'proPrecoVenda', 'action'];
}