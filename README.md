# Investaê

API para gerenciamento de investimentos de usuários, desenvolvida em Java 17 com Spring Boot 3.  
Este projeto implementa uma arquitetura RESTful, com documentação Swagger/OpenAPI, tratamento robusto de erros, uso de DTOs, separação de camadas e exemplos de testes para todos os endpoints.

---

## Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Integrantes](#integrantes)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Executar](#como-executar)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Documentação da API (Swagger)](#documentação-da-api-swagger)
- [Endpoints e Exemplos de Testes](#endpoints-e-exemplos-de-testes)
  - [Usuários Investidores](#usuários-investidores)
  - [Investimentos](#investimentos)
  - [Bancos](#bancos)
  - [Tipos de Investimento](#tipos-de-investimento)
- [Tratamento de Erros](#tratamento-de-erros)
- [Padrões e Boas Práticas](#padrões-e-boas-práticas)
- [Como Contribuir](#como-contribuir)
- [Licença](#licença)

---

## Sobre o Projeto

O **Investaê** é uma API RESTful para cadastro de usuários investidores, seus investimentos, rentabilidades diárias, bancos e tipos de investimento.  
O projeto segue boas práticas de arquitetura, separação de camadas, uso de DTOs, tratamento de erros e documentação automática.

---

## Integrantes

|        Nome Completo         |  RMs  |
|------------------------------|-------|
| André Lambert                | 99148 |
| Felipe Cortez                | 99750 |
| Julia Azevedo Lins           | 98690 |
| Luis Gustavo Barreto Garrido | 99210 |
| Victor Hugo Aranda Forte     | 99667 |

*Preencha com os nomes e RMs dos integrantes do grupo.*

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Oracle Database (pode ser adaptado para H2)
- Swagger/OpenAPI
- JUnit 5

---

## Como Executar

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/seu-usuario/investae.git
   cd investae
   ```

2. **Configure o banco de dados Oracle no arquivo `src/main/resources/application.properties`.**
   > Para testes locais, pode ser adaptado para H2.

3. **Execute a aplicação:**
   ```sh
   ./mvnw spring-boot:run
   ```
   ou
   ```sh
   mvn spring-boot:run
   ```

4. **Acesse a documentação Swagger:**
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Estrutura do Projeto

```
src/
  main/
    java/
      com/challenge/investimentos/investimentos_api/
        controller/   # Controllers REST
        service/      # Lógica de negócio
        repository/   # Repositórios JPA
        model/        # Entidades JPA
        dto/          # Data Transfer Objects
        enums/        # Enums de domínio
        config/       # Configurações (Swagger, ExceptionHandler)
    resources/
      application.properties
      static/index.html
  test/
    java/
      com/challenge/investimentos/investimentos_api/
        InvestimentosApiApplicationTests.java
```

---

## Documentação da API (Swagger)

Acesse a documentação interativa em:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Endpoints e Exemplos de Testes

### Usuários Investidores

#### Criar novo usuário investidor

- **POST** `/api/usuario-investimentos`
- **Body:**
  ```json
  {
    "cpfIdentificacao": "12345678900"
  }
  ```
- **Resposta:** `200 OK`
  ```json
  "Usuário criado com sucesso."
  ```

#### Listar todos os usuários investidores

- **GET** `/api/usuario-investimentos`
- **Resposta:** `200 OK`
  ```json
  [
    {
      "id": 1,
      "cpfIdentificacao": "12345678900",
      "investimentos": []
    }
  ]
  ```

#### Buscar usuário investidor por CPF

- **GET** `/api/usuario-investimentos/{cpf}`
- **Resposta:** `200 OK`
  ```json
  {
    "id": 1,
    "cpfIdentificacao": "12345678900",
    "investimentos": [ ... ]
  }
  ```

#### Deletar usuário investidor por CPF

- **DELETE** `/api/usuario-investimentos/{cpf}`
- **Resposta:** `200 OK`
  ```json
  "Usuário e seus investimentos foram deletados."
  ```

---

### Investimentos

#### Criar novo investimento para um usuário

- **POST** `/api/investimentos`
- **Body:**
  ```json
  {
    "cpfIdentificacao": "12345678900",
    "dataUsuarioInvestimentos": [
      {
        "nomeBanco": "C6 Bank",
        "codigoBancario": 336,
        "tipoInvestimento": "RENDA_FIXA",
        "nomeInvestimento": "Tesouro Selic",
        "montanteInicial": 1000.0,
        "valorInicialAcao": 0.0,
        "taxaRentabilidade": "0.12",
        "numeroAcoesInicial": 0,
        "rentabilidadeDiaria": [
          {
            "dataRentabilidadeDiaria": "19-05-2025",
            "valorDiarioAcao": 0.0,
            "taxaDiarioRentabilidade": "0.12",
            "montanteAcumuladoDiario": 1120.0
          }
        ]
      }
    ]
  }
  ```
- **Resposta:** `201 Created`
  ```json
  "Investimento(s) criado(s) com sucesso"
  ```

#### Listar todos os investimentos

- **GET** `/api/investimentos`
- **Resposta:** `200 OK`
  ```json
  [
    {
      "id": 1,
      "nomeBanco": "C6 Bank",
      "codigoBancario": 336,
      "tipoInvestimento": "RENDA_FIXA",
      "nomeInvestimento": "Tesouro Selic",
      "montanteInicial": 1000.0,
      "valorInicialAcao": 0.0,
      "taxaRentabilidade": "0.12",
      "numeroAcoesInicial": 0,
      "rentabilidadeDiaria": [ ... ]
    }
  ]
  ```

#### Listar investimentos por CPF do usuário

- **GET** `/api/investimentos/usuario/{cpf}`
- **Resposta:** `200 OK`
  ```json
  [
    {
      "id": 1,
      "nomeBanco": "C6 Bank",
      ...
    }
  ]
  ```

#### Atualizar investimento pelo ID

- **PUT** `/api/investimentos/{id}`
- **Body:** (igual ao POST de investimento)
- **Resposta:** `200 OK`
  ```json
  "Investimento atualizado com sucesso"
  ```

#### Deletar investimento pelo ID

- **DELETE** `/api/investimentos/{id}`
- **Resposta:** `200 OK`
  ```json
  "Investimento deletado com sucesso"
  ```

---

### Bancos

#### Listar bancos por CPF

- **GET** `/api/bancos/{cpf}`
- **Resposta:** `200 OK`
  ```json
  [
    {
      "nomeBanco": "C6 Bank",
      "codigoBancario": 336
    }
  ]
  ```

---

### Tipos de Investimento

#### Listar tipos de investimento por CPF

- **GET** `/api/tipos-investimento/{cpf}`
- **Resposta:** `200 OK`
  ```json
  [
    {
      "tipoInvestimento": "RENDA_FIXA"
    }
  ]
  ```

---

## Tratamento de Erros

- **Campos obrigatórios:** Retorna `400 Bad Request` com mensagem clara.
- **Enum inválido:** Retorna `400 Bad Request` e lista os valores permitidos.
- **Usuário ou investimento não encontrado:** Retorna `404 Not Found`.
- **Erro interno:** Retorna `500 Internal Server Error`.

Exemplo de erro para enum inválido:
```json
{
  "message": "Valor inválido para o campo TipoInvestimento. Valores permitidos: [RENDA_FIXA, RENDA_VARIAVEL, TESOURO_DIRETO, CRIPTOMOEDA, FUNDO_IMOBILIARIO, CDB, LCI, LCA, OUTRO]."
}
```

---

## Padrões e Boas Práticas

- **Separação de camadas:** Controller, Service, Repository, DTO, Model.
- **Uso de DTOs:** Nenhum endpoint retorna entidades diretamente.
- **Documentação Swagger/OpenAPI:** Todos os endpoints documentados.
- **Tratamento global de erros:** Classe `RestExceptionHandler`.
- **Enums para valores fixos:** Exemplo: `TipoInvestimentoEnum`.
- **JavaDoc:** Presente nas principais classes e métodos.

---

## Como Contribuir

1. Fork este repositório
2. Crie uma branch: `git checkout -b minha-feature`
3. Commit suas alterações: `git commit -m 'Minha feature'`
4. Push para a branch: `git push origin minha-feature`
5. Abra um Pull Request

---

## Licença

Este projeto é livre para fins acadêmicos.

---