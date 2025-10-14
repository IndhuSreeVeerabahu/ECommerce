# Multi-stage build for production
FROM maven:3.9.6-eclipse-temurin-17-alpine as builder

# Set working directory
WORKDIR /app

# Copy Maven files
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src src

# Build the application
RUN mvn clean package -DskipTests

# Production stage
FROM eclipse-temurin:17-jre-alpine

# Install necessary packages
RUN apk add --no-cache \
    curl \
    bash

# Create application user
RUN addgroup -g 1001 ecommerce && adduser -D -u 1001 -G ecommerce ecommerce

# Set working directory
WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/E_Commerce-0.0.1-SNAPSHOT.jar app.jar

# Create logs directory
RUN mkdir -p /var/log/ecommerce && chown -R ecommerce:ecommerce /var/log/ecommerce

# Change ownership of the app directory
RUN chown -R ecommerce:ecommerce /app

# Switch to non-root user
USER ecommerce

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Set JVM options for production
ENV JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar"]
