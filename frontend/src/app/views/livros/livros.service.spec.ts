import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { LivrosService } from './livros.service';

describe('AutorsService', () => {
  let service: LivrosService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(LivrosService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('>> listarAutors | deve retornar Autors[]', () => {
    const expectedData = [
      {
        nome: 'teste',
        autor: 'teste',
        anoPublicacao: new Date(),
        editora: 'teste',
        codISBN: 'teste',
        qtdExemplares: 0,
      },
    ];

    service.listarLivros().subscribe((res) => {
      expect(res).toEqual(expectedData);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/autor');
    expect(req.request.method).toBe('GET');
    req.flush(expectedData);
  });
});
