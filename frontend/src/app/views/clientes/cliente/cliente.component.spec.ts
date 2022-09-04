import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatTableModule } from '@angular/material/table';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatButtonModule } from '@angular/material/button';
import { of } from 'rxjs';
import { ClienteComponent } from './cliente.component';
import { ClienteService } from '../clientes.service';

describe('ClienteComponent', () => {
  let component: ClienteComponent;
  let fixture: ComponentFixture<ClienteComponent>;
  const mockService = jasmine.createSpyObj('clienteservice', [
    'listarClientes',
  ]);

  beforeEach(async () => {
    mockService.listarClientes.and.returnValue(of([]));

    await TestBed.configureTestingModule({
      declarations: [ClienteComponent],
      imports: [HttpClientTestingModule, MatTableModule, MatButtonModule],
      providers: [{ provide: ClienteService, useValue: mockService }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
