FROM openjdk:17-alpine
ADD build/libs/retail-api.jar /app.jar
CMD java $JAVA_OPTS -jar app.jar $APP_OPTS