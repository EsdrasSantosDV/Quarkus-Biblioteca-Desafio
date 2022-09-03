# DEVELOPERUP - STEFANINI - DESAFIO FINAL - 09/2022

Você foi contratado para desenvolver um sistema de empréstimo de livro para uma biblioteca, esse sistema já tinha sido iniciado por outro desenvolvedor que já tinha feito Listar Clientes tanto no frontend quanto no backend, foi feito também um componente de mensagens, use ele para gerar mensagens de alerta, erro e sucesso!

Você deve desenvolver as seguintes funcionalidades respeitando as regras, fazer o CRUD (Cadastrar, Listar, Atualizar e Deletar) do Autor, o CRUD de Livros, o CRUD de cliente.

Deve também ser feita uma tela para que seja possível emprestar um livro, também deve ser possível listar todos os empréstimos, nessa tabela deve existir uma coluna chamada ações, que vai exibir um botão chamado “Devolver” este botão deve fazer a devolução sem sair da página e atualizar a listagem.

Funcionalidades:

## 1 - CRUD de Autor
- `1.`: Campos Obrigatórios:
- `1.1.`: Nome
- `1.2.`: ISNI
- `1.3.`: E-mail
- `1.4.`: Data de Nascimento
- `1.5.`: Biografia

- Regras:
- `a.`: É permitido o Cadastro de apenas um autor por código ISNI;
- `b.`: Validar se o e-mail tem pelo menos um ponto e apenas um @ ex: <autor@livro.abc>;
- `c.`: Permitir Apenas Data de nascimento passadas, não podendo conter a data atual nem futura;
- `d.`: Não permitir mais que 200 caracteres na Biografia;
- `e`: Não permitir mais que 50 caracteres no Nome.

## 2 - CRUD de Livros
- `2.`: Campos Obrigatórios:
- `2.1.`: Nome
- `2.2.`: Autor (um a partir dos autores cadastrados)
- `2.3.`: Ano de Publicação
- `2.4.`: Editora
- `2.5.`: Código ISBN (validar em API externa)
- `2.6.`: Quantidade de exemplares

- Regras:
- `a.`: É permitido o Cadastro de apenas um livro por código ISBN;
- `b.`: Apenas um autor por livro;
- `c.`: Não permitir mais que 50 caracteres no Nome;
- `d.`: Não permitir mais que 50 caracteres na Editora;
- `e.`: Permitir o Cadastro somente do ano Atual ou passado em Ano de Publicação;
- `f.`: Permitir apenas valores positivos em Quantidade de exemplares.

## 3 - CRUD de cliente
- `3.`: Campos Obrigatórios:
- `3.1.`: Nome
- `3.2.`: E-mail
- `3.3.`: Telefone

- Regras:
- `a.`: É permitido o Cadastro de apenas um cliente por e-mail;
- `b.`: Validar se o e-mail tem pelo menos um ponto e apenas um @ ex: <autor@livro.abc>;
- `c.`: Não permitir mais que 50 caracteres no Nome;
- `d.`: Usar componente de formatação no campo Telefone com o padrão (XX) XXX – XXX – XXX.

## 4 - Cadastro de empréstimos
- `4.`: Campos Obrigatórios:
- `4.1.`: Livro
- `4.2.`: Cliente
- `4.3.`: Data de início
- Campo Opcional:
- `4.4.`: Data de Entrega

- Regras:
- `a.`: Permitir a busca do cliente por parte do nome ou e-mail completo;
- `b.`: Permitir a busca do livro por parte do nome do livro ou código ISBN completo;
- `c.`: É permitido o empréstimo de somente um exemplar do mesmo título;
- `d.`: O cliente pode fazer no máximo 3 empréstimos sem ter que devolver um antes;
- `e.`: Validar se um título já teve todos os seus exemplares emprestados antes de emprestar um;
- `f.`: Registrar a Data de entrega apenas quando o título for devolvido.

## 5 - Observação:
- `5.1.`: Usar qualquer API externa para validar o ISBN do livro cadastrado, API sugerida: <https://openlibrary.org/dev/docs/api/books>
   `Exemplo de uso`: <https://openlibrary.org/books/OL7353617M.json>
- `5.2.`: Use a data de início e data de entrega para determinar se um livro se encontra emprestado ou não. Use também essa informação aliada da quantidade de exemplares para saber se todos os livros estão emprestados.

## 6 - Bônus:
- `6.1.`: O cliente não solicitou, mas ficaria muito feliz que ao preencher o código ISBN já viesse preenchido o máximo de informações possível
- `6.2.`: O cliente gostava bastante do trabalho do último Dev, pois ele fazia 100% de teste de integração/unitário
- `6.3.`: O Cliente gosta de bonificar trabalhos, então será bonificado as persistências via query


Alguns Exemplos de livros e seus respectivos códigos, para mais livros e código pode ser usado o link abaixo ou qualquer outro de sua preferência.
 [OpenLibrary](https://openlibrary.org/search?q=Angular&mode=everything).

| Nome               | Open Library | ISBN 10    | ISBN 13       |
| ------------------ | ------------ | ---------- | ------------- |
| Angular 4          | OL26414423M  | 3864903572 | 9783864903571 |
| Angular Statistics | OL33776374M  | -          | 9781000739787 |
| Angular Momentum   | OL29039186M  | -          | 9783527617838 |
