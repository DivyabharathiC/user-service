FROM openjdk:8

ADD target/docker-user.jar docker-user.jar

EXPOSE 3005

ENTRYPOINT ["java","-jar","docker-user.jar"]