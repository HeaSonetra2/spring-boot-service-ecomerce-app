FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# Install Maven inside the container
RUN apt-get update && apt-get install -y maven

# Build the Spring Boot app with Maven
RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java","-jar","target/demo-0.0.1-SNAPSHOT.jar"]

