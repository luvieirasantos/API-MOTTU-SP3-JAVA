# 🚀 Deploy da Mottu Auth API no Render

Este guia explica como fazer o deploy da aplicação Mottu Auth API na plataforma Render.

## 📋 Pré-requisitos

- ✅ Conta no Render (gratuita)
- ✅ Repositório Git com o código da aplicação
- ✅ Dockerfile configurado (já criado)
- ✅ Acesso ao banco Oracle FIAP

## 🔧 Configuração no Render

### 1. Criar Nova Aplicação Web Service

1. Acesse [render.com](https://render.com) e faça login
2. Clique em **"New +"** → **"Web Service"**
3. Conecte seu repositório Git (GitHub, GitLab, etc.)

### 2. Configurações da Aplicação

#### **Nome e Configurações Básicas**
- **Name**: `mottu-auth-api` (ou nome de sua preferência)
- **Region**: Escolha a região mais próxima (ex: Oregon para Brasil)
- **Branch**: `main` (ou sua branch principal)
- **Root Directory**: Deixe em branco (raiz do projeto)

#### **Configurações de Build**
- **Runtime**: `Docker`
- **Build Command**: Deixe em branco (usará o Dockerfile)
- **Start Command**: Deixe em branco (definido no Dockerfile)

#### **Configurações de Instância**
- **Instance Type**: `Free` (para testes) ou `Starter` ($7/mês) para produção
- **Auto-Deploy**: ✅ Habilitado (deploy automático a cada push)

### 3. Variáveis de Ambiente

Configure as seguintes variáveis de ambiente no Render:

#### **Banco de Dados**
```bash
DATABASE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
DATABASE_USERNAME=rm558935
DATABASE_PASSWORD=310805
```

#### **Aplicação**
```bash
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080
PORT=8080
```

#### **JWT**
```bash
JWT_SECRET=sua_chave_secreta_muito_segura_aqui
JWT_EXPIRATION=5184000000
```

#### **Logging**
```bash
LOGGING_LEVEL_ROOT=WARN
LOGGING_LEVEL_COM_FIAP=INFO
```

#### **Java (Opcional)**
```bash
JAVA_OPTS=-Xms512m -Xmx1024m -XX:+UseG1GC -XX:+UseContainerSupport
```

## 🐳 Dockerfile Otimizado

O Dockerfile já está configurado com:

- ✅ **Multi-stage build** para reduzir tamanho da imagem
- ✅ **Usuário não-root** para segurança
- ✅ **Health checks** para monitoramento
- ✅ **Otimizações de memória** Java
- ✅ **Configurações de produção**

## 📊 Monitoramento e Health Checks

### Endpoints de Monitoramento
- **Health Check**: `https://sua-app.onrender.com/actuator/health`
- **Info**: `https://sua-app.onrender.com/actuator/info`
- **Metrics**: `https://sua-app.onrender.com/actuator/metrics`

### Logs
- Acesse a aba **"Logs"** no dashboard do Render
- Logs são exibidos em tempo real
- Arquivos de log são mantidos por 30 dias

## 🔄 Processo de Deploy

### 1. Primeiro Deploy
1. Clique em **"Create Web Service"**
2. Render detectará automaticamente o Dockerfile
3. Build será executado automaticamente
4. Aplicação será iniciada na porta configurada

### 2. Deploys Automáticos
- A cada push para a branch principal
- Render detecta mudanças automaticamente
- Build e deploy são executados
- Zero downtime (rolling deployment)

### 3. Deploy Manual
- Acesse a aba **"Manual Deploy"**
- Escolha a branch/commit desejado
- Clique em **"Deploy latest commit"**

## 🚨 Troubleshooting

### Problemas Comuns

#### 1. Build Falhando
```bash
# Verificar logs de build
# Verificar se Dockerfile está correto
# Verificar se todas as dependências estão no pom.xml
```

#### 2. Aplicação Não Iniciando
```bash
# Verificar logs da aplicação
# Verificar variáveis de ambiente
# Verificar conectividade com banco Oracle
```

#### 3. Erro de Memória
```bash
# Ajustar JAVA_OPTS
# Verificar tipo de instância (Free tem limitações)
# Otimizar configurações de JVM
```

### Logs de Debug
Para debug detalhado, adicione:
```bash
LOGGING_LEVEL_COM_FIAP=DEBUG
LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_SECURITY=DEBUG
```

## 📈 Escalabilidade

### Free Tier
- ✅ 750 horas/mês
- ✅ 512MB RAM
- ✅ 0.1 CPU
- ✅ Sleep após 15 min de inatividade

### Starter ($7/mês)
- ✅ Sem limite de horas
- ✅ 512MB RAM
- ✅ 0.5 CPU
- ✅ Sem sleep automático

### Professional
- ✅ 1GB+ RAM
- ✅ 1+ CPU cores
- ✅ Load balancing
- ✅ Custom domains

## 🔒 Segurança

### Recomendações
1. **Altere a chave JWT** para produção
2. **Use HTTPS** (Render fornece automaticamente)
3. **Configure CORS** adequadamente
4. **Monitore logs** de segurança
5. **Use variáveis de ambiente** para credenciais

### Variáveis Sensíveis
- ✅ **NUNCA** commite credenciais no código
- ✅ Use variáveis de ambiente do Render
- ✅ Rotacione chaves JWT regularmente
- ✅ Monitore tentativas de acesso

## 📱 URLs da Aplicação

Após o deploy, sua aplicação estará disponível em:

- **URL Principal**: `https://sua-app.onrender.com`
- **Login**: `https://sua-app.onrender.com/login`
- **Cadastro**: `https://sua-app.onrender.com/cadastro`
- **Dashboard**: `https://sua-app.onrender.com/dashboard`
- **Admin**: `https://sua-app.onrender.com/admin`

## 🎯 Próximos Passos

1. ✅ **Deploy inicial** no Render
2. 🔄 **Teste todas as funcionalidades**
3. 📊 **Configure monitoramento**
4. 🔒 **Ajuste configurações de segurança**
5. 📈 **Monitore performance**
6. 🚀 **Scale conforme necessário**

## 📞 Suporte

- **Render Docs**: [docs.render.com](https://docs.render.com)
- **Render Community**: [community.render.com](https://community.render.com)
- **Issues**: Use a aba Issues do seu repositório Git

---

**Mottu Auth API** - Deploy automatizado e escalável no Render! 🚀✨
