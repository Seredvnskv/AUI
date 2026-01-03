FROM eclipse-temurin:21.0.8_9-jre

ENV SERVER_PORT=8080

ENV GATEWAY_API=localhost:8080
ENV CARS_API=http://localhost:8081
ENV BRAND_API=http://localhost:8082

EXPOSE 8080

COPY target/*.jar gateway.jar

CMD ["java", "-jar", "gateway.jar"]
