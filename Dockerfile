FROM gradle:jdk17 as build
COPY --chown=gradle:gradle . .
WORKDIR .
RUN gradle build

FROM gradle:jdk17
EXPOSE 8080
RUN mkdir /app/
COPY --chmod=777 --from=build /home/gradle/build/libs/Task-Tracker-0.0.1-SNAPSHOT.jar /app/Task-Tracker-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/Task-Tracker-0.0.1-SNAPSHOT.jar"]