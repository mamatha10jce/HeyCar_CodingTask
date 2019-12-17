FROM openjdk:8
ADD /target/heycarapplication-0.0.1-SNAPSHOT.jar heycarcodingtask.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","heycarcodingtask.jar"]