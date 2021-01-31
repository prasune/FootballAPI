FROM openjdk:8-jre-slim

WORKDIR /app
COPY ./target/FootballAPI-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "FootballAPI-0.0.1-SNAPSHOT.jar"]