FROM openjdk:17-jdk
VOLUME /tmp COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/biblioteca-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080