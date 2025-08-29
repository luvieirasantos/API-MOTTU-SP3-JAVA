# Multi-stage build para otimizar o tamanho da imagem final
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Definir diretório de trabalho
WORKDIR /app

# Copiar arquivos de dependências primeiro (para aproveitar cache do Docker)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Baixar dependências (esta camada será cacheada se pom.xml não mudar)
RUN mvn dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Compilar e empacotar a aplicação
RUN mvn clean package -DskipTests

# Imagem de produção
FROM eclipse-temurin:17-jre-alpine

# Criar usuário não-root para segurança
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# Definir diretório de trabalho
WORKDIR /app

# Instalar dependências necessárias para Oracle JDBC
RUN apk add --no-cache libc6-compat

# Copiar JAR da aplicação do stage de build
COPY --from=build /app/target/mottu-auth-api-1.0.0.jar app.jar

# Criar diretório para logs
RUN mkdir -p /app/logs && \
    chown -R appuser:appgroup /app

# Mudar para usuário não-root
USER appuser

# Expor porta da aplicação
EXPOSE 8080

# Variáveis de ambiente para configuração
ENV JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -XX:+UseContainerSupport"
ENV SPRING_PROFILES_ACTIVE=production
ENV SERVER_PORT=8080

# Health check para o Render
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Comando para executar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
