FROM eclipse-temurin:17.0.2_8-jre

RUN mkdir -p /app
WORKDIR /app
ENV JAR_FILE=asterisk-backend-api-0.0.1-SNAPSHOT.jar
ENV TARGET_JAR_FILE=asterisk-backend-api.jar

COPY target/$JAR_FILE ./$TARGET_JAR_FILE

EXPOSE 8080
ENV JAVA_ARGS=""
CMD java $JAVA_ARGS -jar $TARGET_JAR_FILE