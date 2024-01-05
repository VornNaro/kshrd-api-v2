FROM eclipse-temurin:11-jre-alpine
ADD target/*.jar /opt/root.jar
ENTRYPOINT [ "java", "-jar", "/opt/root.jar" ]