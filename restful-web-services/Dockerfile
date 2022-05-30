FROM openjdk:11-jdk-slim

ARG JAR_FILE=target/restful-web-services-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} restful-web-services-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","restful-web-services-0.0.1-SNAPSHOT.jar"]
