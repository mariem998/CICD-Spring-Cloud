FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/salaires-1.0-SNAPSHOT.jar /app/


EXPOSE 8081
CMD ["java", "-jar", "salaires-1.0-SNAPSHOT.jar"]