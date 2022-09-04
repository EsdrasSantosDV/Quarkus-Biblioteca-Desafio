import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatTableModule } from '@angular/material/table';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarAutoresComponent } from './listar-autores.component';
import { MatButtonModule } from '@angular/material/button';
import { of } from 'rxjs';
import { AutoresService } from '../autores.service';

describe('ListarAutoresComponent', () => {
  let component: ListarAutoresComponent;
  let fixture: ComponentFixture<ListarAutoresComponent>;
  const mockService = jasmine.createSpyObj('AutoreService', [
    'listarAutores',
  ]);

  beforeEach(async () => {
    mockService.listarAutores.and.returnValue(of([]));

    await TestBed.configureTestingModule({
      declarations: [ListarAutoresComponent],
      imports: [HttpClientTestingModule, MatTableModule, MatButtonModule],
      providers: [{ provide: AutoresService, useValue: mockService }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarAutoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
