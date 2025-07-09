# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /workspace

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
EXPOSE 8080

COPY --from=builder /workspace/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
