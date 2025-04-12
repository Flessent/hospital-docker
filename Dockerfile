FROM openjdk:17
EXPOSE 8082
ADD target/hospital-app.jar hospital-app.jar
ENTRYPOINT ["java","-jar","hospital-app.jar"]

