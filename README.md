# Investaê 🚀

O objetivo do **Investaê** é ser seu assessor de investimentos completo, conectando todos os seus bancos e rentabilidades em um único lugar. Chega de ficar acessando vários apps!  
Esta API faz parte do nosso app mobile, centralizando seus investimentos, orientando suas decisões e tornando tudo simples, visual e inteligente.

Foi aí que decidimos criar o Investaê:  
Um app que centraliza seus investimentos, te orienta e torna tudo simples, visual e inteligente.

---

## 📚 Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [👥 Integrantes](#integrantes)
- [🛠️ Tecnologias Utilizadas](#tecnologias-utilizadas)
- [▶️ Como Executar](#como-executar)
- [📁 Estrutura do Projeto](#estrutura-do-projeto)
- [📝 Documentação da API (Swagger)](#documentação-da-api-swagger)
- [🔗 Endpoints e Exemplos de Testes](#endpoints-e-exemplos-de-testes)
  - [Usuários Investidores](#usuários-investidores)
  - [Investimentos](#investimentos)
  - [Bancos](#bancos)
  - [Tipos de Investimento](#tipos-de-investimento)
- [⚙️ Regras de Negócio](#regras-de-negócio)
- [⚠️ Tratamento de Erros](#tratamento-de-erros)
- [💡 Padrões e Boas Práticas](#padrões-e-boas-práticas)
- [📝 Licença](#licença)

---

## Sobre o Projeto

O **Investaê** é uma API RESTful para cadastro de usuários investidores, seus investimentos, rentabilidades diárias, bancos e tipos de investimento.  
O projeto segue boas práticas de arquitetura, separação de camadas, uso de DTOs, tratamento de erros e documentação automática.  
Esta API está integrada ao nosso aplicativo mobile, proporcionando uma experiência centralizada e inteligente para o investidor.

Repositório oficial: [https://github.com/JuliaAzevedoLins/ChallengeXP_Java](https://github.com/JuliaAzevedoLins/ChallengeXP_Java)

---

## 👥 Integrantes

|        Nome Completo         |  RMs  |
|------------------------------|-------|
| André Lambert                | 99148 |
| Felipe Cortez                | 99750 |
| Julia Azevedo Lins           | 98690 |
| Luis Gustavo Barreto Garrido | 99210 |
| Victor Hugo Aranda Forte     | 99667 |

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Oracle Database (pode ser adaptado para H2)
- Swagger/OpenAPI
- JUnit 5

---

## ▶️ Como Executar

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/JuliaAzevedoLins/ChallengeXP_Java.git
   cd ChallengeXP_Java
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

## 📁 Estrutura do Projeto

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

## 📝 Documentação da API (Swagger)

Acesse a documentação interativa em:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🔗 Endpoints e Exemplos de Testes

### 👤 Usuários Investidores

#### ➕ Criar novo usuário investidor

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

#### 📋 Listar todos os usuários investidores

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

#### 🔍 Buscar usuário investidor por CPF

- **GET** `/api/usuario-investimentos/{cpf}`
- **Resposta:** `200 OK`
  ```json
  {
    "id": 1,
    "cpfIdentificacao": "12345678900",
    "investimentos": [ ... ]
  }
  ```

#### 🗑️ Deletar usuário investidor por CPF

- **DELETE** `/api/usuario-investimentos/{cpf}`
- **Resposta:** `200 OK`
  ```json
  "Usuário e seus investimentos foram deletados."
  ```

---

### 💰 Investimentos

#### ➕ Criar novo investimento para um usuário

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

#### 📋 Listar todos os investimentos

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

#### 🔍 Listar investimentos por CPF do usuário

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

#### ✏️ Atualizar investimento pelo ID

- **PUT** `/api/investimentos/{id}`
- **Body:** (igual ao POST de investimento)
- **Resposta:** `200 OK`
  ```json
  "Investimento atualizado com sucesso"
  ```

#### 🗑️ Deletar investimento pelo ID

- **DELETE** `/api/investimentos/{id}`
- **Resposta:** `200 OK`
  ```json
  "Investimento deletado com sucesso"
  ```

---

### 🏦 Bancos

#### 📋 Listar bancos por CPF

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

### 📊 Tipos de Investimento

#### 📋 Listar tipos de investimento por CPF

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

## ⚙️ Regras de Negócio

- **Centralização e Consulta:** O Investaê centraliza todos os investimentos do usuário, permitindo visualizar e gerenciar aplicações de diferentes bancos em um só lugar.
- **Cadastro e Gerenciamento:** Apenas usuários investidores podem cadastrar, atualizar ou remover seus próprios investimentos.
- **Rentabilidade Diária:** Cada investimento pode ter uma ou mais rentabilidades diárias associadas, permitindo o acompanhamento detalhado da evolução.
- **Bancos e Tipos de Investimento são apenas consultáveis:**  
  Os controllers de **Banco** (`/api/bancos`) e **Tipo de Investimento** (`/api/tipos-investimento`) possuem apenas métodos GET, pois, pela regra de negócio, bancos e tipos de investimento são derivados dos investimentos cadastrados pelo usuário. Não é permitido criar, editar ou remover bancos ou tipos de investimento diretamente via API — eles são sempre obtidos a partir dos investimentos já registrados.
- **Validação e Segurança:** Todos os dados são validados e tratados para garantir integridade e segurança das informações.

---

## ⚠️ Tratamento de Erros

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

## 💡 Padrões e Boas Práticas

- **Separação de camadas:** Controller, Service, Repository, DTO, Model.
- **Uso de DTOs:** Nenhum endpoint retorna entidades diretamente.
- **Documentação Swagger/OpenAPI:** Todos os endpoints documentados.
- **Tratamento global de erros:** Classe `RestExceptionHandler`.
- **Enums para valores fixos:** Exemplo: `TipoInvestimentoEnum`.
- **JavaDoc:** Presente nas principais classes e métodos.

---

## 📝 Licença

Este projeto é livre para fins acadêmicos.

---