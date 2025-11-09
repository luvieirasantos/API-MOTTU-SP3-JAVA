# 🚀 Mottu Auth API - Sistema de Autenticação

## Participantes
Lu Vieira 558935
Melissa Pereira 555656
Diego Furigo do Nascimento 558755

## Video no youtube
https://youtu.be/-vqBHPe_I2c

## 📋 Descrição do Projeto

**Mottu Auth API** é uma aplicação web completa desenvolvida em **Spring Boot** para demonstrar um sistema robusto de autenticação e autorização. Este projeto foi desenvolvido para a **Sprint 3 - Java Advanced** da FIAP, implementando as melhores práticas de desenvolvimento e segurança.

### 🎯 Objetivos da Sprint 3

- ✅ **Thymeleaf**: Camada de visualização com fragmentos reutilizáveis
- ✅ **Flyway**: Controle de versões do banco de dados
- ✅ **Spring Security**: Autenticação e controle de acesso
- ✅ **Funcionalidades Completas**: Fluxos de autenticação e gerenciamento de usuários

## 🏗️ Arquitetura da Aplicação

### Arquitetura Microservices Multiplataforma

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  Frontend Web   │    │   Backend API   │    │   Database      │
│   (Thymeleaf)   │◄──►│  (Spring Boot)  │◄──►│   (Oracle)      │
│                 │    │                 │    │                 │
│ • Dashboard     │    │ • REST API      │    │ • Usuários      │
│ • Admin Panel   │    │ • JWT Auth      │    │ • Perfis        │
│ • Management    │    │ • Security      │    │ • Auditoria     │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                ▲
                                │
                                ▼
┌─────────────────┐    ┌─────────────────┐
│ Mobile App      │    │   Production    │
│ (React Native)  │◄──►│   Deployment    │
│                 │    │                 │
│ • iOS/Android   │    │ • Render Cloud  │
│ • JWT Client    │    │ • HTTPS/SSL     │
│ • Real-time     │    │ • Auto-scaling  │
└─────────────────┘    └─────────────────┘
```

### 🔗 Integração Multiplataforma

A **Mottu Auth API** foi desenvolvida como um serviço centralizado de autenticação que atende múltiplas interfaces:

- **Backend API**: Serviço RESTful central com Spring Boot
- **Frontend Web**: Interface administrativa com Thymeleaf
- **Mobile App**: Aplicativo React Native consumindo a mesma API
- **Database**: Oracle Database com persistência centralizada

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework principal
- **Spring Security 6.2.0** - Segurança e autenticação
- **Spring Data JPA** - Persistência de dados
- **Spring Boot Thymeleaf** - Template engine

### Frontend Web
- **Thymeleaf** - Template engine server-side
- **Bootstrap 5.3.0** - Framework CSS responsivo
- **Font Awesome 6.0.0** - Ícones
- **JavaScript ES6+** - Interatividade

### Mobile App (Integração)
- **React Native** - Framework cross-platform para iOS/Android
- **Axios/Fetch** - Cliente HTTP para consumo da API
- **JWT Client** - Gerenciamento de tokens no aplicativo
- **Async Storage** - Armazenamento local de credenciais

### Banco de Dados
- **Oracle Database 19.3** - Banco de dados principal FIAP
- **Flyway 10.8.0** - Migração e versionamento automatizado
- **HikariCP** - Connection pooling otimizado
- **JPA/Hibernate** - Mapeamento ORM avançado

### Segurança
- **JWT (JSON Web Tokens)** - Autenticação stateless com 2 meses de expiração
- **BCrypt** - Criptografia de senhas com salt único
- **Spring Security** - Controle de acesso baseado em roles
- **CORS** - Configuração para cross-origin (mobile/web)

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

- **Java 17+** (recomendado: OpenJDK 17 ou superior)
- **Maven 3.9+** (para build e gerenciamento de dependências)
- **Acesso ao Oracle Database FIAP** (servidor remoto configurado)
- **Git** para clonar o repositório
- **(Opcional) Docker** para containerização e deploy
- **(Opcional) IDE** IntelliJ IDEA, Eclipse ou VS Code com extensões Java

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/Sprint3-Java.git
cd Sprint3-Java
```

### 2. Configuração da Aplicação

#### 2.1 Configuração do Banco de Dados
A aplicação está pré-configurada para usar o banco Oracle da FIAP:
- **URL**: `jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL`
- **Usuário**: `rm558935`
- **Senha**: `310805`

**⚠️ Importante**: As credenciais estão hardcoded no código para fins educacionais. Em produção, considere usar variáveis de ambiente.

#### 2.2 Configuração JWT (Opcional)
Para alterar a chave secreta JWT, modifique no arquivo `application.yml`:
```yaml
jwt:
  secret: sua_chave_secreta_muito_segura_aqui
```

**Recomendação**: Use uma chave forte com pelo menos 256 bits.

### 3. Executar a Aplicação

#### 3.1 Via Maven (Recomendado)
```bash
# Compilar e executar em um comando
mvn spring-boot:run

# Ou executar pulando testes (mais rápido para desenvolvimento)
mvn spring-boot:run -DskipTests
```

#### 3.2 Via IDE
- Abra o projeto no IntelliJ IDEA, Eclipse ou VS Code
- Execute a classe `MottuAuthApplication.java` localizada em `src/main/java/com/fiap/mottu/`
- A aplicação estará disponível em `http://localhost:8080`

#### 3.3 Via Docker
```bash
# Build da imagem
docker build -t mottu-auth-api .

# Executar container
docker run -p 8080:8080 mottu-auth-api

# Ou usar docker-compose (recomendado para desenvolvimento)
docker-compose up -d
```

**Nota**: O Docker está configurado para usar as mesmas credenciais hardcoded do banco Oracle FIAP.

### 4. Acessar a Aplicação

#### Ambiente Local (Desenvolvimento)
- **URL Principal**: http://localhost:8080
- **Login**: http://localhost:8080/login
- **Cadastro**: http://localhost:8080/cadastro
- **Dashboard**: http://localhost:8080/dashboard (após login)
- **Admin**: http://localhost:8080/admin (apenas para administradores)

#### Ambiente de Produção (Deploy)
- **API URL**: https://mottu-auth-api.onrender.com
- **Web Interface**: https://mottu-auth-api.onrender.com/
- **API Endpoints Base**: https://mottu-auth-api.onrender.com/api
- **Health Check**: https://mottu-auth-api.onrender.com/actuator/health

#### Mobile App (React Native)
- **API Consumption**: https://mottu-auth-api.onrender.com/api
- **Endpoints Disponíveis**:
  - POST `/api/auth/login` - Autenticação
  - POST `/api/auth/cadastro` - Cadastro
  - GET `/api/auth/perfil` - Perfil do usuário

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

As migrações são executadas automaticamente na inicialização da aplicação. Para controle manual:

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

## 📱 Integração Multiplataforma e Consumo da API

### Arquitetura de Comunicação

A Mottu Auth API foi projetada como um **serviço centralizado** que atende múltiplos clientes através de uma API RESTful padronizada:

```
┌─────────────────────────────────────────────────────────────┐
│                    MOTTU AUTH API                           │
│                (Single Source of Truth)                     │
│                                                             │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐         │
│  │   Web UI    │  │ Mobile App  │  │Future Apps  │         │
│  │ (Thymeleaf) │  │(React Native)│  │  (IoT/Web)  │         │
│  └─────────────┘  └─────────────┘  └─────────────┘         │
│         │                │                │               │
│         └────────────────┼────────────────┘               │
│                          │                                │
│                  ┌───────▼───────┐                        │
│                  │  REST API v1  │                        │
│                  │  /api/auth/** │                        │
│                  └───────────────┘                        │
│                          │                                │
│                  ┌───────▼───────┐                        │
│                  │   Database    │                        │
│                  │    Oracle     │                        │
│                  └───────────────┘                        │
└─────────────────────────────────────────────────────────────┘
```

### 📲 React Native Integration

O aplicativo mobile React Native consome a mesma API através dos seguintes endpoints:

#### Autenticação no App
```javascript
// Login Request
POST /api/auth/login
{
  "email": "user@mottu.com",
  "senha": "password123"
}

// Response
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "nome": "João Silva",
  "email": "user@mottu.com",
  "perfil": "USUARIO"
}
```

#### Estrutura do Cliente Mobile
```javascript
// API Service Example
import axios from 'axios';

const api = axios.create({
  baseURL: 'https://mottu-auth-api.onrender.com/api',
  timeout: 10000,
});

// Interceptor para JWT
api.interceptors.request.use(async (config) => {
  const token = await AsyncStorage.getItem('authToken');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});
```

### 🗄️ Integração com Banco de Dados

#### Estrutura Centralizada
- **Única Fonte da Verdade**: Todos os clientes acessam o mesmo banco Oracle
- **Consistência de Dados**: Garantida pela arquitetura centralizada
- **Performance**: Otimizada com índices e connection pooling

#### Fluxo de Dados
```
Mobile App ──┐
             ├──► API Spring Boot ──► Oracle Database ──► Response
Web UI ──────┘
```

#### Migrações Automatizadas (Flyway)
- **V1**: Criação da tabela `mottu_usuarios_sistema`
- **V2**: Inserção de usuário administrador
- **V3**: Inserção de usuário de teste
- **V4**: Trigger de auditoria automática

### 🔧 Configuração CORS Multiplataforma

A API está configurada para aceitar requisições de múltiplas origens:

```java
@Configuration
public class WebConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",  // React Native development
            "http://localhost:8080",  // Web development
            "https://mottu-app.com"   // Produção mobile
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
    }
}
```

### 📊 Benefícios da Arquitetura Multiplataforma

1. **Single Source of Truth**: Uma API para todas as plataformas
2. **Consistência**: Mesmas regras de negócio em todos os clientes
3. **Manutenibilidade**: Alterações centralizadas afetam todos os clientes
4. **Escalabilidade**: Backend pode ser escalado independentemente
5. **Segurança**: Controle de acesso unificado através de JWT

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
  profiles:
    active: ${SPRING_PROFILES_ACTIVE:prod}
  datasource:
    url: jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL
    username: rm558935
    password: 310805
    driver-class-name: oracle.jdbc.OracleDriver

  jpa:
    database-platform: org.hibernate.dialect.OracleDialect
    hibernate:
      ddl-auto: none
    properties:
      hibernate:
        format_sql: true
        jdbc:
          lob:
            non_contextual_creation: true
  flyway:
    enabled: true
    locations: classpath:db/migration
    validate-on-migrate: true
    baseline-on-migrate: true

server:
  port: 8080

logging:
  level:
    org.hibernate.SQL: info
    org.hibernate.orm.jdbc.bind: info

jwt:
  secret: ${JWT_SECRET:c1f6b9c2a7d94e04b0a1f9d2c3e4f5a6c7d8e9f0a1b2c3d4e5f6a7b8c9d0e1f2}
  expiration: 5184000000 # 2 meses em milissegundos (60 dias)

management:
  endpoints:
    web:
      exposure:
        include: health
  endpoint:
    health:
      show-details: always
```

**Nota**: As configurações de banco e JWT estão hardcoded para fins educacionais. Para produção, considere usar variáveis de ambiente.

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

### 🌐 Informações de Produção

#### URL da API Deployada
- **Base URL**: `https://mottu-auth-api.onrender.com`
- **API Endpoints**: `https://mottu-auth-api.onrender.com/api/*`
- **Status**: ✅ Online e funcional

#### Clientes Conectados
1. **Frontend Web**: Interface administrativa Thymeleaf
2. **Mobile App**: Aplicação React Native (iOS/Android)
3. **Future Integrations**: API pronta para novos clientes

#### Configurações de Produção
- **Runtime**: Docker Container
- **Java Version**: OpenJDK 17
- **Database**: Oracle Database FIAP
- **SSL**: HTTPS habilitado automaticamente
- **Auto-scaling**: Configurado no Render
- **Health Monitoring**: `/actuator/health` endpoint

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

#### Endpoints de Autenticação (Consumidos por React Native e Web)

##### 1. Cadastro de Usuário
```http
POST /api/auth/cadastro
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@mottu.com",
  "senha": "senha123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "nome": "João Silva",
  "email": "joao@mottu.com",
  "perfil": "USUARIO"
}
```

##### 2. Login de Usuário
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "joao@mottu.com",
  "senha": "senha123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "nome": "João Silva",
  "email": "joao@mottu.com",
  "perfil": "USUARIO"
}
```

##### 3. Obter Perfil do Usuário
```http
GET /api/auth/perfil
Authorization: Bearer {jwt_token}
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@mottu.com",
  "perfil": "USUARIO",
  "ativo": true,
  "dataCriacao": "2024-01-01T10:00:00Z"
}
```

#### React Native - Exemplo de Implementação

```javascript
// services/authService.js
import axios from 'axios';
import AsyncStorage from '@react-native-async-storage/async-storage';

const API_URL = 'https://mottu-auth-api.onrender.com/api';

class AuthService {
  async login(email, senha) {
    try {
      const response = await axios.post(`${API_URL}/auth/login`, {
        email,
        senha
      });

      if (response.data.token) {
        await AsyncStorage.setItem('authToken', response.data.token);
        await AsyncStorage.setItem('userData', JSON.stringify(response.data));
      }

      return response.data;
    } catch (error) {
      throw error.response?.data || error.message;
    }
  }

  async cadastrar(nome, email, senha) {
    try {
      const response = await axios.post(`${API_URL}/auth/cadastro`, {
        nome,
        email,
        senha
      });

      if (response.data.token) {
        await AsyncStorage.setItem('authToken', response.data.token);
        await AsyncStorage.setItem('userData', JSON.stringify(response.data));
      }

      return response.data;
    } catch (error) {
      throw error.response?.data || error.message;
    }
  }

  async getPerfil() {
    try {
      const token = await AsyncStorage.getItem('authToken');
      const response = await axios.get(`${API_URL}/auth/perfil`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      return response.data;
    } catch (error) {
      throw error.response?.data || error.message;
    }
  }

  async logout() {
    await AsyncStorage.removeItem('authToken');
    await AsyncStorage.removeItem('userData');
  }

  async isAuthenticated() {
    const token = await AsyncStorage.getItem('authToken');
    return token !== null;
  }
}

export default new AuthService();
```

#### Componente React Native - Exemplo de Uso

```javascript
// screens/LoginScreen.js
import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import authService from '../services/authService';

const LoginScreen = ({ navigation }) => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [loading, setLoading] = useState(false);

  const handleLogin = async () => {
    if (!email || !senha) {
      Alert.alert('Erro', 'Preencha todos os campos');
      return;
    }

    setLoading(true);
    try {
      const userData = await authService.login(email, senha);
      Alert.alert('Sucesso', `Bem-vindo, ${userData.nome}!`);
      navigation.navigate('Dashboard');
    } catch (error) {
      Alert.alert('Erro de Login', error.message || 'Falha na autenticação');
    } finally {
      setLoading(false);
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', padding: 20 }}>
      <Text style={{ fontSize: 24, fontWeight: 'bold', textAlign: 'center', marginBottom: 30 }}>
        Mottu Auth
      </Text>

      <TextInput
        style={{ borderWidth: 1, borderColor: '#ccc', padding: 10, marginBottom: 15 }}
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
        keyboardType="email-address"
        autoCapitalize="none"
      />

      <TextInput
        style={{ borderWidth: 1, borderColor: '#ccc', padding: 10, marginBottom: 20 }}
        placeholder="Senha"
        value={senha}
        onChangeText={setSenha}
        secureTextEntry
      />

      <Button
        title={loading ? "Entrando..." : "Entrar"}
        onPress={handleLogin}
        disabled={loading}
      />
    </View>
  );
};

export default LoginScreen;
```

#### Endpoints Administrativos (requer perfil ADMIN)
- **GET** `/admin/users` - Listar todos os usuários
- **GET** `/admin/users/new` - Formulário de criação de usuário
- **POST** `/admin/users` - Criar novo usuário
- **GET** `/admin/users/{id}/edit` - Formulário de edição
- **POST** `/admin/users/{id}` - Atualizar usuário
- **POST** `/admin/users/{id}/delete` - Excluir usuário
- **POST** `/admin/users/{id}/toggle` - Ativar/desativar usuário

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
