import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { AutoresService } from './autores.service';

describe('AutorsService', () => {
  let service: AutoresService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(AutoresService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('>> listarAutors | deve retornar Autors[]', () => {
    const expectedData = [
      {
        email: 'teste',
        nome: 'teste',
        ISNI: 0,
        dataNascimento: new Date(),
        biografia: 'teste',
      },
    ];

    service.listarAutores().subscribe((res) => {
      expect(res).toEqual(expectedData);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/autor');
    expect(req.request.method).toBe('GET');
    req.flush(expectedData);
  });
});
