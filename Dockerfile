FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# ⭐ Add this line - chmod +x mvnw = អនុញ្ញាតឲ្យ run file
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD java -jar target/*.jar

