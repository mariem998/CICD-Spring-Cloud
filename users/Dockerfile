FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/users-1.0-SNAPSHOT.jar /app/


EXPOSE 8080
CMD ["java", "-jar", "users-1.0-SNAPSHOT.jar"]