FROM openjdk:17
EXPOSE 8080
COPY /target/FlightControlApplication-0.0.1-SNAPSHOT.jar flightcontrol.jar
ENTRYPOINT ["java","-jar" ,"/flightcontrol.jar"]