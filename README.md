# Gerenciador de Tarefas

API REST desenvolvida em **Java 25** com **Spring Boot 4**, criada como projeto final da disciplina de **Programação Web Back-end I**.

O projeto tem como objetivo disponibilizar uma API para centralizar o gerenciamento de projetos, responsáveis e tarefas, permitindo criar, consultar, atualizar, filtrar e remover tarefas, com persistência dos dados em banco de dados relacional. A aplicação não possui interface gráfica.

## 🚀 Tecnologias utilizadas

* **Java 25**
* **Spring Boot 4.x**
* **Maven**
* **Spring Web**
* **Spring Data JPA**
* **PostgreSQL**

## 📋 Funcionalidades

A API permite:

* Cadastrar projetos;
* Listar projetos;
* Cadastrar responsáveis;
* Listar responsáveis;
* Criar tarefas vinculadas a projetos;
* Listar tarefas;
* Filtrar tarefas por status;
* Filtrar tarefas por projeto;
* Consultar uma tarefa pelo ID;
* Atualizar tarefas;
* Alterar o status de uma tarefa;
* Atribuir um responsável a uma tarefa;
* Remover tarefas;
* Registrar automaticamente a data de criação da tarefa;
* Registrar automaticamente a data de conclusão quando uma tarefa é concluída;
* Retornar `404 Not Found` quando um recurso solicitado não existe.

## 🗂️ Modelos de dados

### Projeto

Um projeto representa um conjunto de tarefas que precisam ser gerenciadas.

### Responsável

Representa a pessoa que pode ser atribuída a uma tarefa.

### Tarefa

Uma tarefa possui informações como:

* `id`
* `titulo`
* `descricao`
* `status`
* `prioridade`
* `prazo`
* `criadaEm`
* `concluidaEm`
* `projeto`
* `responsavel`

Toda tarefa obrigatoriamente pertence a um projeto, enquanto a atribuição de um responsável é opcional.

### Status da tarefa

As tarefas podem possuir os seguintes status:

```text
NOVA
EM_ANDAMENTO
CONCLUIDA
CANCELADA
```

Uma nova tarefa é criada com o status `NOVA`.

### Prioridade

As prioridades disponíveis são:

```text
BAIXA
MEDIA
ALTA
```

## 🗄️ Banco de dados

O banco de dados utilizado no projeto é o **PostgreSQL**.

A persistência é realizada utilizando **Spring Data JPA**, com as entidades mapeadas através de JPA e os repositórios utilizando `JpaRepository`.

Antes de executar a aplicação, é necessário possuir uma instância do PostgreSQL disponível e configurar as informações de conexão de acordo com o arquivo de configuração do projeto.

> **Observação:** as credenciais e o nome exato do banco devem corresponder à configuração existente na aplicação.

### (Opcional) Rodar o banco via container Docker 🐋

Na pasta do projeto, suba o container do PostgreSql
```bash
docker compose up -d
```
E nesse caso, nos proximos passos voce deve rodar o Spring Boot com o profile docker
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=docker
```

## ⚙️ Requisitos

Para executar o projeto, é necessário ter instalado:

* Java 25
* PostgreSQL

Verifique a versão instalada:

```bash
java -version
```
> **Observação:** Não é necessário ter o maven instalado, o projeto já disponibiliza um wrapper.

## ▶️ Como executar

### 1. Clone o repositório

```bash
git clone https://github.com/antoniopedroaraujo/gerenciador-tarefas.git
```

Entre no diretório do projeto:

```bash
cd gerenciador-tarefas
```

### 2. Configure o PostgreSQL

Crie um banco de dados PostgreSQL para a aplicação e configure as informações de conexão no arquivo de configuração do Spring Boot.

Exemplo de configuração:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gerenciador_tarefas
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Aqui utilizamos usuário e senha padrão (postgres). Utilize os valores de conexão correspondentes à configuração do seu ambiente.

### 3. Instale as dependências do projeto

```bash
mvn clean install
```

### 4. Execute a aplicação

```bash
mvn spring-boot:run
```

Após a inicialização, a API estará disponível para receber requisições HTTP.

## 🧪 Testando a API

As requisições podem ser realizadas utilizando ferramentas como Postman

A coleção completa está disponível em:

- [📋 Coleção do Postman](./postman/gerenciador-tarefas.postman_collection.json)

A coleção contempla:

- Criação de projetos
- Listagem de projetos
- Criação de responsáveis
- Listagem de responsáveis
- Criação de tarefas
- Listagem de tarefas
- Filtro por status
- Filtro por projeto
- Consulta por ID
- Atualização de tarefas
- Conclusão de tarefas
- Remoção de tarefas
- Validação de recurso inexistente (404)

## 📌 Endpoints

### Projetos

| Método | Endpoint    | Descrição                | Status        |
| ------ | ----------- | ------------------------ | ------------- |
| `POST` | `/projetos` | Cadastra um novo projeto | `201 Created` |
| `GET`  | `/projetos` | Lista todos os projetos  | `200 OK`      |

### Responsáveis

| Método | Endpoint        | Descrição                    | Status        |
| ------ | --------------- | ---------------------------- | ------------- |
| `POST` | `/responsaveis` | Cadastra um novo responsável | `201 Created` |
| `GET`  | `/responsaveis` | Lista todos os responsáveis  | `200 OK`      |

### Tarefas

| Método   | Endpoint        | Descrição                   | Status                             |
| -------- | --------------- | --------------------------- | ---------------------------------- |
| `POST`   | `/tarefas`      | Cria uma nova tarefa        | `201 Created`                      |
| `GET`    | `/tarefas`      | Lista todas as tarefas      | `200 OK`                           |
| `GET`    | `/tarefas/{id}` | Consulta uma tarefa pelo ID | `200 OK` / `404 Not Found`         |
| `PUT`    | `/tarefas/{id}` | Atualiza uma tarefa         | `200 OK` / `404 Not Found`         |
| `DELETE` | `/tarefas/{id}` | Remove uma tarefa           | `204 No Content` / `404 Not Found` |

## 🔎 Filtros de tarefas

O endpoint de listagem de tarefas aceita filtros opcionais através de query string.

### Filtrar por status

```http
GET /tarefas?status=NOVA
```

Exemplo:

```http
GET /tarefas?status=CONCLUIDA
```

### Filtrar por projeto

O endpoint também deve permitir a filtragem das tarefas por projeto através de query string.

Exemplo:

```http
GET /tarefas?projeto=1
```

O desafio exige filtros por **status** e **projeto** na listagem de tarefas.

## 📝 Exemplo de criação de tarefa

### Requisição

```http
POST /tarefas
Content-Type: application/json
```

```json
{
  "titulo": "Revisar o contrato do fornecedor",
  "descricao": "Conferir clausulas de reajuste antes da renovacao",
  "prioridade": "ALTA",
  "prazo": "2026-09-30",
  "projeto": {
    "id": 1
  },
  "responsavel": {
    "id": 2
  }
}
```

Ao criar uma tarefa, o sistema define automaticamente:

* `status` como `NOVA`;
* `criadaEm` com a data e hora da criação.

Essas regras fazem parte do contrato do desafio.

## ✅ Conclusão de tarefas

Quando uma tarefa tem seu status alterado para:

```text
CONCLUIDA
```

o sistema deve preencher automaticamente o campo:

```text
concluidaEm
```

## ❌ Tratamento de erros

Quando uma tarefa ou projeto não existe, a API deve retornar:

```http
404 Not Found
```

em vez de um erro interno `500`.

Exemplo:

```http
GET /tarefas/999
```

Resposta:

```json
{
  "erro": "Tarefa nao encontrada: 999",
  "timestamp": "2026-09-01T14:31:02Z"
}
```

A resposta deve ser consistente e não retorna a stack trace da aplicação.


---

