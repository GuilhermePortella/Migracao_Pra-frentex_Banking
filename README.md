# Pra-frentex_Banking

Este projeto é uma aplicação baseada em **Spring Boot**, desenvolvida com o objetivo de **simular a modernização de um sistema bancário legado**. Ele representa a transição de uma arquitetura tradicional em camadas para uma abordagem moderna, resiliente e escalável, utilizando boas práticas de engenharia de software e tecnologias amplamente adotadas no mercado.

## ⚙️ Objetivos da Modernização

- Reestruturar o sistema para **Arquitetura Hexagonal (Ports & Adapters)**, promovendo **baixo acoplamento** e **alta testabilidade**.
- Centralizar regras de negócio aplicando **Domain-Driven Design (DDD)** e princípios **SOLID**.
- Adotar **Spring Security** com base para futuras integrações (JWT, OAuth2).
- Promover **observabilidade** com logs estruturados, métricas, tracing e monitoramento (✔️ **Datadog**, **Micrometer**, **Actuator**).
- Preparar o sistema para **cloud-native** e **serverless (ex: AWS Lambda)** com foco em boas práticas **FinOps-friendly**.
- Utilizar **mensageria assíncrona** (SQS, Kafka) para desacoplamento e resiliência.

## 🔍 Tecnologias e Conceitos

- **Java 17+**, **Spring Boot 3+**
- **Arquitetura Hexagonal** (Ports & Adapters)
- **Domain-Driven Design**, **SOLID**, **Clean Architecture**
- **Validações customizadas** (ex: CPF, CNPJ)
- **Spring Security** com preparação para OAuth2 e JWT
- **Aspect-Oriented Programming (AOP)** para logging
- **Spring Boot Actuator**, **Micrometer**, **Datadog Java Agent**
- **Mensageria assíncrona** com suporte a SQS e Kafka
- **Modularização por contextos de negócio (Bounded Contexts)**

## Pilares da Modernização

- **Isolamento de domínios:** separação clara entre regras de negócio (core domain), infraestrutura e serviços externos.
- **Boas práticas arquiteturais:** aplicação de princípios SOLID, DDD, separação de responsabilidades e injeção de dependência.
- **Observabilidade:** logs estruturados, rastreamento de transações com AOP e integração com ferramentas como Datadog e Micrometer.
- **Resiliência:** arquitetura preparada para falhas externas (serviços downstream) com previsão de circuit breakers, retries e fallback.
- **Preparação para Cloud:** modularização e desacoplamento que permitem fácil containerização e execução em ambientes como AWS ou Azure.
- **Testabilidade:** maior cobertura de testes unitários e de integração graças ao desacoplamento por interfaces e uso de dependências mockáveis.
- **Código Limpo:** padronização de pacotes, validações declarativas, e logging coerente com princípios de legibilidade e manutenção.


## Principais Dependências

### Spring Boot Starters

- **spring-boot-starter-actuator**  
  Fornece endpoints para monitoramento e gerenciamento da aplicação (health, metrics, info, etc).

- **spring-boot-starter-data-ldap**  
  Suporte para integração com servidores LDAP, útil para autenticação e gerenciamento de usuários.

- **spring-boot-starter-data-redis**  
  Integração com Redis para armazenamento de dados em cache ou como banco de dados NoSQL.

- **spring-boot-starter-data-redis-reactive**  
  Suporte reativo para operações com Redis, permitindo programação assíncrona e não bloqueante.

- **spring-boot-starter-data-rest**  
  Facilita a exposição de repositórios Spring Data como endpoints REST automaticamente.

- **spring-boot-starter-hateoas**  
  Permite criar APIs RESTful seguindo o princípio HATEOAS (Hypermedia as the Engine of Application State).

- **spring-boot-starter-oauth2-authorization-server**  
  Implementa um servidor de autorização OAuth2, permitindo emissão de tokens de acesso.

- **spring-boot-starter-oauth2-client**  
  Permite que a aplicação atue como cliente OAuth2, consumindo recursos protegidos.

- **spring-boot-starter-oauth2-resource-server**  
  Permite proteger APIs usando OAuth2, validando tokens de acesso recebidos.

- **spring-boot-starter-security**  
  Adiciona autenticação e autorização à aplicação.

- **spring-boot-starter-validation**  
  Suporte à validação de dados usando Bean Validation (JSR-380).

- **spring-boot-starter-web**  
  Estrutura básica para aplicações web RESTful usando Spring MVC.

### Mensageria e Streaming

- **org.apache.kafka:kafka-streams**  
  Permite processamento de streams de dados em tempo real usando Apache Kafka.

- **org.springframework.kafka:spring-kafka**  
  Integração Spring para produção e consumo de mensagens Kafka.

### Banco de Dados e Migração

- **org.flywaydb:flyway-core**  
  Gerenciamento de versionamento e migração de banco de dados.

### Inteligência Artificial

- **org.springframework.ai:spring-ai-starter-vector-store-redis**  
  Integração com IA para armazenamento vetorial usando Redis.

### Cloud & Gateway

- **spring-cloud-starter-gateway-server-webflux**  
  API Gateway reativo para roteamento, balanceamento e filtros.

- **spring-cloud-starter-gateway-server-webmvc**  
  API Gateway baseado em Spring MVC.

### Observabilidade

- **io.micrometer:micrometer-registry-dynatrace**  
  Exporta métricas para o Dynatrace.

### Desenvolvimento

- **spring-boot-devtools**  
  Ferramentas para desenvolvimento, como reload automático.

- **spring-boot-docker-compose**  
  Integração com Docker Compose para facilitar o uso de containers em desenvolvimento.

- **spring-ai-spring-boot-docker-compose**  
  Suporte para rodar componentes de IA em containers via Docker Compose.

- **lombok**  
  Gera automaticamente código boilerplate (getters, setters, etc) via anotações.

### Testes

- **spring-boot-starter-test**  
  Dependências para testes unitários e de integração.

- **spring-boot-testcontainers**  
  Suporte para testes de integração usando containers (Testcontainers).

- **unboundid-ldapsdk**  
  SDK para testes com servidores LDAP.

- **reactor-test**  
  Utilitários para testar código reativo.

- **spring-ai-spring-boot-testcontainers**  
  Suporte para testar integrações de IA usando containers.

- **spring-cloud-starter-contract-stub-runner**  
  Testes de contratos (Consumer Driven Contracts).

- **spring-kafka-test**  
  Utilitários para testes com Kafka.

- **spring-restdocs-mockmvc**  
  Geração de documentação de APIs REST a partir de testes.

- **spring-security-test**  
  Utilitários para testar segurança.

- **testcontainers:junit-jupiter**  
  Integração do Testcontainers com JUnit 5.

- **testcontainers:kafka**  
  Containers Kafka para testes de integração.

---

## Como Executar o Projeto

Este projeto pode ser executado de duas maneiras principais: a forma padrão, ideal para desenvolvimento rápido, ou com a integração de monitoramento da Datadog.

### 1. Execução Padrão (Sem Datadog)

Use os comandos do Maven Wrapper (`mvnw`) para compilar e rodar a aplicação.

**Opção A: Comando Direto (Recomendado para desenvolvimento)**

Este comando compila e inicia a aplicação em um único passo.
```sh
./mvnw spring-boot:run
```

**Opção B: Compilando o Pacote `.jar`**

Este método é mais próximo de um ambiente de produção, onde você primeiro gera o artefato e depois o executa.

1.  **Compile e empacote o projeto (pulando os testes):**
    ```sh
    ./mvnw clean package -DskipTests
    ```
2.  **Execute o arquivo JAR gerado:**
    ```sh
    java -jar target/backing-0.0.1-SNAPSHOT.jar
    ```

### 2. Execução com Monitoramento Datadog

Para uma execução com observabilidade completa (traces, métricas e logs), utilize o script `start-with-datadog.sh`.

#### Pré-requisitos

*   **Java 17+** instalado.
*   **Conta na Datadog** e uma **API Key** válida.

#### Passo a Passo

**1. Configure sua Chave da API do Datadog**

O script utiliza uma variável de ambiente para carregar sua API Key de forma segura. Antes de executar, exporte a variável no seu terminal:

*   **No Linux ou macOS:**
    ```sh
    export DATADOG_API_KEY='sua_chave_de_api_aqui'
    ```
*   **No Windows (PowerShell):**
    ```powershell
    $env:DATADOG_API_KEY="sua_chave_de_api_aqui"
    ```
> ⚠️ **Importante:** Substitua `sua_chave_de_api_aqui` pela sua chave real. Não armazene a chave diretamente no script.

**2. Dê Permissão de Execução ao Script (Apenas Linux/macOS)**

Pode ser necessário tornar o script executável:
```sh
chmod +x start-with-datadog.sh
```

**3. Execute o Script**

Na raiz do projeto, execute:
```sh
./start-with-datadog.sh
```

#### O que o Script Faz?

1.  **Verifica a API Key**: Confere se a variável de ambiente `DATADOG_API_KEY` foi definida.
2.  **Compila o Projeto**: Executa `./mvnw clean install -DskipTests` para gerar o arquivo `.jar` na pasta `target`.
3.  **Baixa o Agente Datadog**: Verifica se o `dd-java-agent.jar` existe e, caso contrário, baixa a versão mais recente.
4.  **Inicia a Aplicação com o Agente**: Executa o `.jar` anexando o agente da Datadog (`-javaagent`), que instrumenta a aplicação para coletar e enviar dados de telemetria.

## Como Rodar os Testes

Para executar a suíte de testes unitários e de integração, utilize o comando:
```sh
./mvnw test
```

## Observações

- Para funcionalidades de IA, Redis e Kafka, é recomendado rodar os serviços via Docker Compose.
- O monitoramento pode ser integrado ao Dynatrace.
- A documentação da API pode ser gerada automaticamente via Spring REST Docs.

---

## Mais informações

Consulte o [`pom.xml`](pom.xml) para detalhes completos das