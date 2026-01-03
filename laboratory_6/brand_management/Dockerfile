FROM eclipse-temurin:21.0.8_9-jre

ENV SERVER_PORT=8080

ENV CARS_API=http://localhost:8081

EXPOSE 8080

COPY target/*.jar brand-management.jar

CMD ["java", "-jar", "brand-management.jar"]