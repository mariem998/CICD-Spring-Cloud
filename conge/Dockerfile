FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/conge-1.0-SNAPSHOT.jar /app/
WORKDIR /app
EXPOSE 8099
CMD ["java", "-jar", "conge-1.0-SNAPSHOT.jar"]