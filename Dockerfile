FROM java:openjdk-8-jre-alpine
MAINTAINER papajohns.com
COPY /target/alexa-skill-handler-service-0.0.1-SNAPSHOT.war  /opt/alexa-skill-handler-service-0.0.1-SNAPSHOT.war
COPY servers.json /opt/servers.json
COPY cert.txt /opt/cert.txt
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/opt/alexa-skill-handler-service-0.0.1-SNAPSHOT.war"]