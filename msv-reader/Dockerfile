ARG MSVC_NAME=msv-reader
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
COPY --from=builder $TARGET_FOLDER/msv-reader-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=8082

ARG MONGODB_URL_APP
ARG DB_REACTIVE_INTERVAL_APP
ARG DB_REACTIVE_LIMIT_APP
ARG RCA-DB-RETRIES_APP
ARG RCA-DB-INTERVAL_APP

ENV MONGODB_URL $MONGODB_URL_APP
ENV DB_REACTIVE_INTERVAL $DB_REACTIVE_INTERVAL_APP;
ENV DB_REACTIVE_LIMIT $DB_REACTIVE_LIMIT_APP;
ENV RCA-DB-RETRIES $RETRIES_APP;
ENV RCA-DB-INTERVAL $INTERVAL_APP;

ENV PORT $PORT_APP
EXPOSE $PORT

CMD sleep 12 && java -jar msv-reader-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "msv-reader-0.0.1-SNAPSHOT.jar"]