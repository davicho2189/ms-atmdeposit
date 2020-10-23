FROM openjdk:12
VOLUME /tmp
EXPOSE 8080
ADD ./target/com-atmdeposit-atm-0.0.1-SNAPSHOT.jar atmdeposit-item.jar
ENTRYPOINT ["java","-jar","/servicio-item.jar"]