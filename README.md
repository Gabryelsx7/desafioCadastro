# 🐾 Desafio Cadastro de Pets

API REST desenvolvida em **Java + Spring Boot** para gerenciamento (cadastro, consulta, atualização e remoção) de pets, incluindo dados de endereço, tipo e sexo do animal.

## 📋 Sobre o projeto

Este projeto implementa um CRUD completo de Pets, com:

- Cadastro, listagem, atualização e exclusão de pets
- Filtros de busca dinâmicos (via `Specification`)
- Validação de regras de negócio
- Tratamento centralizado de exceções
- Testes unitários

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Maven
- Banco de dados: *(ex: PostgreSQL / H2 — ajuste conforme seu `application.yaml`)*
- JUnit / Mockito (testes)

## 📁 Estrutura do projeto

```
src/main/java/com/example/desafioCadastro
├── controller
│   ├── PetController.java
│   ├── GlobalExceptionHandler.java
│   └── dto
│       ├── PetRequestDTO.java
│       └── PetResponseDto.java
├── domain
│   ├── Pet.java
│   ├── Endereco.java
│   └── enums
│       ├── TipoPet.java
│       └── SexoPet.java
├── repository
│   ├── PetRepository.java
│   └── PetSpecification.java
├── service
│   ├── PetService.java
│   └── exception
│       ├── PetNaoEncontradoException.java
│       └── RegraDeNegocioException.java
└── PetsV2Application.java
```

## ⚙️ Como executar o projeto

### Pré-requisitos

- Java 17 (ou versão utilizada no projeto)
- Maven
- *(Banco de dados, se aplicável)*

### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/desafioCadastro.git

# Entre na pasta do projeto
cd desafioCadastro

# Rode com Maven
./mvnw spring-boot:run
```

A aplicação sobe por padrão em `http://localhost:8080`.

## 📌 Endpoints principais

| Método | Endpoint         | Descrição                          |
|--------|------------------|-------------------------------------|
| POST   | `/pets`          | Cadastra um novo pet                |
| GET    | `/pets`          | Lista pets (com filtros opcionais)  |
| GET    | `/pets/{id}`     | Busca um pet por ID                 |
| PUT    | `/pets/{id}`     | Atualiza os dados de um pet         |
| DELETE | `/pets/{id}`     | Remove um pet                       |

> Ajuste esta tabela conforme os endpoints reais implementados no `PetController`.

## 🧪 Exemplo de requisição

```json
POST /pets
{
  "nome": "Rex",
  "tipo": "CACHORRO",
  "sexo": "MACHO",
  "endereco": {
    "cidade": "São Paulo",
    "estado": "SP"
  }
}
```
