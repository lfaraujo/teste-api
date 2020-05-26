FROM openjdk:11
ADD target/vivo-api.jar vivo-api.jar
ADD wait-for-it.sh .
EXPOSE 8080
ENTRYPOINT ["./wait-for-it.sh", "db:3306", "--timeout=20", "--", "java", "-jar", "vivo-api.jar"]