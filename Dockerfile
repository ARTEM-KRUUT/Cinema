FROM openjdk:17-jdk-slim-buster

COPY target/Cinema-0.0.1-SNAPSHOT.jar /app/myproject.jar

COPY cinema /app/cinema

WORKDIR /app

CMD ["java", "-jar", "myproject.jar"]
