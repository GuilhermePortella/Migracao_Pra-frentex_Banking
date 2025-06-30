# Pra-frentex_Banking

Este projeto é uma aplicação Spring Boot para demonstração de funcionalidades, a ideia é simular uma modernização de um projeto já existem de um banco, nessa nova versão iremos atualizar a arquitetura do projeto, melhorar diversos pontos e implementar novos recursos deixando o projeto moderno e usando diversas tecnologias muito utilizadas em ambientes de produção e sistemas com grande fluxo de usuários, integrando diversas tecnologias para autenticação, segurança, mensageria, IA, documentação, monitoramento e mais.

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

## Como rodar

1. **Build:**  
   ```sh
   ./mvnw clean package
   ```

2. **Executar:**  
   ```sh
   ./mvnw spring-boot:run
   ```

3. **Testes:**  
   ```sh
   ./mvnw test
   ```

## Observações

- O projeto utiliza Java 17.
- Para funcionalidades de IA, Redis e Kafka, é recomendado rodar os serviços via Docker Compose.
- O monitoramento pode ser integrado ao Dynatrace.
- A documentação da API pode ser gerada automaticamente via Spring REST Docs.

---

## Mais informações

Consulte o [`pom.xml`](pom.xml) para detalhes completos das