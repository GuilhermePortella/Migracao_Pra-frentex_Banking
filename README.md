# Pra-frentex_Banking

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.x-orange.svg)
![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)

Este projeto é uma aplicação baseada em **Spring Boot** que simula a **modernização de um sistema bancário legado**. Ele demonstra a transição de uma arquitetura monolítica para uma abordagem moderna, resiliente e escalável, utilizando **Arquitetura Hexagonal**, **Domain-Driven Design (DDD)** e as melhores práticas de engenharia de software.

## 📜 Tabela de Conteúdos

- [Pra-frentex\_Banking](#pra-frentex_banking)
  - [📜 Tabela de Conteúdos](#-tabela-de-conteúdos)
  - [🎯 Objetivos e Pilares Arquiteturais](#-objetivos-e-pilares-arquiteturais)
  - [🏗️ Estrutura do Projeto (Arquitetura Hexagonal)](#️-estrutura-do-projeto-arquitetura-hexagonal)
  - [🛠️ Principais Tecnologias](#️-principais-tecnologias)
  - [🚀 Como Começar](#-como-começar)
    - [Pré-requisitos](#pré-requisitos)
    - [Executando a Aplicação](#executando-a-aplicação)
    - [Executando com Monitoramento Datadog](#executando-com-monitoramento-datadog)
      - [O que o Script Faz?](#o-que-o-script-faz)
  - [Como Rodar os Testes](#como-rodar-os-testes)
  - [Observações](#observações)
  - [Mais informações](#mais-informações)

## 🎯 Objetivos e Pilares Arquiteturais

O principal objetivo é criar uma base de aplicação robusta que sirva como referência para projetos de modernização, focando nos seguintes pilares:

- **Isolamento de Domínio:** Separar claramente as regras de negócio (`core domain`) da infraestrutura (frameworks, bancos de dados, serviços externos) usando **Arquitetura Hexagonal (Ports & Adapters)**.
- **Design Orientado a Domínio (DDD):** Modelar o software em torno do domínio de negócio, utilizando conceitos como *Bounded Contexts* e *Ubiquitous Language*.
- **Observabilidade Completa:** Integrar logs estruturados (JSON), métricas e tracing distribuído desde o início, utilizando o trio **Actuator, Micrometer e Datadog/Dynatrace**.
- **Segurança Robusta:** Implementar um ecossistema de segurança com **Spring Security** e **OAuth2**, preparando a aplicação para atuar como Authorization Server, Resource Server e Client.
- **Resiliência e Desacoplamento:** Utilizar **mensageria assíncrona** (Kafka) para criar sistemas reativos e tolerantes a falhas.
- **Testabilidade:** Garantir alta cobertura de testes (unitários, de integração e de contrato) facilitada pelo baixo acoplamento da arquitetura.
- **Cloud-Native Ready:** Construir uma aplicação modular e "containerizável", pronta para ser implantada em ambientes de nuvem como AWS, Azure ou GCP.

## 🏗️ Estrutura do Projeto (Arquitetura Hexagonal)

A estrutura de pacotes é organizada para refletir a separação de responsabilidades da Arquitetura Hexagonal, isolando o núcleo de negócio das tecnologias externas.

```
br/
└── backing/
    ├── domain/
    │   ├── model/      # Entidades, Agregados e Value Objects (código puro, sem frameworks)
    │   └── service/    # Interfaces de serviço do domínio (regras de negócio)
    │
    ├── application/
    │   ├── ports/
    │   │   ├── in/     # Portas de entrada (interfaces para os casos de uso)
    │   │   └── out/    # Portas de saída (interfaces que o domínio precisa para se comunicar com o exterior, ex: UserRepositoryPort)
    │   └── usecase/    # Implementação dos casos de uso (orquestra o domínio)
    │
    └── infrastructure/
        ├── adapters/
        │   ├── in/
        │   │   ├── rest/       # Adaptadores de entrada (Controladores REST)
        │   │   └── consumer/   # Adaptadores de entrada (Consumidores de Fila Kafka)
        │   └── out/
        │       ├── persistence/ # Adaptadores de saída (Implementação de repositórios com Spring Data)
        │       └── client/      # Adaptadores de saída (Clientes para outras APIs)
        └── config/             # Configuração do Spring (Beans, Segurança, etc.)
```

## 🛠️ Principais Tecnologias

Este projeto utiliza um conjunto moderno de tecnologias para construir uma aplicação completa e observável.

| Categoria         | Tecnologias                                                                                             |
| ----------------- | ------------------------------------------------------------------------------------------------------- |
| **Core Framework**  | `Java 21`, `Spring Boot 3`, `Lombok`                                                                    |
| **Web & API**       | `Spring Web`, `Spring Data REST`, `Spring HATEOAS`                                                        |
| **Segurança**       | `Spring Security`, `OAuth2` (Authorization Server, Client, Resource Server)                             |
| **Dados & Persistência** | `Spring Data` (Redis, LDAP), `Flyway` (Database Migration)                                              |
| **Mensageria**      | `Spring Kafka`, `Kafka Streams`                                                                         |
| **Observabilidade** | `Spring Boot Actuator`, `Micrometer` (Datadog, Dynatrace), `Logstash Logback Encoder`                     |
| **Inteligência Artificial** | `Spring AI` (Redis Vector Store)                                                                        |
| **Testes**          | `JUnit 5`, `Mockito`, `Testcontainers`, `Spring REST Docs`, `Spring Security Test`, `Spring Kafka Test` |

Para uma lista completa, consulte o arquivo `pom.xml`.

## 🚀 Como Começar

Siga os passos abaixo para compilar e executar o projeto localmente.

### Pré-requisitos

- **Java 21**
- **Apache Maven** 3.8+
- **Docker** e **Docker Compose** (recomendado para rodar dependências como Redis e Kafka)

### Executando a Aplicação

Você pode executar a aplicação de duas maneiras:

**1. Via Maven Wrapper (Ideal para desenvolvimento)**

Este comando compila e inicia a aplicação em um único passo.
```sh
./mvnw spring-boot:run
```

**2. Gerando o Pacote `.jar` (Simula o ambiente de produção)**

```sh
# 1. Compile e empacote o projeto (pulando os testes)
./mvnw clean package -DskipTests

# 2. Execute o arquivo JAR gerado
java -jar target/backing-0.0.1-SNAPSHOT.jar
```

### Executando com Monitoramento Datadog

Para uma execução com observabilidade completa (traces, métricas e logs), utilize o script `start-with-datadog.sh`.

**1. Configure sua Chave da API do Datadog**

O script utiliza uma variável de ambiente para carregar sua API Key de forma segura. Antes de executar, exporte a variável no seu terminal:

- **Linux / macOS:**
  ```sh
  export DATADOG_API_KEY='sua_chave_de_api_aqui'
  ```
- **Windows (PowerShell):**
  ```powershell
  $env:DATADOG_API_KEY="sua_chave_de_api_aqui"
  ```
> ⚠️ **Importante:** Substitua `sua_chave_de_api_aqui` pela sua chave real. **Não armazene a chave diretamente no script.**

**2. Dê Permissão de Execução ao Script (Apenas Linux/macOS)**

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

Consulte o [`pom.xml`](pom.xml) para detalhes completos das dependencias
