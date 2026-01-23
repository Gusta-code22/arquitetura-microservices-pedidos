# Sistema de Microsserviços com Spring Boot e Spring Cloud

## 📌 Visão Geral

Este projeto demonstra a implementação de uma **arquitetura de microsserviços** utilizando **Spring Boot**, **Spring Data JPA**, **Spring Cloud**, **OpenFeign**, **Eureka Server** e **Spring Cloud Gateway**.

A aplicação evoluiu para um cenário mais próximo de produção, incorporando:

* **Service Registry com Eureka Server**
* **API Gateway** para **roteamento centralizado**, **service discovery** e **load balancing**
* Comunicação síncrona entre microsserviços
* Separação clara de responsabilidades
* Boas práticas para sistemas distribuídos

O sistema simula um fluxo real de negócio envolvendo **Produto**, **Preço** e **Imposto**.

---

## 🧱 Arquitetura

A arquitetura segue um modelo clássico de microsserviços com **Service Discovery** e **Gateway**:

```
Cliente
   │
   ▼
API Gateway (Spring Cloud Gateway)
   │
   ▼
Service Registry (Eureka Server)
   │
   ├── Produto Service
   │        │ (Feign + Load Balancer)
   │        ▼
   ├── Preço Service
   │        │ (Feign + Load Balancer)
   │        ▼
   └── Imposto Service
```

### Principais características da arquitetura:

* O **cliente acessa apenas o API Gateway**
* O Gateway resolve as rotas dinamicamente via **Eureka Server**
* Os microsserviços **não conhecem endereços físicos (host/porta)** uns dos outros
* O **load balancing é feito automaticamente** pelo Spring Cloud

---

## 🧩 Componentes do Sistema

### 🟣 Eureka Server (Service Registry)

* **Responsabilidade:** Registrar e gerenciar todos os microsserviços
* **Porta:** `8431`
* **Função:**

  * Centralizar o registro de instâncias
  * Permitir **service discovery dinâmico**
  * Base para o load balancing

Acesso ao dashboard:

```
http://localhost:8431
```

---

### 🟡 API Gateway

* **Tecnologia:** Spring Cloud Gateway (WebFlux)
* **Porta:** `8900`
* **Responsabilidade:**

  * Roteamento centralizado
  * Integração com Eureka Server
  * Load balancing automático

Exemplo de configuração baseada em service discovery:

* Roteamento dinâmico via `spring.cloud.gateway.discovery.locator.enabled=true`
* URLs baseadas no **nome do serviço registrado no Eureka**

Exemplo de acesso:

```
GET http://localhost:8765/service-produto/produto/3/BRL
```

---

### 🟢 Produto Service

* **Responsabilidade:** Orquestrar o fluxo principal do sistema
* **Porta:** `8001`
* **Função:**

  * Consultar o **banco de dados SQL** para obter o produto
  * Gerenciar persistência com **Spring Data JPA**
  * Versionar o banco com **Flyway**
  * Orquestrar chamadas para o Preço Service via Feign

Fluxo interno:

1. Recebe o `id` do produto e a `moeda`
2. Busca o produto no banco de dados
3. Envia o valor base e a moeda para o Preço Service
4. Retorna o produto com valores calculados

Endpoint interno:

```http
GET /service-produto/{id}/{moeda}
```

---

### 🔵 Preço Service

* **Responsabilidade:** Calcular o preço final do produto
* **Porta:** `8002`
* **Função:**

  * Receber o valor base
  * Consultar o Imposto Service
  * Compor o valor final

---

### 🟠 Imposto Service

* **Responsabilidade:** Calcular impostos com base no valor e na moeda
* **Porta:** `8003`
* **Função:**

  * Aplicar regras fiscais
  * Validar moedas suportadas (BRL, USD, EUR)

---

## 🔗 Comunicação entre Serviços

A comunicação entre microsserviços é realizada com **Spring Cloud OpenFeign**, totalmente integrada ao **Eureka Server**:

* Os serviços se comunicam usando apenas o **nome lógico**
* O **load balancing** é feito automaticamente
* Nenhuma URL fixa é necessária

Exemplo:

```java
@FeignClient(name = "service-imposto")
public interface ImpostoProxy {

    @GetMapping("/service-imposto/{valor}/{moeda}")
    BigDecimal calcularImposto(@PathVariable BigDecimal valor,
                               @PathVariable String moeda);
}
```

---

## ⚠️ Tratamento de Erros

O projeto demonstra cenários comuns em sistemas distribuídos:

* Erros de validação retornam **HTTP 400**
* Falta de tratamento adequado no Feign pode resultar em **HTTP 500**

Esse comportamento reforça a importância de:

* Validação consistente
* Tratamento global de exceções
* Padronização de respostas de erro

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot**
* **Spring Web / WebFlux**
* **Spring Cloud Gateway**
* **Spring Cloud Netflix Eureka**
* **Spring Cloud OpenFeign**
* **Spring Data JPA**
* **Flyway**
* **Banco de Dados SQL (MySQL)**
* **Maven**
* **Git & GitHub**

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

* Java 21+
* Maven
* Git

### Ordem de Inicialização

```bash
# 1. Eureka Server
mvn spring-boot:run

# 2. API Gateway
mvn spring-boot:run

# 3. Microsserviços
mvn spring-boot:run
```

A ordem correta é essencial para o registro no Eureka.

---

## 🧪 Exemplo de Requisição via Gateway

```http
GET http://localhost:8765/service-produto/produto/3/BRL
```

Resposta esperada:

```json
{
  "produtoId": 3,
  "nome": "Headset Surround 7.1 USB",
  "moeda": "BRL",
  "valorBase": 289.5,
  "valorFinal": 324.24
}
```

---

## 📚 Objetivo Educacional

Este projeto foi desenvolvido com foco em **aprendizado prático**, abordando:

* Arquitetura de microsserviços
* Service Discovery com Eureka
* API Gateway e roteamento
* Load balancing
* Comunicação entre serviços
* Boas práticas com Spring Cloud

---

## ✍️ Autor

**Gustavo Miranda Brito**
GitHub: [Gusta-code22](https://github.com/Gusta-code22)

---

## 📄 Licença

Este projeto é livre para fins educacionais e de estudo.
