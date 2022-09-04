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
  autor: Autor|null=null;

  editar: boolean = true;
  ISNI: String | null = null;


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
    this.ISNI = this.router.getCurrentNavigation()?.extras.state?.ISNI
  }

  ngOnInit(): void {

    console.log(this.ISNI);
    if (this.ISNI && this.editar) {
    this.autorApi.buscarAutor(this.ISNI).subscribe((res:Autor|any) => {
      this.autor = res;
      if (res) {
        this.autorForm.patchValue({
          email: res.email,
          nome: res.nome,
          dataNascimento:res.dataNascimento,
          ISNI:res.ISNI,
          biografia:res.biografia,

        });
      }
        console.log(res);

      });
    }
  }

  async onSubmit(form: Autor) {
    if (new Date(form.dataNascimento.toString()) < new Date()) {

      if (form.email.includes('.') && form.email.includes('@')) {
        if (this.editar) {
          let object: Autor = {
            email: form.email,
            nome: form.nome,
            ISNI: form.ISNI,
            dataNascimento: form.dataNascimento.toString(),
            biografia: form.biografia,
          }
          this.autorApi.updateAutor(object).subscribe((res) => {
            alert('Autor Atualizado com Sucesso!');
            this.router.navigateByUrl('/autores/listar');
          }, (err) => {
            console.log(err)
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
            alert('Autor Adicionado com Sucesso!');
            this.router.navigateByUrl('/autores/listar');
          }, (err) => {
            console.log(err)
            alert('Houve um erro ao adicionar o Autor!');
          });

        }
      }
      else {

        alert('Digite uma Data passada!');
      }
    }
    else {

      alert('Digite uma data Passada!');
    }
  }
}
