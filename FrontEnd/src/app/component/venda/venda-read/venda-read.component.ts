import { Component, Input } from '@angular/core';
import { VendaService } from '../venda.service';
import { Venda } from '../venda.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-venda-read',
  templateUrl: './venda-read.component.html',
  styleUrls: ['./venda-read.component.css']
})
export class VendaReadComponent {

  @Input() vendas: Venda[] = [];


  constructor(private vendaService: VendaService, private router: Router) {}

  ngOnInit(): void {
    this.loadVendas();
  }

  displayedColumns = ['cliId', 'vendaCodigo'];

  loadVendas(): void{
    this.vendaService.read().subscribe({
      next: (data) => this.vendas = data,
      error: (err) => {
        console.error('Erro ao carregar vendas', err);
        this.vendaService.showMessage('Erro ao carregar vendas');
      }
    });
}

  onInfoClick(venda: Venda): void {
    
    console.log('Informações da venda:', venda);
    this.vendaService.showMessage(`Venda ${venda.vendaCodigo} clicada.`);
  }
}