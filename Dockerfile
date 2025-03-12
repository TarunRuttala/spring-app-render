# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose the port that Spring Boot runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
