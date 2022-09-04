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
      res.map((element) => {
        element.contato = element.contato.includes('-') || element.contato.includes('(') || element.contato.includes(')') ? element.contato : this.converterContato(element.contato);
      })
      this.clientes = res;
    });
  }

  converterContato(contato: string) {
    return(`(${contato[0]}${contato[1]}) ${contato[2]}${contato[3]}${contato[4]} - ${contato[0]}${contato[5]}${contato[6]} - ${contato[7]}${contato[8]}${contato[9]}`)
  }

  async deleteCliente(email:string) {
    console.log('deletar',email);
    this.clienteApi.deleteCliente(email).subscribe((res) => {

      this.buscarClientes();

    });


  }


}
