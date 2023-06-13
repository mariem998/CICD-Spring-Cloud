FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/formation-1.0-SNAPSHOT.jar /app/
WORKDIR /app
EXPOSE 8077
CMD ["java", "-jar", "formation-1.0-SNAPSHOT.jar"]