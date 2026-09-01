# ---- Stage 1: Build the jar ----
# Build an image with the Java 17 image
FROM maven:3.9-eclipse-temurin-17 AS build
# Set the working directory to `/app`
WORKDIR /app
# Copy `pom.xml`
COPY pom.xml .
# Install mvn dependency
RUN mvn dependency:go-offline
# Copy `src`
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Stage 2: Run the jar ----
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]