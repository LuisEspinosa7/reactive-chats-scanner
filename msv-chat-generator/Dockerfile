ARG MSVC_NAME=msv-chat-generator
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
COPY --from=builder $TARGET_FOLDER/msv-chat-generator-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=8090

ENV SOURCE_FILE=sourceFile \
     INPUT_REACTIVE_INTERVAL=inputReactiveInterval \
     MONGODB_URL=mongodbUrl

ENV PORT $PORT_APP
EXPOSE $PORT

CMD sleep 1 && java -jar msv-chat-generator-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "msv-chat-generator-0.0.1-SNAPSHOT.jar"]