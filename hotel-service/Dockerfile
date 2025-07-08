# Build Stage: Maven JDK-21 Alpine
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

# Add Maintainer Info
LABEL maintainer="Swayamsidh Nayak <swayamsidh2001@gmail.com>"
LABEL description="Build stage for Hotel Service"
LABEL version="1.0.0"

# Set Working Directory
WORKDIR /review-system

# Copy Project Files From Root
COPY . .

# Package Application
RUN mvn clean package -DskipTests -Pkube

# Runtime Stage: JDK-21 Alpine
FROM eclipse-temurin:21-jdk-alpine

# Add Maintainer Info
LABEL maintainer="Swayamsidh Nayak <swayamsidh2001@gmail.com>"
LABEL description="Runtime image for Hotel Service"
LABEL version="1.0.0"

# Install Dependencies
RUN apk add --no-cache curl

# Set Working Directory
WORKDIR /app

# Copy Built JAR From Build Stage
COPY --from=builder /review-system/hotel-service/target/hotel-service.jar hotel-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "hotel-service.jar"]
