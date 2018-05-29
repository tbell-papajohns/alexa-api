FROM us.gcr.io/pji-prod-cicd-infrastructure/papajohns/shared-images/appd-jdk-8-alpine
MAINTAINER papajohns.com
COPY /target/alexa-skill-handler-service-0.0.1-SNAPSHOT.war  /opt/alexa-skill-handler-service-0.0.1-SNAPSHOT.war
COPY servers.json /opt/servers.json
COPY cert.txt /opt/cert.txt
EXPOSE 8080
ENTRYPOINT ["java", "-javaagent:/opt/appdynamics/javaagent.jar", "-Dappdynamics.agent.tierName=ALEXA-SKILL-HANDLER", "-Dappdynamics.agent.reuse.nodeName=true", "-Dappdynamics.agent.reuse.nodeName.prefix=ALEXA-SKILL-HANDLER", "-Dspring.profiles.active=docker", "-Dspring.profiles.active=docker", "-jar", "/opt/alexa-skill-handler-service-0.0.1-SNAPSHOT.war"]