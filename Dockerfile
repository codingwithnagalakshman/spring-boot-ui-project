FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

ENV backendServiceUrl "http://localhost:8081"

COPY build/libs/ui-project.jar ui-project.jar

ENTRYPOINT ["java", "-jar", "/ui-project.jar"]