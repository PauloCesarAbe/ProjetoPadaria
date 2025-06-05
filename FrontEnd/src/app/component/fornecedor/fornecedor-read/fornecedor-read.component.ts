import { Component, Input } from '@angular/core';
import { Fornecedor } from '../fornecedor.model';

@Component({
  selector: 'app-fornecedor-read',
  templateUrl: './fornecedor-read.component.html',
  styleUrls: ['./fornecedor-read.component.css']
})
export class FornecedorReadComponent {
  @Input() fornecedores: Fornecedor[] = []; // Recebe a lista de fornecedores do componente pai
  
  displayedColumns = ['forId', 'forNomeFantasia', 'forCnpj', 'forRazaoSocial', 'action'];
}