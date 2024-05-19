FROM openjdk:17-jdk-alpine
MAINTAINER work.com
COPY target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.jar","--debug"]
