FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY ../pom.xml /tmp/parent.pom
RUN mvn install:install-file \
    -Dfile=/tmp/parent.pom \
    -DgroupId=com.example \
    -DartifactId=potro-net \
    -Dversion=0.0.1-SNAPSHOT \
    -Dpackaging=pom
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests
FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar /api-v1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/api-v1.jar"]