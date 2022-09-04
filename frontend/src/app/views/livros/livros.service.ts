import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import ApiUrl from 'src/app/global/constant/api-urls.constant';
import Livros from 'src/app/global/models/livros.model';

@Injectable({
  providedIn: 'root',
})
export class LivrosService {
  constructor(private http: HttpClient) {}

  listarLivros(): Observable<Livros[]> {
    return this.http
      .get<Livros[]>(ApiUrl.listarLivros)
      .pipe(map((res) => res.map((c, i) => ({ ...c, posicao: i }))));
  }

  addLivro(newLivro: Livros): Observable<Livros[]> {
    return this.http
      .post<Livros[]>(ApiUrl.listarLivros, newLivro);
  }

  updateLivro(updateLivro: Livros): Observable<Livros[]> {
    return this.http
      .put<Livros[]>(ApiUrl.listarLivros, updateLivro)
      .pipe(map((res) => res.map((c, i) => ({ ...c, posicao: i }))));
  }

  deleteLivro(deleteLivro: Livros): Observable<Livros[]> {
    return this.http
      .delete<Livros[]>(ApiUrl.listarLivros + '/' + deleteLivro)
      .pipe(map((res) => res.map((c, i) => ({ ...c, posicao: i }))));
  }
}
