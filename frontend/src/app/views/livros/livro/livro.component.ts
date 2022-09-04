import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import Livros from 'src/app/global/models/livros.model';
import { LivrosService } from '../livros.service';

@Component({
  templateUrl: './livro.component.html',
  styleUrls: ['./livro.component.scss'],
})
export class LivroComponent implements OnInit {
  livro: Livros | null = null;

  editar: boolean = true;
  nome: String | null = null;

  livroForm: FormGroup = Object.create(null);

  constructor(private livroApi: LivrosService,
    private modal: NgbModal,
    public router: Router,
    private route: ActivatedRoute,
    private toastr: ToastrService) {

    this.livroForm = new FormGroup({
      'nome': new FormControl(null, [Validators.required, Validators.maxLength(50)]),
      'autor': new FormControl(null, [Validators.required]),
      'anoPublicacao': new FormControl(null, [Validators.required]),
      'editora': new FormControl(null, [Validators.required, Validators.maxLength(50)]),
      'codISBN': new FormControl(null, [Validators.required]),
      'qtdExemplares': new FormControl(null, [Validators.required]),
    });
    this.editar = (this.route.snapshot.paramMap.get('editar') == 'editar') ? true : false;
    this.nome = this.router.getCurrentNavigation()?.extras.state?.nome;
  }

  ngOnInit(): void {

    if (this.nome && this.editar) {
      this.livroApi.listarLivros().subscribe((res: Livros[]) => {
        this.livro = res[0];
      });
    }
    if (this.livro) {
      this.livroForm.patchValue({
        nome: this.livro.nome,
        autor: this.livro.autor,
        anoPublicacao: this.livro.anoPublicacao,
        editora: this.livro.editora,
        codISBN: this.livro.codISBN,
        qtdExemplares: this.livro.qtdExemplares,
      });
    }
  }

  async onSubmit(form: Livros) {
    var data = new Date();
    var ano = data.getFullYear();
    if (form.anoPublicacao == ano || form.anoPublicacao == (ano - 1)) {
      if (parseInt(form.qtdExemplares.toString()) > 0) {
        await this.isValid(form.codISBN).then(async (res: boolean) => {
          if (res) {
            if (this.editar) {

              let object: Livros = {
                nome: form.nome,
                autor: form.autor,
                editora: form.editora,
                codISBN: form.codISBN.toString(),
                qtdExemplares: parseInt(form.qtdExemplares.toString()),
                anoPublicacao: parseInt(form.anoPublicacao.toString()),
              }

              this.livroApi.updateLivro(object).subscribe((res) => {
                if (res) {
                  // this.toastr.success('Livro Atualizado com Sucesso', 'Adicionado!');
                  alert('Livro Atualizado com Sucesso!');
                  this.router.navigateByUrl('/autores/listar');
                }
              }, (err) => {
                console.log(err)
                // this.toastr.error('Houve um erro ao editar o Livro!', 'Erro ao Editar!');
                alert('Houve um erro ao editar o Livro!');
              });
            }
            else {
              let object: Livros = {
                nome: form.nome,
                autor: form.autor,
                editora: form.editora,
                codISBN: form.codISBN.toString(),
                qtdExemplares: parseInt(form.qtdExemplares.toString()),
                anoPublicacao: parseInt(form.anoPublicacao.toString()),
              }
              this.livroApi.addLivro(object).subscribe((res) => {
                if (res) {
                  // this.toastr.success('Livro Adicionado com Sucesso', 'Adicionado!');
                  alert('Livro Adicionado com Sucesso!');
                  this.router.navigateByUrl('/livros/listar');
                }
              }, (err: any) => {
                console.log(err);
                // this.toastr.error('Houve um erro ao adicionar o Livro!', 'Erro ao Adicionar!');
                alert('Houve um erro ao adicionar o Livro!');
              });
            }
          }
          else {
            // this.toastr.error('Digite um Código ISBN Válido!', 'Código ISBN Inválido!');
            alert('Digite um Código ISBN Válido!');
          }
        })
      }
      else {
        // this.toastr.error('Digite uma Quantidade de Exemplares Válida!', 'Quantidade de Exemplares Inválida!');
        alert('Digite um Ano Válido!');
      }
    }
    else {
      // this.toastr.error('Digite um Ano Válido!', 'Ano Inválido!');
      alert('Digite um Ano Válido!');
    }

  }

  async isValid(cod: string) {
    /* VALIDAÇÃO */
    return true;

  }
}
