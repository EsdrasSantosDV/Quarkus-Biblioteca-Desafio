import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Autor from 'src/app/global/models/autor.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AutoresService } from '../autores.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  templateUrl: './autor.component.html',
  styleUrls: ['./autor.component.scss'],
})
export class AutorComponent implements OnInit {
  autores: Autor[] = [];

  editar: boolean = true;
  email: String | null = null;


  autorForm: FormGroup = Object.create(null);

  constructor(private autorApi: AutoresService,
    private modal: NgbModal,
    public router: Router,
    private route: ActivatedRoute,
    private toastr: ToastrService,) {

    this.autorForm = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'nome': new FormControl(null, [Validators.required, Validators.maxLength(50)]),
      'ISNI': new FormControl(null, [Validators.required]),
      'dataNascimento': new FormControl(null, [Validators.required]),
      'biografia': new FormControl(null, [Validators.required, Validators.maxLength(200)]),
    });
    this.editar = (this.route.snapshot.paramMap.get('editar') == 'editar') ? true : false;
    this.email = this.router.getCurrentNavigation()?.extras.state?.email
  }

  ngOnInit(): void {
    this.autorApi.listarAutores().subscribe((res) => {
      this.autores = res;
    });
  }

  async onSubmit(form: Autor) {

    if (new Date(form.dataNascimento.toString()) < new Date()) {
      if (form.email.includes('.') && form.email.includes('@')) {
        if (this.editar) {

          let object: Autor = {
            email: form.email,
            nome: form.nome,
            ISNI: form.ISNI.toString(),
            dataNascimento: form.dataNascimento.toString(),
            biografia: form.biografia,
          }

          this.autorApi.updateAutor(object).subscribe((res) => {
            if (res) {
              // this.toastr.success('Autor Atualizado com Sucesso', 'Adicionado!');
              alert('Autor Atualizado com Sucesso!');
              this.router.navigateByUrl('/autores/listar');
            }
          }, (err) => {
            console.log(err)
            // this.toastr.error('Houve um erro ao editar o Autor!', 'Erro ao Editar!');
            alert('Houve um erro ao editar o Autor!');
          });
        }
        else {
          let object: Autor = {
            email: form.email,
            nome: form.nome,
            ISNI: form.ISNI.toString(),
            dataNascimento: form.dataNascimento.toString(),
            biografia: form.biografia,
          }

          this.autorApi.addAutor(object).subscribe((res) => {
            if (res) {
              // this.toastr.success('Autor Adicionado com Sucesso!', 'Adicionado!');
              alert('Autor Adicionado com Sucesso!');
              this.router.navigateByUrl('/autores/listar');
            }
          }, (err) => {
            console.log(err)
            // this.toastr.error('Houve um erro ao adicionar o Autor!', 'Erro ao Adicionar!');
            alert('Houve um erro ao adicionar o Autor!');
          });

        }
      }
      else {
        // this.toastr.error('Digite um E-mail Válido!', 'E-mail Inválido!');
        alert('Digite um Ano Válido!');
      }
    }
    else {
      // this.toastr.error('Digite uma Data Válida!', 'Data Inválida!');
      alert('Digite um Ano Válido!');
    }
  }
}
