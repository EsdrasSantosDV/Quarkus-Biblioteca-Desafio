import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Autor from 'src/app/global/models/autor.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ClienteService } from '../clientes.service';
import Cliente from 'src/app/global/models/cliente.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss'],
})
export class ClienteComponent implements OnInit {
  cliente: Cliente | null = null;

  editar: boolean = true;
  email: String | null = null;

  clienteForm: FormGroup = Object.create(null);

  constructor(private clienteApi: ClienteService,
    private modal: NgbModal,
    public router: Router,
    private route: ActivatedRoute,
    private toastr: ToastrService) {

    this.clienteForm = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'nome': new FormControl(null, [Validators.required, Validators.maxLength(50)]),
      'contato': new FormControl(null, [Validators.required]),
    });
    this.editar = (this.route.snapshot.paramMap.get('editar') == 'editar') ? true : false;
    this.email = this.router.getCurrentNavigation()?.extras.state?.email
  }

  ngOnInit(): void {
    if (this.email && this.editar) {
      this.clienteApi.buscarCliente(this.email).subscribe((res) => {
        this.cliente = res[0];
      });
    }
    if (this.cliente) {
      this.clienteForm.patchValue({
        email: this.cliente.email,
        nome: this.cliente.nome,
        contato: this.cliente.contato,
      });
    }

  }

  async onSubmit(form: Cliente) {

    if (form.email.includes('.') && form.email.includes('@')) {
      if (this.editar) {

        let object: Cliente = {
          email: form.email,
          nome: form.nome,
          contato: form.contato
        }

        this.clienteApi.updateCliente(object).subscribe((res) => {
          if (res) {
            // this.toastr.success('Cliente Atualizado com Sucesso', 'Adicionado!');
            alert('Cliente Atualizado com Sucesso!');
            this.router.navigateByUrl('/clientes/listar');
          }
        }, (err) => {
          console.log(err)
          // this.toastr.error('Houve um erro ao editar o Cliente!', 'Erro ao Editar!');
          alert('Houve um erro ao editar o Cliente!');
        });

      }
      else {
        let object: Cliente = {
          email: form.email,
          nome: form.nome,
          contato: form.contato
        }

        this.clienteApi.addCliente(object).subscribe((res) => {
          if (res) {
            // this.toastr.success('Cliente Adicionado com Sucesso', 'Adicionado!');
            alert('Cliente Adicionado com Sucesso!');
            this.router.navigateByUrl('/clientes/listar');
          }
        }, (err: any) => {
          console.log(err)
          // this.toastr.error('Houve um erro ao adicionar o Cliente!', 'Erro ao Adicionar!');
          alert('Houve um erro ao adicionar o Cliente!');
        });
      }
    }
    else {
      this.toastr.error('Digite um E-mail Válido!', 'E-mail Inválido!');
      alert('Digite E-mail Válido!');
    }
  }
}
