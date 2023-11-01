FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DB_URL
ARG DB_USER
ARG DB_PWD
ENV TECHBUILD_URL=$DB_URL
ENV TECHBUILD_USER=$DB_USER
ENV TECHBUILD_PWD=$DB_PWD
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]