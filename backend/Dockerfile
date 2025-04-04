# Use OpenJDK 21 for building and running the Spring Boot app
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file built by Maven
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]