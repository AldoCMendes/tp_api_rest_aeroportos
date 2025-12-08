# API REST — Gerenciamento de Aeroportos

## Objetivo
- Construir uma API REST para gerenciar aeroportos (listar, buscar por IATA, criar, atualizar e deletar) baseada no dataset público OpenFlights.
- Popular o banco com o CSV `airports.csv` do OpenFlights por URL configurável.

## Tecnologias Utilizadas
- Java 17+
- Spring Boot 3 (Web, Data JPA, Validation)
- Banco H2 (memória) para desenvolvimento e testes
- Maven (Surefire para testes de unidade, Failsafe para integração)

## Configuração do Ambiente
- Requisitos:
  - JDK 17+ (`java -version` deve retornar 17 ou superior)
  - Maven instalado (`mvn -v`).
  - MySQL 8+ com uma base criada, por exemplo `aeroportosdb`.
    - Crie com: `CREATE DATABASE aeroportosdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;`
    - Ajuste usuário/senha via variáveis de ambiente `DB_USERNAME` e `DB_PASSWORD`.

## Como Executar a Aplicação
- Configuração padrão usa MySQL local (`localhost:3306/aeroportosdb`) e importa dados do CSV ao iniciar.
- Com Maven instalado:
  - `mvn spring-boot:run`
  - Endpoints em `http://localhost:8080/api/v1/aeroportos`

## Endpoints
- `GET /api/v1/aeroportos` — lista todos
- `GET /api/v1/aeroportos/{iata}` — busca por código IATA
- `POST /api/v1/aeroportos` — cria novo aeroporto
- `PUT /api/v1/aeroportos/{iata}` — atualiza aeroporto existente
- `DELETE /api/v1/aeroportos/{iata}` — remove aeroporto

## Importação de Dados
- Controlada por propriedades em `src/main/resources/application.properties`:
  - `app.import.csv.enabled=true`
  - `app.import.csv.url=...`
- Em testes de integração o import é desativado (`application-test.properties`).

## Como Executar os Testes
- Testes de unidade: `mvn -q test`
- Testes de integração: `mvn -q verify` (roda Surefire e Failsafe)

### Cobertura dos Testes
- Unidade:
  - Conversão de pés para metros (`AltitudeConverter`)
  - Mapeamento de país para ISO (`CountryIsoMapper`)
  - Validações e exceções de serviço (`AeroportoService`)
- Integração:
  - Fluxo completo dos endpoints: `POST`, `GET`, `PUT`, `DELETE`, e `GET` após `DELETE` (404)

## Padrão de Commits
- Convenção: Conventional Commits
- Exemplos:
  - `feat: inicializa projeto Spring Boot com Maven`
  - `feat(api): implementa controlador REST e DTOs de aeroportos`
  - `test(integration): cobre fluxo completo dos endpoints REST`

## Observações
- Banco é H2 em memória por padrão; ajuste para banco real se necessário via propriedades.
- O importador ignora linhas sem IATA válido.
