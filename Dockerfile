FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN apt update && apt install -y maven

RUN mvn clean package -DskipTests

CMD ["sh", "-c", "java -jar target/*.jar"]