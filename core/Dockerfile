FROM openjdk:11
VOLUME /tmp
EXPOSE 8081
ADD ./target/interview-core-0.0.1-SNAPSHOT.jar interview-core.jar
ENTRYPOINT ["java","-jar","/interview-core.jar"]