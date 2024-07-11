FROM openjdk:21-slim

WORKDIR /app

COPY ./build/libs/hashgenerator-0.0.1-SNAPSHOT.jar ./

EXPOSE 7777

ENTRYPOINT ["java", "-jar", "hashgenerator-0.0.1-SNAPSHOT.jar"]
