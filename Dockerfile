FROM maven:3.3-jdk-8 as build

WORKDIR /app

COPY pom.xml .
COPY src/ src/
RUN mvn package

FROM openjdk:8-jre-alpine
COPY --from=build /app/target/assignment-0.0.1-SNAPSHOT.jar /app/assignment-0.0.1.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/app/assignment-0.0.1.jar"]

