import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatTableModule } from '@angular/material/table';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatButtonModule } from '@angular/material/button';
import { of } from 'rxjs';
import { LivrosService } from '../livros.service';
import { LivroComponent } from './livro.component';

describe('ListarAutoresComponent', () => {
  let component: LivroComponent;
  let fixture: ComponentFixture<LivroComponent>;
  const mockService = jasmine.createSpyObj('AutoreService', [
    'listarAutores',
  ]);

  beforeEach(async () => {
    mockService.listarAutores.and.returnValue(of([]));

    await TestBed.configureTestingModule({
      declarations: [LivroComponent],
      imports: [HttpClientTestingModule, MatTableModule, MatButtonModule],
      providers: [{ provide: LivrosService, useValue: mockService }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LivroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
