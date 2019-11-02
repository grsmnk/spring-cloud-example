FROM openjdk:latest

ARG JAR_FILE

COPY ${JAR_FILE} zuul.jar

ENTRYPOINT ["java","-jar","/zuul.jar"]
































# docker build -t com.hillel/eureka:test --build-arg JAR_FILE=target/eureka-service-discovery-1.0-SNAPSHOT.jar .
# docker run -p 8761:8761 com.hillel/eureka:test