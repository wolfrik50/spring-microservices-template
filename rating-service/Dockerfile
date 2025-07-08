# Build Stage: Maven JDK-21 Alpine
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

# Add Maintainer Info
LABEL maintainer="Swayamsidh Nayak <swayamsidh2001@gmail.com>"
LABEL description="Build stage for Rating Service"
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
LABEL description="Runtime image for Rating Service"
LABEL version="1.0.0"

# Install Dependencies
RUN apk add --no-cache curl

# Set Working Directory
WORKDIR /app

# Copy Built JAR From Build Stage
COPY --from=builder /review-system/rating-service/target/rating-service.jar rating-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "rating-service.jar"]
