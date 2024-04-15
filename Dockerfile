FROM maven:amazoncorretto

WORKDIR /app/

COPY pom.xml ./
COPY src ./src

RUN mvn package


FROM openjdk:23-slim

COPY target/*.jar ./javaapp.jar

ENTRYPOINT [ "java", "-jar", "/javaapp.jar" ]