import { Component, Input } from '@angular/core';
import { Cliente } from '../cliente.model';

@Component({
  selector: 'app-cliente-read',
  templateUrl: './cliente-read.component.html',
  styleUrls: ['./cliente-read.component.css']
})
export class ClienteReadComponent {
  // Recebe os clientes do componente pai (ClienteCrudComponent)
  @Input() clientes: Cliente[] = [];

  // Define as colunas a serem exibidas
  displayedColumns = ['cliId', 'cliNome', 'cliCpf', 'cliEmail', 'cliTelefone', 'action'];
}