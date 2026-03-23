FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . ./
RUN ./gradlew bootJar -x test
CMD ["sh", "-c", "java -jar build/libs/*.jar"]