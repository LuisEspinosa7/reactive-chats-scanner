ARG MSVC_NAME=msv-reporter
FROM openjdk:11.0.16 as builder
ARG MSVC_NAME
WORKDIR /app/$MSVC_NAME


COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:11-jre-slim-buster
ARG MSVC_NAME
WORKDIR /app
RUN mkdir ./logs
ARG TARGET_FOLDER=/app/$MSVC_NAME/target
COPY --from=builder $TARGET_FOLDER/msv-reporter-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=8084

ENV MONGODB_URL=localhost \
     RCA-DB-RETRIES=dbRetries \
     RCA-DB-INTERVAL=dbInterval



ENV PORT $PORT_APP
EXPOSE $PORT

CMD sleep 5 && java -jar msv-reporter-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "msv-reporter-0.0.1-SNAPSHOT.jar"]