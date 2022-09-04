import { Component, OnInit } from '@angular/core';
import Autor from 'src/app/global/models/autor.model';
import { AutoresService } from '../autores.service';

@Component({
  templateUrl: './listar-autores.component.html',
  styleUrls: ['./listar-autores.component.scss'],
})
export class ListarAutoresComponent implements OnInit {
  autores: Autor[] = [];
  displayedColumns = ['email', 'nome', 'biografia', 'dataNascimento', 'acoes'];


  constructor(private autorApi: AutoresService) { }

  async ngOnInit() {
    await this.buscarAutores();
  }

  async buscarAutores(){    
    this.autorApi.listarAutores().subscribe((res) => {
      this.autores = res;
    });
  }

  async deleteAutor(){
    console.log('deletar');
    this.buscarAutores();
  }
}
