# 📅 Agenda de Tarefas

## 📖 Descrição do Projeto
Aplicação desenvolvida em **[Spring Boot](https://spring.io/projects/spring-boot)** com arquitetura de **API REST**, destinada à criação e gerenciamento de uma **agenda de tarefas** e **categorias**.  

Tecnologias e recursos utilizados:  
- **[PostgreSQL](https://www.postgresql.org/)** – Banco de dados relacional  
- Arquitetura limpa com separação em camadas  
- **[JDBC](https://docs.oracle.com/javase/tutorial/jdbc/)** para conexão com o banco de dados  
- **API REST** para padronização dos serviços  
- **[Swagger](https://swagger.io/)** para documentação da API  
- **[Lombok](https://projectlombok.org/)** para reduzir boilerplate no código  
- Configuração de **[CORS](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/CORS)** para permissões de acesso  

---

## 🏗️ Organização do Projeto
O projeto está estruturado nas seguintes camadas:  

- **Entities** → Classes de modelo de dados  
- **Repositories** → Implementação das operações SQL no banco  
- **Factories** → Implementação do padrão *Factory* para conexão com o PostgreSQL  
- **DTOs** → Classes para entrada (*request*) e saída (*response*) da API  
- **Configurations** → Configuração do Swagger e do CORS  
- **Controllers** → Serviços da API seguindo o padrão REST  
- **Enums** → Definição de tipos multivalorados  

---

## 🧩 Teoria: Arquitetura Limpa
A **[Arquitetura Limpa](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)**, proposta por Robert C. Martin (*Uncle Bob*), organiza o sistema em camadas concêntricas que separam **regras de negócio** de **detalhes de implementação**.  

Princípios básicos:  
- **Independência de frameworks** → O projeto não deve depender de detalhes de uma tecnologia específica.  
- **Testabilidade** → Facilita a escrita de testes unitários.  
- **Independência de UI** → A interface pode mudar sem afetar o núcleo do sistema.  
- **Independência de banco de dados** → Regras de negócio não devem depender do banco escolhido.  

No projeto, essa filosofia é aplicada com a divisão clara em **Entities, DTOs, Repositories, Controllers e Configurations**, facilitando manutenção e evolução.  

---

## 🌱 Teoria: Spring Boot
O **Spring Boot** é um framework baseado no **[Spring](https://spring.io/)** que simplifica a criação de aplicações Java.  
Principais vantagens:  
- **Configuração automática** (*auto-configuration*) → Reduz a necessidade de configuração manual.  
- **Servidor embutido** (Tomcat, Jetty, Undertow) → Permite rodar a aplicação sem necessidade de deploy em servidores externos.  
- **Integração com Spring Ecosystem** → Inclui segurança, dados, mensageria, entre outros.  
- **Produção pronta** (*production-ready*) → Com métricas, monitoramento e endpoints de saúde.  

Esse projeto utiliza o Spring Boot para expor uma **API RESTful**, integrando o PostgreSQL via JDBC e fornecendo documentação automática via Swagger.  
