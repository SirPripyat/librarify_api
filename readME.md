# Librarify

Librarify é um sistema de gerenciamento de biblioteca desenvolvido com Spring Boot. Este projeto inclui APIs RESTful 
para gerenciar livros, membros e empréstimos, utilizando PostgreSQL como banco de dados.

## Índice

- [Pré-requisitos](#pré-requisitos)
- [Configuração e Instalação](#configuração-e-instalação)
- [Executando a Aplicação](#executando-a-aplicação)
- [Executando os Testes](#executando-os-testes)
- [Visualizando o Relatório de Cobertura de Código](#visualizando-o-relatório-de-cobertura-de-código)

## Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- Java JDK 22 ou superior
- Maven 3.8 ou superior
- PostgreSQL (Remoto ou Local)
- Git

## Configuração e Instalação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/SirPripyat/librarify_api.git
   cd librarify_api
   
2. **Configure o banco de dados:**
A aplicação utiliza PostgreSQL. Utilize as credenciais abaixo para conectar no banco de dados da AWS:
**Configure o banco de dados:**

   ```bash
   HOST: jdbc:postgresql://librarify.cpigmacucmaw.us-east-2.rds.amazonaws.com:5432/postgres
   DATABASE: postgres
   USER: postgres
   PASSWORD: dijsEj-macbax-4zyjxa

## Executando a Aplicação
Você pode rodar a aplicação utilizando Maven:

    mvn spring-boot:run
A aplicação estará disponível em `http://localhost:8080`.

## Executando os Testes
Para rodar os testes, execute o comando abaixo:

    mvn test

Esse comando executa os testes unitários utilizando JUnit e gera um relatório de cobertura de código utilizando o JaCoCo.

## Visualizando o Relatório de Cobertura de Código
Após rodar os testes, o relatório de cobertura de código do JaCoCo será gerado na seguinte pasta:
    
        target/site/jacoco/index.html

Para visualizar o relatório, abra o seguinte arquivo em um navegador.

1. Abra o arquivo `index.html`, que está localizado na raíz do repositório, em um navegador.

        target/site/jacoco/index.html    

O relatório exibirá informações detalhadas sobre a cobertura de código, incluindo classes, métodos e linhas cobertas.
