FROM openjdk:11
ADD target/vivo-api.jar vivo-api.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "vivo-api.jar"]