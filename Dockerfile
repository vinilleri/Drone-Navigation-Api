FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN apt update && apt install -y maven

RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/*.jar"]