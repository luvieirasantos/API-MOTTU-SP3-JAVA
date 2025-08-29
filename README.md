# Mottu Auth API

Sistema de autenticação completo desenvolvido para Sprint 3 - Java Advanced FIAP, utilizando Spring Boot, Spring Security, Thymeleaf e Flyway.

## 🚀 Funcionalidades

- **Autenticação JWT**: Sistema de login seguro com tokens JWT
- **Cadastro de Usuários**: Formulário completo com validações
- **Spring Security**: Controle de acesso baseado em perfis (ADMIN/USUARIO)
- **Thymeleaf**: Interface web responsiva e moderna
- **Flyway**: Controle de versão do banco de dados
- **Oracle Database**: Conectividade com banco Oracle
- **Validações**: Validações client-side e server-side

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf**
- **Flyway 10.7.1**
- **Oracle JDBC Driver**
- **JWT (JSON Web Tokens)**
- **Bootstrap 5.3.0**
- **Maven**

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Oracle Database (acesso ao banco FIAP)
- Conexão com internet para dependências

## 🔧 Configuração

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd Sprint3-Java
```

### 2. Configuração do Banco de Dados
O sistema está configurado para conectar ao banco Oracle da FIAP:
- **URL**: `oracle.fiap.com.br:1521:ORCL`
- **Usuário**: `rm558935`
- **Senha**: `310805`

### 3. Executar Migrações Flyway
```bash
mvn flyway:migrate
```

### 4. Executar a Aplicação
```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📱 Endpoints da API

### Autenticação
- `POST /api/auth/cadastro` - Cadastro de usuários
- `POST /api/auth/login` - Login de usuários
- `GET /api/auth/perfil` - Obter perfil do usuário logado

### Páginas Web
- `GET /` - Página inicial
- `GET /login` - Página de login
- `GET /cadastro` - Página de cadastro
- `GET /dashboard` - Dashboard do usuário (requer autenticação)
- `GET /admin` - Painel administrativo (requer perfil ADMIN)

## 👥 Usuários de Teste

### Administrador
- **Email**: `admin@mottu.com`
- **Senha**: `admin123`

### Usuário Comum
- **Email**: `user@mottu.com`
- **Senha**: `user123`

## 🗄️ Estrutura do Banco de Dados

### Tabela: MOTTU_USUARIOS_SISTEMA
- `ID_USUARIO` - Identificador único (auto-incremento)
- `NOME_COMPLETO` - Nome completo do usuário
- `EMAIL_USUARIO` - Email único para login
- `SENHA_CRIPTOGRAFADA` - Senha criptografada com BCrypt
- `PERFIL_ACESSO` - Perfil (ADMIN ou USUARIO)
- `ATIVO` - Status da conta (1 = ativo, 0 = inativo)
- `DATA_CRIACAO` - Data de criação do registro
- `DATA_ATUALIZACAO` - Data da última atualização

## 🔐 Segurança

- **Spring Security**: Configurado para autenticação JWT
- **BCrypt**: Criptografia de senhas
- **JWT**: Tokens de autenticação com expiração configurável
- **Validações**: Validações de entrada em todos os formulários
- **Controle de Acesso**: Rotas protegidas por perfil de usuário

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/fiap/mottu/
│   │   ├── config/          # Configurações (Security, etc.)
│   │   ├── controller/      # Controladores REST e Web
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── entity/         # Entidades JPA
│   │   ├── repository/     # Repositórios JPA
│   │   ├── security/       # Filtros de segurança
│   │   ├── service/        # Serviços de negócio
│   │   └── MottuAuthApplication.java
│   └── resources/
│       ├── db/migration/   # Migrações Flyway
│       ├── templates/      # Templates Thymeleaf
│       │   ├── fragments/  # Fragmentos reutilizáveis
│       │   ├── admin.html
│       │   ├── cadastro.html
│       │   ├── dashboard.html
│       │   ├── home.html
│       │   └── login.html
│       └── application.yml # Configurações da aplicação
```

## 🚀 Deploy

### 1. Build do Projeto
```bash
mvn clean package
```

### 2. Executar JAR
```bash
java -jar target/mottu-auth-api-1.0.0.jar
```

### 3. Configurações de Produção
Para deploy em produção, ajuste as seguintes configurações em `application.yml`:
- URL do banco de dados
- Credenciais de acesso
- Chave secreta JWT
- Configurações de logging

## 📊 Migrações Flyway

O sistema inclui 4 migrações principais:

1. **V1**: Criação da tabela de usuários
2. **V2**: Inserção do usuário administrador padrão
3. **V3**: Inserção do usuário de teste
4. **V4**: Criação de trigger para atualização automática

## 🧪 Testes

### Executar Testes
```bash
mvn test
```

### Testes de Integração
```bash
mvn verify
```

## 📝 Logs

A aplicação está configurada com logging detalhado para:
- Spring Security (DEBUG)
- Aplicação (DEBUG)
- Flyway (INFO)

## 🔧 Comandos Úteis

### Limpar e Recompilar
```bash
mvn clean compile
```

### Executar com Perfil Específico
```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```

### Verificar Dependências
```bash
mvn dependency:tree
```

## 🐛 Solução de Problemas

### Erro de Conexão com Banco
- Verificar se o banco Oracle está acessível
- Confirmar credenciais em `application.yml`
- Verificar se as migrações Flyway foram executadas

### Erro de Porta
- Verificar se a porta 8080 está disponível
- Alterar porta em `application.yml` se necessário

### Erro de JWT
- Verificar se a chave secreta está configurada
- Confirmar expiração do token

## 📞 Suporte

Para dúvidas ou problemas:
- Verificar logs da aplicação
- Consultar documentação do Spring Boot
- Verificar configurações do banco Oracle

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais na FIAP.

## 👨‍💻 Desenvolvedor

Desenvolvido para Sprint 3 - Java Advanced FIAP.

---

**Nota**: Esta aplicação está configurada para conectar ao banco Oracle da FIAP. Para uso em outros ambientes, ajuste as configurações de banco de dados em `application.yml`.
