
FROM openjdk:8-jre-slim
WORKDIR /home
COPY /target/drones-0.0.1.jar drones-0.0.1.jar
EXPOSE 8282
ENTRYPOINT ["java", "-jar", "drones-0.0.1.jar"]