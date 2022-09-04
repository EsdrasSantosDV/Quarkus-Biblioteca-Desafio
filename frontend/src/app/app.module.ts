import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './core/components/layout/layout.component';
import { FooterComponent } from './core/components/footer/footer.component';
import { HomeComponent } from './views/home/home.component';
import { ListarClientesComponent } from './views/clientes/listar-clientes/listar-clientes.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { ListarAutoresComponent } from './views/autores/listar-autores/listar-autores.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AutorComponent } from './views/autores/autor/autor.component';
import { NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ListarLivrosComponent } from './views/livros/listar-livros/listar-livros.component';
import { LivroComponent } from './views/livros/livro/livro.component';
import { ToastrModule } from 'ngx-toastr';
import { ClienteComponent } from './views/clientes/cliente/cliente.component';

import { NgxMaskModule } from 'ngx-mask'

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    HomeComponent,
    ListarClientesComponent,
    ClienteComponent,
    ListarAutoresComponent,
    AutorComponent,
    ListarLivrosComponent,
    LivroComponent,
    FooterComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    NgbModule,
    NgbModalModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatTableModule,
    MatSnackBarModule,
    MatCheckboxModule,
    NgxMaskModule.forRoot(),
    ToastrModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
