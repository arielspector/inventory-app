FROM openjdk:8-jdk-alpine
VOLUME c:/dockerdata
ARG JAR_FILE 
COPY ${JAR_FILE} app.jar
EXPOSE 8280
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
