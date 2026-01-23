# Sistema de Microsserviços com Spring Boot, Spring Cloud e Swagger

## 📌 Visão Geral

Este projeto demonstra a implementação de uma **arquitetura de microsserviços** utilizando **Spring Boot**, **Spring Data JPA**, **Spring Cloud**, **OpenFeign**, **Eureka Server**, **Spring Cloud Gateway** e **Swagger/OpenAPI** para documentação das APIs.

A aplicação foi evoluída para um cenário mais próximo de produção, incorporando:

- **Service Registry com Eureka Server**
- **API Gateway** para roteamento centralizado, service discovery e load balancing
- Comunicação síncrona entre microsserviços
- Separação clara de responsabilidades
- Documentação automática das APIs com **Swagger UI**
- Boas práticas para sistemas distribuídos

O sistema simula um fluxo real de negócio envolvendo **Produto**, **Preço** e **Imposto**.

---

## 🧱 Arquitetura

A arquitetura segue um modelo clássico de microsserviços com **Service Discovery**, **Gateway** e **documentação desacoplada por serviço**:

Cliente
│
▼
API Gateway (Spring Cloud Gateway)
│
▼
Service Registry (Eureka Server)
│
├── Produto Service
│ │ (Feign + Load Balancer + Swagger)
│ ▼
├── Preço Service
│ │ (Feign + Load Balancer + Swagger)
│ ▼
└── Imposto Service

markdown
Copiar código

### Principais características da arquitetura

- O cliente consome os serviços preferencialmente via **API Gateway**
- O Gateway resolve as rotas dinamicamente via **Eureka Server**
- Os microsserviços não conhecem host/porta fixos
- O **load balancing** é automático
- Cada microsserviço possui **Swagger próprio**, acessível diretamente pela sua porta

---

## 🧩 Componentes do Sistema

### 🟣 Eureka Server (Service Registry)

- **Responsabilidade:** Registro e gerenciamento dos microsserviços  
- **Porta:** `8431`  

**Funções:**

- Centralizar o registro de instâncias
- Permitir service discovery dinâmico
- Servir de base para o load balancing

Dashboard:

http://localhost:8431

markdown
Copiar código

---

### 🟡 API Gateway

- **Tecnologia:** Spring Cloud Gateway (WebFlux)
- **Porta:** `8900`
- **Responsabilidade:**
  - Roteamento centralizado
  - Integração com Eureka Server
  - Load balancing automático

Configuração baseada em service discovery:

- `spring.cloud.gateway.discovery.locator.enabled=true`
- Rotas baseadas no nome lógico do serviço

Exemplo de acesso via Gateway:

```http
GET http://localhost:8900/service-produto/produto/3/BRL
🟢 Produto Service
Responsabilidade: Orquestrar o fluxo principal do sistema

Porta: 8001

Funções:

Consulta ao banco de dados SQL

Persistência com Spring Data JPA

Versionamento com Flyway

Comunicação com Preço Service via Feign

Exposição de endpoints documentados com Swagger

Endpoint principal:

http
Copiar código
GET /service-produto/{id}/{moeda}
Swagger – Produto Service
A documentação da API do Produto Service está disponível em:

bash
Copiar código
http://localhost:8001/swagger-ui.html
ou

bash
Copiar código
http://localhost:8001/swagger-ui/index.html
🔵 Preço Service
Responsabilidade: Calcular o preço final do produto

Porta: 8002

Funções:

Receber o valor base

Consultar o Imposto Service

Compor o valor final

Expor endpoints documentados com Swagger

Swagger – Preço Service
A documentação da API do Preço Service está disponível em:

bash
Copiar código
http://localhost:8002/swagger-ui.html
ou

bash
Copiar código
http://localhost:8002/swagger-ui/index.html
🟠 Imposto Service
Responsabilidade: Calcular impostos com base no valor e na moeda

Porta: 8003

Funções:

Aplicar regras fiscais

Validar moedas suportadas (BRL, USD, EUR)

🔗 Comunicação entre Serviços
A comunicação entre microsserviços é realizada com Spring Cloud OpenFeign, integrada ao Eureka Server:

Comunicação via nome lógico do serviço

Load balancing automático

Ausência de URLs físicas fixas

Exemplo:

java
Copiar código
@FeignClient(name = "service-imposto")
public interface ImpostoProxy {

    @GetMapping("/service-imposto/{valor}/{moeda}")
    BigDecimal calcularImposto(
        @PathVariable BigDecimal valor,
        @PathVariable String moeda
    );
}
⚠️ Tratamento de Erros
O projeto demonstra cenários comuns em sistemas distribuídos:

Erros de validação retornam HTTP 400

Falta de tratamento adequado no Feign pode resultar em HTTP 500

Boas práticas reforçadas:

Validação consistente

Tratamento global de exceções

Padronização de respostas de erro

🛠️ Tecnologias Utilizadas
Java 21

Spring Boot

Spring Web / WebFlux

Spring Cloud Gateway

Spring Cloud Netflix Eureka

Spring Cloud OpenFeign

Spring Data JPA

Flyway

Swagger / OpenAPI

Banco de Dados SQL (MySQL)

Maven

Git & GitHub

▶️ Como Executar o Projeto
Pré-requisitos
Java 21+

Maven

Git

Ordem de Inicialização
bash
Copiar código
# 1. Eureka Server
mvn spring-boot:run

# 2. API Gateway
mvn spring-boot:run

# 3. Microsserviços
mvn spring-boot:run
A ordem correta é essencial para o registro no Eureka Server.

🧪 Exemplo de Requisição via Gateway
http
Copiar código
GET http://localhost:8900/service-produto/produto/3/BRL
Resposta esperada:

json
Copiar código
{
  "produtoId": 3,
  "nome": "Headset Surround 7.1 USB",
  "moeda": "BRL",
  "valorBase": 289.5,
  "valorFinal": 324.24
}
📚 Objetivo Educacional
Este projeto foi desenvolvido com foco em aprendizado prático, abordando:

Arquitetura de microsserviços

Service Discovery com Eureka

API Gateway

Load balancing

Comunicação entre serviços

Documentação de APIs com Swagger

Boas práticas com Spring Cloud

✍️ Autor
Gustavo Miranda Brito
GitHub: Gusta-code22

📄 Licença
Projeto livre para fins educacionais e de estudo.
