# 🚀 Mottu Auth API - Sistema de Autenticação

## 📋 Descrição do Projeto

**Mottu Auth API** é uma aplicação web completa desenvolvida em **Spring Boot** para demonstrar um sistema robusto de autenticação e autorização. Este projeto foi desenvolvido para a **Sprint 3 - Java Advanced** da FIAP, implementando as melhores práticas de desenvolvimento e segurança.

### 🎯 Objetivos da Sprint 3

- ✅ **Thymeleaf**: Camada de visualização com fragmentos reutilizáveis
- ✅ **Flyway**: Controle de versões do banco de dados
- ✅ **Spring Security**: Autenticação e controle de acesso
- ✅ **Funcionalidades Completas**: Fluxos de autenticação e gerenciamento de usuários

## 🏗️ Arquitetura da Aplicação

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend       │    │   Database      │
│   (Thymeleaf)   │◄──►│  (Spring Boot)  │◄──►│   (Oracle)      │
│                 │    │                 │    │                 │
│ • Login         │    │ • Controllers   │    │ • Usuários      │
│ • Dashboard     │    │ • Services      │    │ • Perfis        │
│ • Admin         │    │ • Security      │    │ • Auditoria     │
│ • Cadastro      │    │ • JWT           │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework principal
- **Spring Security 6.2.0** - Segurança e autenticação
- **Spring Data JPA** - Persistência de dados
- **Spring Boot Thymeleaf** - Template engine

### Frontend
- **Thymeleaf** - Template engine server-side
- **Bootstrap 5.3.0** - Framework CSS responsivo
- **Font Awesome 6.0.0** - Ícones
- **JavaScript ES6+** - Interatividade

### Banco de Dados
- **Oracle Database 19.3** - Banco de dados principal
- **Flyway 10.8.0** - Migração e versionamento
- **HikariCP** - Connection pooling

### Segurança
- **JWT (JSON Web Tokens)** - Autenticação stateless
- **BCrypt** - Criptografia de senhas
- **Spring Security** - Controle de acesso

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/fiap/mottu/
│   │   ├── config/           # Configurações (Security, Web)
│   │   ├── controller/       # Controladores REST e Web
│   │   ├── dto/             # Data Transfer Objects
│   │   ├── entity/          # Entidades JPA
│   │   ├── repository/      # Repositórios de dados
│   │   ├── security/        # Filtros e configurações de segurança
│   │   ├── service/         # Lógica de negócio
│   │   └── MottuAuthApplication.java
│   ├── resources/
│   │   ├── db/migration/    # Scripts Flyway
│   │   ├── templates/       # Templates Thymeleaf
│   │   │   ├── fragments/   # Fragmentos reutilizáveis
│   │   │   ├── home.html    # Página inicial
│   │   │   ├── login.html   # Formulário de login
│   │   │   ├── cadastro.html # Formulário de cadastro
│   │   │   ├── dashboard.html # Dashboard do usuário
│   │   │   └── admin.html   # Painel administrativo
│   │   └── application.yml  # Configurações da aplicação
│   └── test/                # Testes unitários
```

## 🚀 Como Executar a Aplicação

### Pré-requisitos

- **Java 17** ou superior
- **Maven 3.6+**
- **Oracle Database 19.3** (ou superior)
- **Git** para clonar o repositório

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/Sprint3-Java.git
cd Sprint3-Java
```

### 2. Configuração do Banco de Dados

#### 2.1 Acesso ao Oracle FIAP
A aplicação está configurada para usar o banco Oracle da FIAP:
- **Host**: `oracle.fiap.com.br`
- **Porta**: `1521`
- **SID**: `ORCL`
- **Usuário**: `rm558935`
- **Senha**: `310805`

#### 2.2 Configuração Local (Opcional)
Se quiser usar um banco local, edite o `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@localhost:1521:XE
    username: seu_usuario
    password: sua_senha
    driver-class-name: oracle.jdbc.OracleDriver
```

### 3. Executar a Aplicação

#### 3.1 Via Maven
```bash
mvn clean install
mvn spring-boot:run
```

#### 3.2 Via IDE
- Abra o projeto no IntelliJ IDEA ou Eclipse
- Execute a classe `MottuAuthApplication`
- A aplicação estará disponível em `http://localhost:8080`

### 4. Acessar a Aplicação

- **URL Principal**: http://localhost:8080
- **Login**: http://localhost:8080/login
- **Cadastro**: http://localhost:8080/cadastro
- **Dashboard**: http://localhost:8080/dashboard (após login)
- **Admin**: http://localhost:8080/admin (apenas para administradores)

## 👥 Usuários de Teste

### Administrador
- **Email**: `admin@mottu.com`
- **Senha**: `admin123`
- **Perfil**: `ADMIN`
- **Acesso**: Todas as funcionalidades

### Usuário Padrão
- **Email**: `user@mottu.com`
- **Senha**: `user123`
- **Perfil**: `USUARIO`
- **Acesso**: Funcionalidades básicas

## 🔐 Funcionalidades de Segurança

### Autenticação JWT
- **Token**: JSON Web Token com expiração de 2 meses (60 dias)
- **Armazenamento**: LocalStorage do navegador
- **Validação**: Automática em todas as requisições API

### Controle de Acesso
- **Rotas Públicas**: Home, Login, Cadastro
- **Rotas Protegidas**: Dashboard, Admin, APIs
- **Perfis**: ADMIN e USUARIO com permissões diferentes

### Criptografia
- **Senhas**: Criptografadas com BCrypt
- **Tokens**: Assinados com chave secreta
- **Comunicação**: HTTPS recomendado para produção

### Configuração de Expiração JWT
- **Duração**: 2 meses (60 dias)
- **Cálculo**: 60 dias × 24 horas × 60 minutos × 60 segundos × 1000 milissegundos = 5.184.000.000 ms
- **Vantagem**: Menos necessidade de relogin para usuários ativos
- **Segurança**: Tokens ainda são validados a cada requisição

## 🗄️ Migrações do Banco de Dados

### Versões Flyway Implementadas

#### V1 - Criação da Tabela de Usuários
- Tabela `MOTTU_USUARIOS_SISTEMA`
- Campos: ID, Nome, Email, Senha, Perfil, Status, Datas
- Índices para performance
- Comentários para documentação

#### V2 - Usuário Administrador
- Inserção do usuário admin padrão
- Credenciais: admin@mottu.com / admin123
- Perfil de acesso ADMIN

#### V3 - Usuário de Teste
- Inserção do usuário padrão para testes
- Credenciais: user@mottu.com / user123
- Perfil de acesso USUARIO

#### V4 - Trigger de Auditoria
- Trigger para atualização automática de timestamps
- Campo `DATA_ATUALIZACAO` atualizado automaticamente
- Auditoria de mudanças nos registros

### Executar Migrações

```bash
# Verificar status das migrações
mvn flyway:info

# Executar migrações pendentes
mvn flyway:migrate

# Reparar migrações com falha
mvn flyway:repair

# Limpar banco (cuidado!)
mvn flyway:clean
```

## 🎨 Interface do Usuário

### Páginas Principais

#### Home (`/`)
- Apresentação do sistema
- Links para login e cadastro
- Informações sobre funcionalidades

#### Login (`/login`)
- Formulário de autenticação
- Validação de credenciais
- Redirecionamento baseado no perfil
- Credenciais de teste exibidas

#### Cadastro (`/cadastro`)
- Formulário de registro
- Validação de dados
- Criptografia automática de senha
- Redirecionamento após sucesso

#### Dashboard (`/dashboard`)
- Informações do usuário logado
- Estatísticas da conta
- Ações rápidas (atualizar perfil, ver token)
- Navegação para outras áreas

#### Admin (`/admin`)
- Painel administrativo
- Gerenciamento de usuários
- Estatísticas do sistema
- Acesso restrito a administradores

### Fragmentos Reutilizáveis

- **Header**: Navegação principal com menu responsivo
- **Footer**: Informações da aplicação e links úteis
- **Layout**: Estrutura base para todas as páginas

## 🔧 Configurações

### application.yml
```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
    username: 
    password: 
  
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
  
  flyway:
    enabled: false  # Temporariamente desabilitado
    baseline-on-migrate: true

server:
  port: 8080

jwt:
  secret: mottuSecretKey2024Sprint3JavaAdvancedFIAP
  expiration: 5184000000  # 2 meses (60 dias)
```

### Logs
- **Spring Security**: DEBUG
- **Aplicação**: DEBUG
- **Flyway**: INFO
- **Hibernate**: SQL queries exibidas

## 🧪 Testes

### Executar Testes
```bash
# Testes unitários
mvn test

# Testes de integração
mvn verify

# Cobertura de código
mvn jacoco:report
```

### Estrutura de Testes
- **MottuAuthApplicationTests**: Teste de contexto da aplicação
- **Testes de Segurança**: Validação de rotas protegidas
- **Testes de Serviços**: Lógica de negócio
- **Testes de Repositórios**: Acesso a dados

## 📊 Monitoramento e Logs

### Endpoints de Monitoramento
- **Health Check**: `/actuator/health`
- **Info**: `/actuator/info`
- **Metrics**: `/actuator/metrics`

### Logs Principais
- **Aplicação**: Inicialização e operação
- **Segurança**: Tentativas de login e acesso
- **Banco**: Queries SQL e conexões
- **Flyway**: Status das migrações

## 🚨 Troubleshooting

### Problemas Comuns

#### 1. Erro de Conexão com Banco
```
Caused by: java.sql.SQLException: ORA-12541: TNS:no listener
```
**Solução**: Verificar se o Oracle está rodando e acessível

#### 2. Erro de Migração Flyway
```
Detected failed migration to version 1
```
**Solução**: Executar `mvn flyway:repair`

#### 3. Erro de Autenticação
```
Access Denied - HTTP ERROR 403
```
**Solução**: Verificar se o usuário está logado e tem permissão

#### 4. Erro de Bean Circular
```
Circular dependency detected
```
**Solução**: Verificar configurações de dependências

### Logs de Debug
Para debug detalhado, adicione ao `application.yml`:
```yaml
logging:
  level:
    com.fiap.mottu: DEBUG
    org.springframework.security: DEBUG
    org.flywaydb: DEBUG
```

## 🔄 Deploy e Produção

### Preparação para Produção
1. **Configurações de Segurança**
   - Alterar chave JWT secreta
   - Configurar HTTPS
   - Definir políticas de senha

2. **Banco de Dados**
   - Usar credenciais de produção
   - Configurar backup automático
   - Monitorar performance

3. **Logs e Monitoramento**
   - Configurar log rotation
   - Implementar alertas
   - Monitorar métricas de uso

### Docker
```dockerfile
# Multi-stage build para otimizar o tamanho da imagem final
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .mvn .mvn mvnw .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup
WORKDIR /app
RUN apk add --no-cache libc6-compat
COPY --from=build /app/target/mottu-auth-api-1.0.0.jar app.jar
RUN mkdir -p /app/logs && chown -R appuser:appgroup /app
USER appuser
EXPOSE 8080
ENV JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -XX:+UseContainerSupport"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
```

#### Executar com Docker
```bash
# Build da imagem
docker build -t mottu-auth-api .

# Executar container
docker run -p 8080:8080 mottu-auth-api

# Com docker-compose
docker-compose up -d
```

## 🚀 Deploy e Produção

### Deploy no Render
Para fazer deploy da aplicação no Render (plataforma de cloud gratuita):

1. **Conecte seu repositório** no Render
2. **Configure como Web Service** com runtime Docker
3. **Configure variáveis de ambiente** (banco, JWT, etc.)
4. **Deploy automático** a cada push

📖 **Guia completo**: Consulte [RENDER_DEPLOY.md](./RENDER_DEPLOY.md)

### Deploy Local com Docker
```bash
# Build e execução
docker build -t mottu-auth-api .
docker run -p 8080:8080 mottu-auth-api

# Com docker-compose (recomendado)
docker-compose up -d
```

### Configurações de Produção
- **Perfil**: `prod` (application-prod.yml)
- **Logging**: Otimizado para produção
- **Health Checks**: Endpoints de monitoramento
- **Segurança**: Usuário não-root, HTTPS

## 📚 Documentação Adicional

### APIs REST
- **POST** `/api/auth/cadastro` - Cadastro de usuário
- **POST** `/api/auth/login` - Autenticação
- **GET** `/api/auth/perfil` - Perfil do usuário (autenticado)

### Segurança
- **JWT**: Implementação completa
- **BCrypt**: Criptografia de senhas
- **Spring Security**: Configuração avançada
- **CORS**: Configurado para desenvolvimento

### Banco de Dados
- **Oracle**: Configuração e conexão
- **Flyway**: Migrações e versionamento
- **JPA/Hibernate**: Mapeamento de entidades
- **HikariCP**: Connection pooling


### Padrões de Código
- **Java**: Seguir convenções Java
- **Spring**: Usar anotações padrão
- **HTML**: Semântico e acessível
- **CSS**: Responsivo e organizado
- **JavaScript**: ES6+ com async/await

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais na **FIAP** como parte da **Sprint 3 - Java Advanced**.

## 👨‍💻 Desenvolvedor

- **Nome**: Lu Vieira Santos
- **RA**: 558935
- **Curso**: ADS
- **FIAP**: Faculdade de Informática e Administração Paulista

## 📞 Suporte

Para dúvidas ou problemas:
- **Email**: [henrique3.terceiro@gmail.com]
- **Issues**: Use a aba Issues do GitHub
- **Documentação**: Consulte este README

---

**Mottu Auth API** - Sistema de autenticação robusto e seguro desenvolvido com as melhores práticas de desenvolvimento Java e Spring Boot. 🚀
