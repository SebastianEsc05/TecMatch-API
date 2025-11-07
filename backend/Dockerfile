FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean install
FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar /api-v1.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/api-v1.jar"]