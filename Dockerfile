#stage-1(installing and building a jar)
FROM maven:3.8.8-eclipse-temurin-17 AS builder

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTest=true

# stage-29 - execute JAR file from the above stage
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/librarymanagerapp.jar

CMD ["java", "-jar", "librarymanagerapp.jar"]
