# Multi-stage Dockerfile for CuongHoangDev

# ===== Stage 1: Build =====
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copy Maven wrapper and pom first for better layer caching
COPY mvnw mvnw.cmd ./
COPY .mvn/ .mvn/
COPY pom.xml ./

# Pre-fetch dependencies
RUN ./mvnw -q -DskipTests dependency:go-offline

# Copy source and build
COPY src/ ./src/
RUN ./mvnw -q -DskipTests clean package

# ===== Stage 2: Runtime =====
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app

# Non-root user for security
RUN useradd -r -u 1001 appuser

# Copy fat JAR from builder
COPY --from=builder /app/target/CuongHoangDev-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port
EXPOSE 8080

# Set default active profile via env (can be overridden)
ENV SPRING_PROFILES_ACTIVE=prod

# Healthcheck (optional)
HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run as non-root
USER appuser

ENTRYPOINT ["java","-jar","/app/app.jar"]

