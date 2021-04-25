FROM openjdk:8-alpine
ADD target/calculator-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/app.jar"]

EXPOSE 8080