FROM eclipse-temurin:21.0.8_9-jre

ENV SERVER_PORT=8080

ENV BRAND_API=http://localhost:8082

EXPOSE 8080

COPY target/*.jar model-management.jar

CMD ["java", "-jar", "model-management.jar"]