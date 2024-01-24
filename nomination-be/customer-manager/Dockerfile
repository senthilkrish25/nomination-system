FROM eclipse-temurin:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} customer-manager.jar
ENTRYPOINT ["java","-jar","/customer-manager.jar"]
