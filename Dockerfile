FROM openjdk:8-jdk-alpine
COPY target/monitor-service-1.0.0.jar monitor-service-1.0.0.jar
ENTRYPOINT ["java","-jar","/monitor-service-1.0.0.jar"]