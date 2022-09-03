# Projeto Back-end

Após ler o enunciado disponível [aqui](../README.md "DESAFIO DEVELOPERUP STEFANINI"), você poderá seguir este tutorial para ver os procedimentos necessários para rodar o projeto backend.

Este projeto utiliza o framework Quarkus.
Se quiser saber mais sobre o Quarkus, visite o site: https://quarkus.io/ .

## Rodando Back-end
Você tem duas formas de rodar o programa:
Acesse a pasta do projeto clonado no terminal (cmd ou terminal da IDE)

`1`: A primeira é usando o maven embutido no projeto da seguinte forma
# Vá para a pasta backend
```shell script
$ cd backend
```

# Compile a aplicação - Windows
```shell script
mvnw compile quarkus:dev
```

 # Compile a aplicação - Linux
```shell script
./mvnw compile quarkus:dev
```
> **_NOTE:_**  Para usar o maven do projeto as variáveis de ambiente deverão estar configuradas, exemplo JAVA_HOME.
> **_NOTE:_**  O Quarkus agora vem com uma Dev UI, que está disponível no modo dev apenas em at http://localhost:8080/q/dev/.

`2`: A outra forma é utilizando um maven externo do projeto ou fornecido pela sua IDE.


## Acessando a API
Esta API expõe o swagger, que além de outras coisas serve como documentação para o desenvolvedor ver como e quais endpoints estão disponíveis, e poder testar a api sem depender do frontend.

## Swagger UI
http://localhost:8080/q/swagger-ui/

## Executando testes de integração
Para a realização dos testes fique atendo a estrutura de onde cada Classe deve ficar. Além disso é necessário rodar o seguinte comando para rodar os testes.
# Testando a aplicação - Windows
```shell script
mvnw verify
```
# Testando a aplicação - Linux
```shell script
./mvnw verify
```
**_NOTE:_**  Note que o diretorio `\target\jacoco-report` foi criado, para acessar a cobertura do seu código abra o arquivo `index.html` no seu navegador.