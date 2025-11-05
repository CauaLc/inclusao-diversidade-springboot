# ----- Estagio 1: Build (Construção) -----
FROM maven:3.9-eclipse-temurin-21 AS builder

# Define o diretorio de trabalho dentro do conteiner
WORKDIR /app

# Copia o pom.xml
COPY pom.xml .

# Copia todo o código-fonte
COPY src ./src

# Executa o build do Maven
RUN mvn clean package -DskipTests

# ----- Estagio 2: Run (Execução) -----
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o .jar do estagio 'builder' para este conteiner
COPY --from=builder /app/target/inclusao-diversidade-0.0.1-SNAPSHOT.jar app.jar

# Expoe a porta 8080 (padrão do Spring Boot)
EXPOSE 8080

# Comando para iniciar sua aplicaca
ENTRYPOINT ["java", "-jar", "app.jar"]