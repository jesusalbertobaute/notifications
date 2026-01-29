FROM maven:3.9.12-amazoncorretto-21-alpine AS build
COPY ./notifications-library/pom.xml /opt/app/notification/pom.xml
COPY ./notifications-library/src /opt/app/notification/src
COPY ./notification-rest-client/pom.xml /opt/app/notification-client/pom.xml
COPY ./notification-rest-client/src /opt/app/notification-client/src
RUN mvn -f /opt/app/notification/pom.xml clean install -DskipTests
RUN mvn -f /opt/app/notification-client/pom.xml clean install -DskipTests

FROM amazoncorretto:21-alpine-jdk
RUN addgroup -S maven && adduser -S muser -G maven
RUN mkdir /app && chown muser:maven /app
COPY --from=build /opt/app/notification-client/target/notification-rest-client-1.0.0.jar /app/notification-rest-client-1.0.0.jar
USER muser
WORKDIR /app
EXPOSE 8080
CMD ["java","-jar","notification-rest-client-1.0.0.jar"]