FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/apigw-1.0-SNAPSHOT.jar /app/
WORKDIR /app
EXPOSE 9977
CMD ["java", "-jar", "apigw-1.0-SNAPSHOT.jar"]