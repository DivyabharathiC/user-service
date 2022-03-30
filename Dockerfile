FROM openjdk:8
EXPOSE 3005
ADD target/docker-user.jar docker-user.jar
ENTRYPOINT ["java","-jar","/docker-user.jar"]