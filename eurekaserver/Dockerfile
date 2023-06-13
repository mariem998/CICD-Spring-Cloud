FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/eurekaserver-1.0-SNAPSHOT.jar /app/
WORKDIR /app
EXPOSE 7777
CMD ["java", "-jar", "eurekaserver-1.0-SNAPSHOT.jar"]