FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar CinepoLIDTS.jar
ENTRYPOINT ["java","-jar","/CinepoLIDTS.jar"]
EXPOSE 8080
