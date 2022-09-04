import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import Cliente from 'src/app/global/models/cliente.model';
import { ClienteService as ClienteService } from '../clientes.service';

@Component({
  templateUrl: './listar-clientes.component.html',
  styleUrls: ['./listar-clientes.component.scss'],
})
export class ListarClientesComponent implements OnInit {
  clientes: Cliente[] = [];
  displayedColumns = ['nome', 'email', 'contato', 'acoes'];

  constructor(private clienteApi: ClienteService) {
  }

  async ngOnInit() {
    await this.buscarClientes();
  }

  async buscarClientes() {
    this.clienteApi.listarClientes().subscribe((res) => {
      this.clientes = res;
    });
  }



  async deleteCliente(email:string) {
    console.log('deletar',email);
    this.clienteApi.deleteCliente(email).subscribe((res) => {

      this.buscarClientes();

    });


  }


}
