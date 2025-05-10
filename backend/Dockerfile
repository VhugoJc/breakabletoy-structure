# Stage 1: Use a base image with Java 21 and Maven for the build stage
FROM eclipse-temurin:21 as build

# Install Maven in the build image
RUN apt-get update && apt-get install -y maven

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven descriptor and build files separately to utilize Docker cache effectively
COPY pom.xml .

# Download dependencies separately to leverage caching (improves build time)
RUN mvn dependency:go-offline

# Copy the entire source code to the working directory
COPY ./src ./src

# Build the project using Maven (skipping tests can speed up the build process)
RUN mvn clean package -DskipTests

# Stage 2: Use a slimmer JRE image for the final image
FROM eclipse-temurin:21-jre

# Set the working directory inside the final image
WORKDIR /app

# Copy the built JAR from the previous build stage
COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]