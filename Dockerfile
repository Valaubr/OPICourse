from openjdk:17
add build/libs/Task-Tracker-0.0.1-SNAPSHOT.jar backend.jar
entrypoint ["java", "-jar", "backend.jar"]
