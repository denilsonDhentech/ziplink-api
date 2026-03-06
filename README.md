# ZipLink API 🔗

O **ZipLink** é um encurtador de URLs de alta performance desenvolvido com **Spring Boot 4.0.3**. O projeto foca em segurança de concorrência, arquitetura limpa e uma experiência de desenvolvedor superior através de documentação automatizada e tratamento de erros semântico.

---
## 📋 Sumário

1. [Sobre o Projeto](#ziplink-api-)
2. [Como Rodar o Projeto](#-1-como-rodar-o-projeto)
    * [Pré-requisitos](#pré-requisitos)
    * [Passo a Passo](#passo-a-passo)
3. [Escolhas de Design](#-2-escolhas-de-design)
    * [Arquitetura e Padrões](#arquitetura-e-padrões)
    * [Experiência do Usuário (Dev Experience)](#experiência-do-usuário-dev-experience)
4. [Evoluções Futuras](#-3-o-que-faria-diferente-com-mais-tempo)
5. [Autor](#desenvolvido-por-dhensouza)
---

## 🚀 1. Como Rodar o Projeto

### Pré-requisitos
* **Java 21** ou superior.
* **Maven 3.9+**.
* **Banco de Dados:** Por padrão, o projeto utiliza **H2 Database** (em memória) para facilitar o teste imediato, mas está preparado para PostgreSQL.

### Passo a Passo
1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/DhenSouza/ziplink-api.git](https://github.com/DhenSouza/ziplink-api.git)
   cd ziplink-api
   ```
2. **Compile e rode os testes:**
    ``` 
   ./mvnw clean test
   ```
3. **Inicie a aplicação:**
   ```
   ./mvnw spring-boot:run
   ```
4. Acesse a API:
    - A API estará disponível em: http://localhost:8080
    - Documentação Swagger: http://localhost:8080/swagger-ui/index.html
---
### 🔐 Autenticação e Acesso
A API utiliza **OAuth2 com JWT (JSON Web Tokens)** assinado via chaves **RSA (2048 bits)**.

1. **Obter Token:** Realize um `POST` para `/api/auth/login` com o corpo:
   ```json
   {
     "username": "admin",
     "password": "admin123"
   }
---
## 🏗️ 2. Escolhas de Design

### Arquitetura e Padrões
- **Clean Architecture:** Divisão clara entre Domain, Application e Infrastructure. Isso garante que as regras de negócio não dependam de frameworks externos.

- **TDD (Test Driven Development):** O desenvolvimento foi guiado por testes, cobrindo desde a lógica de domínio até testes de concorrência e integração de API.

- **Thread-Safety com ReentrantLock:** Para evitar que dois usuários criem o mesmo alias simultaneamente, implementamos um controle de concorrência granular no nível de serviço, garantindo a integridade dos dados sem sacrificar a performance.

### Experiência do Usuário (Dev Experience)
- **Global Exception Handler:** Centralização de erros com respostas JSON padronizadas.

- **Domain Error Enums:** Todas as mensagens de erro são centralizadas em Enums, facilitando a manutenção e futuras traduções.

- **SpringDoc OpenAPI 3:** Documentação viva que permite testar os endpoints diretamente pelo navegador, compatível com a versão mais recente do Spring.

---

## ⏳ 3. O que faria diferente com mais tempo

Apesar de sólido, um projeto sempre pode evoluir. Com mais tempo, eu implementaria:

1. **Camada de Cache (Redis):** O redirecionamento de URLs é uma operação de leitura intensa. Implementar cache para os códigos mais acessados reduziria drasticamente a carga no banco de dados.

2. **Observabilidade:** Adicionaria Prometheus e Grafana para monitorar métricas de saúde da API e latência de redirecionamento.

3. **Estatísticas de Acesso:** Criaria uma tabela de telemetria para rastrear quantos cliques cada link recebeu, de quais regiões e dispositivos.

4. **Dockerização:** Criaria um Dockerfile e um docker-compose.yml para subir a aplicação, o banco de dados e o Redis em um único comando.

5. **Autenticação:** Refinar o sistema de login através da implementação de **ROLES** específicas, permitindo diferentes níveis de permissão e acesso granular aos recursos da API.

---
# Desenvolvido por DhenSouza