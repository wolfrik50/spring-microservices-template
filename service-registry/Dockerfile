# Build stage
FROM maven:3.9.9-eclipse-temurin-21-jammy AS build
WORKDIR /app

# Copy the POM file
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-jammy

# Set working directory
WORKDIR /app

# Set non-root user
RUN addgroup --system javauser && adduser --system --no-create-home --group javauser

# Copy the JAR file from the build stage
COPY --from=build --chown=javauser:javauser /app/target/*.jar app.jar

# Set the user
USER javauser

# Expose the port the app runs on
EXPOSE 8080

# Set JVM options for better performance
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0 -XX:MinRAMPercentage=25.0 -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof"

# Command to run the application
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
