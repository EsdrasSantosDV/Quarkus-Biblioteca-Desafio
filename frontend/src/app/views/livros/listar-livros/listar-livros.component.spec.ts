import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatTableModule } from '@angular/material/table';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatButtonModule } from '@angular/material/button';
import { of } from 'rxjs';
import { LivrosService } from '../livros.service';
import { ListarLivrosComponent } from './listar-livros.component';

describe('ListarLivrosComponent', () => {
  let component: ListarLivrosComponent;
  let fixture: ComponentFixture<ListarLivrosComponent>;
  const mockService = jasmine.createSpyObj('AutoreService', [
    'listarAutores',
  ]);

  beforeEach(async () => {
    mockService.listarAutores.and.returnValue(of([]));

    await TestBed.configureTestingModule({
      declarations: [ListarLivrosComponent],
      imports: [HttpClientTestingModule, MatTableModule, MatButtonModule],
      providers: [{ provide: LivrosService, useValue: mockService }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarLivrosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
