FROM gradle:8.3-jdk17 AS build
WORKDIR /app
COPY build.gradle settings.gradle gradlew* /app/
COPY gradle /app/gradle
RUN gradle dependencies --no-daemon
COPY . /app
RUN gradle build --no-daemon
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]