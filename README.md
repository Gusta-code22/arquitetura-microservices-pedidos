# Sistema de Microsserviços com Spring Boot e Spring Cloud

## 📌 Visão Geral

Este projeto demonstra a implementação de uma **arquitetura de microsserviços** utilizando **Spring Boot**, **Spring Cloud** e **OpenFeign**, com comunicação síncrona entre serviços, separação clara de responsabilidades e foco em boas práticas para projetos distribuídos.

O sistema é composto por serviços independentes que se comunicam via HTTP, simulando um fluxo real de negócio envolvendo **Produto**, **Preço** e **Imposto**.

---

## 🧱 Arquitetura

A arquitetura segue o padrão clássico de microsserviços:

```
Cliente
   │
   ▼
Produto Service
   │ (Feign)
   ▼
Preço Service
   │ (Feign)
   ▼
Imposto Service
```

Cada serviço possui:

* Porta dedicada
* Responsabilidade única
* Comunicação via REST
* Contratos bem definidos

---

## 🧩 Microsserviços

### 🟢 Produto Service

* **Responsabilidade:** Orquestrar o fluxo e expor o endpoint principal ao cliente
* **Porta:** `8001`
* **Função:** Solicita o cálculo de preço ao serviço de Preço

Endpoint de exemplo:

```http
GET /produto/{id}/{moeda}
```

---

### 🔵 Preço Service

* **Responsabilidade:** Calcular o preço final do produto
* **Porta:** `8002`
* **Função:** Integra-se com o serviço de Imposto para compor o valor final

---

### 🟠 Imposto Service

* **Responsabilidade:** Calcular imposto com base no valor e na moeda (BRL, USD, EUR)
* **Porta:** `8003`
* **Função:** Aplicar regras fiscais e validações

---

## 🔗 Comunicação entre Serviços

A comunicação é feita utilizando **Spring Cloud OpenFeign**, permitindo chamadas HTTP de forma declarativa:

* Menos código boilerplate
* Integração nativa com Spring
* Tratamento de erros via exceções

Exemplo conceitual:

```java
@FeignClient(name = "imposto-service")
public interface ImpostoProxy {
    @GetMapping("/imposto/{valor}/{moeda}")
    BigDecimal calcularImposto(@PathVariable BigDecimal valor,
                               @PathVariable String moeda);
}
```

---

## ⚠️ Tratamento de Erros

O projeto demonstra um comportamento comum em sistemas distribuídos:

* Erros de validação (ex: moeda inválida) retornam **HTTP 400**
* Falta de tratamento no Feign resulta em **HTTP 500** no serviço chamador

Este cenário evidencia a importância de:

* Validação de entrada
* Tratamento de exceções
* Padronização de respostas de erro

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot**
* **Spring Cloud OpenFeign**
* **Spring Web (REST)**
* **Maven**
* **Git & GitHub**

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

* Java 21+
* Maven
* Git

### Passos

```bash
# Clonar o repositório
git clone https://github.com/Gusta-code22/microservices-spring-cloud-and-java-erudio.git

# Entrar em cada serviço e iniciar
mvn spring-boot:run
```

Inicie os serviços respeitando as portas configuradas.

---

## 🧪 Exemplo de Requisição

```http
GET http://localhost:8001/produto/3/BRL
```

Resposta esperada:

```json
{
  "valorBase": 3899.90,
  "valorImposto": 467.99,
  "valorFinal": 4289.90
}
```

---

## 📚 Objetivo Educacional

Este projeto foi desenvolvido com foco em **aprendizado prático**, abordando:

* Comunicação entre microsserviços
* Uso real de Feign
* Propagação de erros
* Organização de camadas
* Boas práticas com Spring Cloud

---

## ✍️ Autor

**Gustavo Miranda Brito**
GitHub: [Gusta-code22](https://github.com/Gusta-code22)

---

## 📄 Licença

Este projeto é livre para fins educacionais e de estudo.
