FROM openjdk:11
ADD target/kitulgala.jar kitulgala.jar
EXPOSE 3030
ENTRYPOINT ["java","-jar","kitulgala.jar"]
