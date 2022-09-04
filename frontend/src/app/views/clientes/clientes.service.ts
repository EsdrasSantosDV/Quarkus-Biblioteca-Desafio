import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import Cliente from 'src/app/global/models/cliente.model';
import ApiUrl from 'src/app/global/constant/api-urls.constant';

@Injectable({
  providedIn: 'root',
})
export class ClienteService {
  constructor(private http: HttpClient) { }

  listarClientes(): Observable<Cliente[]> {
    return this.http
      .get<Cliente[]>(ApiUrl.listarClientes)
      .pipe(map((res) => res.map((c, i) => ({ ...c, posicao: i }))));
  }

  buscarCliente(email: String): Observable<Cliente[]> {
    return this.http
      .get<Cliente[]>(ApiUrl.listarClientes + '/' + email);

  }

  addCliente(newCliente: Cliente): Observable<Cliente[]> {
    return this.http
      .post<Cliente[]>(ApiUrl.listarClientes, newCliente);
  }

  updateCliente(updateCliente: Cliente): Observable<Cliente[]> {
    return this.http
      .put<Cliente[]>(ApiUrl.listarClientes+'/'+updateCliente.email, updateCliente);
  }

  deleteCliente(email:string) {

    //console.log(ApiUrl.listarClientes + '/' + email);
    return this.http
      .delete(ApiUrl.listarClientes + '/' + email);
  }


}
