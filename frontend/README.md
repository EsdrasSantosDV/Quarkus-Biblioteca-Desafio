# Projeto Front-end

Após ler o enunciado disponível [aqui](../README.md "DESAFIO DEVELOPERUP STEFANINI"), você poderá seguir este tutorial para ver os procedimentos necessários para rodar o projeto frontend.

## Rodando a aplicação Front-end
Você precisa executar dois comandos no terminal para conseguir subir o projeto.
Acesse a pasta do projeto no terminal/cmd.
# Vá para a pasta frontend
```shell script
$ cd frontend
```

# Instale as dependências
```shell script
npm install
```

# Execute a aplicação em modo de desenvolvimento
```shell script
ng serve
```

# O servidor iniciará na porta padrão 4200
- http://localhost:4200/
O aplicativo será recarregado automaticamente se você alterar qualquer um dos arquivos de origem.

## Verificando o padrão de qualidade do código
Digite `ng lint` para verificar se o sódigo possui algum erro em potêncial ou não está de acordo com os padrões de qualidade estabelecidos.
```shell script
ng lint
```

## Executando testes unitários
Digite `ng test` para executar os testes unitários via [Karma](https://karma-runner.github.io).
```shell script
ng test
```

## Verificando a cobertura de teste
Digite `ng test --code-coverage` para gerar o relatório de cobertura de testes
```shell script
ng test --code-coverage
```
Note que o diretorio `\coverage` foi criado, para acessar a cobertura do seu código abra o arquivo `index.html` no seu navegador.

## links Uteis
https://getbootstrap.com/docs/5.1/getting-started/introduction/
https://v12.material.angular.io/
