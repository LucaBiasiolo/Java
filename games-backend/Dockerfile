FROM openjdk:25-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV DB_URL=jdbc:mysql://host.docker.internal:3306/games
EXPOSE 8080 3306
ENTRYPOINT ["java","-jar","/app.jar"]