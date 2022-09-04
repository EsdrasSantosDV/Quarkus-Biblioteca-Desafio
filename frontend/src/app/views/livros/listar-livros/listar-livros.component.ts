import { Component, OnInit } from '@angular/core';
import Autor from 'src/app/global/models/autor.model';
import Livros from 'src/app/global/models/livros.model';
import { LivrosService } from '../livros.service';

@Component({
  templateUrl: './listar-livros.component.html',
  styleUrls: ['./listar-livros.component.scss'],
})
export class ListarLivrosComponent implements OnInit {
  livros: Livros[] = [];
  displayedColumns = ['nome', 'autor', 'anoPublicacao', 'editora', 'codISBN', 'qtdExemplares', 'acoes'];


  constructor(private autorApi: LivrosService) { }

  async ngOnInit() {
    this.buscarLivros();
  }
  
  async buscarLivros() {
    this.autorApi.listarLivros().subscribe((res) => {
      this.livros = res;
    });
  }

  async deleteLivros() {
    console.log('deletar');
    this.buscarLivros();
  }
}
