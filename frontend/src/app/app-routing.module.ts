import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutorComponent } from './views/autores/autor/autor.component';
import { ListarAutoresComponent } from './views/autores/listar-autores/listar-autores.component';
import { ClienteComponent } from './views/clientes/cliente/cliente.component';
import { ListarClientesComponent } from './views/clientes/listar-clientes/listar-clientes.component';
import { HomeComponent } from './views/home/home.component';
import { ListarLivrosComponent } from './views/livros/listar-livros/listar-livros.component';
import { LivroComponent } from './views/livros/livro/livro.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    data: { pageTitle: 'Página Inicial' },
  },
  {
    path: 'clientes/listar',
    component: ListarClientesComponent,
    data: { pageTitle: 'Listar Clientes' },
  },
  {
    path: 'clientes/cliente/:editar',
    component: ClienteComponent,
    data: { pageTitle: 'Listar Clientes' },
  },
  {
    path: 'autores/listar',
    component: ListarAutoresComponent,
    data: { pageTitle: 'Listar Autores' },
  },
  {
    path: 'autores/autor/:editar',
    component: AutorComponent,
    data: { pageTitle: 'Autores' },
  },
  {
    path: 'livros/listar',
    component: ListarLivrosComponent,
    data: { pageTitle: 'Listar Autores' },
  },
  {
    path: 'livros/livro/:editar',
    component: LivroComponent,
    data: { pageTitle: 'Autores' },
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
