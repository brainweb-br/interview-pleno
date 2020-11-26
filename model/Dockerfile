FROM openjdk:11
VOLUME /tmp
ADD ./target/interview-model-0.0.1-SNAPSHOT.jar interview-model.jar
ENTRYPOINT ["java","-jar","/interview-model.jar"]