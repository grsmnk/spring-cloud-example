#FROM ubuntu:18.04

FROM openjdk:latest

ARG JAR_FILE

COPY ${JAR_FILE} eureka.jar

ENTRYPOINT ["java","-jar","/eureka.jar"]
































# docker build -t com.hillel/eureka:test
# --build-arg JAR_FILE=target/eureka.jar .


# docker run -p 8761:8761 com.hillel/eureka:test