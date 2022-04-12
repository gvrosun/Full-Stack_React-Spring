FROM openjdk:11

COPY . /usr/src/server

WORKDIR /usr/src/server/api

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]