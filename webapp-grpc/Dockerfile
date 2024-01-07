FROM openjdk:17-jdk-slim

COPY target/*.jar /opt/app.jar
WORKDIR /opt/
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]