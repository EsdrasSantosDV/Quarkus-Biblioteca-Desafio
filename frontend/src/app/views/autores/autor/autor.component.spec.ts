import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatTableModule } from '@angular/material/table';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatButtonModule } from '@angular/material/button';
import { of } from 'rxjs';
import { AutoresService } from '../autores.service';
import { AutorComponent } from './autor.component';

describe('ListarAutoresComponent', () => {
  let component: AutorComponent;
  let fixture: ComponentFixture<AutorComponent>;
  const mockService = jasmine.createSpyObj('AutoreService', [
    'listarAutores',
  ]);

  beforeEach(async () => {
    mockService.listarAutores.and.returnValue(of([]));

    await TestBed.configureTestingModule({
      declarations: [AutorComponent],
      imports: [HttpClientTestingModule, MatTableModule, MatButtonModule],
      providers: [{ provide: AutoresService, useValue: mockService }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
