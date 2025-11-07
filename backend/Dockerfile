FROM amazoncorretto:21-alpine-jdk
ARG JAR_FILE=target/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /api-v1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/api-v1.jar"]