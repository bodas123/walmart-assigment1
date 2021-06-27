FROM openjdk:8-jre-alpine
WORKDIR /app
COPY target/driver-service.jar /app
CMD chmod +774 driver-service.jar
EXPOSE 9080
CMD sleep 10 && java -jar driver-service.jar
