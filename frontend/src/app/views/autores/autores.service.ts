import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import ApiUrl from 'src/app/global/constant/api-urls.constant';
import Autor from 'src/app/global/models/autor.model';

@Injectable({
  providedIn: 'root',
})
export class AutoresService {
  constructor(private http: HttpClient) { }

  listarAutores(): Observable<Autor[]> {
    return this.http
      .get<Autor[]>(ApiUrl.listarAutores)
  }

  buscarAutor(ISNI: String): Observable<Autor[]> {
    return this.http
      .get<Autor[]>(ApiUrl.listarAutores + '/' + ISNI)

  }

  addAutor(newAutor: Autor): Observable<Autor[]> {
    return this.http
      .post<Autor[]>(ApiUrl.listarAutores, newAutor);
  }

  updateAutor(updateAutor: Autor): Observable<Autor[]> {
    return this.http
      .put<Autor[]>(ApiUrl.listarAutores+'/'+updateAutor.ISNI, updateAutor);
  }

  deleteAutor(deleteAutor: string) {
    return this.http
      .delete(ApiUrl.listarAutores + '/' + deleteAutor);
  }
}
