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
