import { Component, OnInit } from '@angular/core';
import { Product } from '../product.model';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  product: Product = {
    proNome: '', // Nome do produto
    proPrecoCusto: 0, // Preço de custo inicial
    proPrecoVenda: 0, // Preço de venda inicial
  };

  // Injeção de dependências: ProductService e Router
  constructor(private productService: ProductService, private router: Router) {}

  ngOnInit(): void {}

  // Método para criar um produto
  createProduct(): void {
    this.productService.create(this.product).subscribe(() => {
      this.productService.showMessage('Produto criado!'); // Exibe mensagem de sucesso
      this.router.navigate(['/products']); // Redireciona para a lista de produtos
    });
  }

  // Método para cancelar a criação e voltar à lista de produtos
  cancel(): void {
    this.router.navigate(['/products']);
  }
}