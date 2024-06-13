#syntax=docker/dockerfile:1
FROM gradle:8.2.1-jdk17-alpine as build
WORKDIR /app

COPY . .

RUN gradle build

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/Connector.jar /app/Connector.jar

RUN apk --no-cache add curl

ENTRYPOINT ["sh", "-c", "exec java $JVM_ARGS -Dedc.fs.config=config/dataspaceconnector-configuration.properties -jar Connector.jar"]
