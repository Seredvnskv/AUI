FROM eclipse-temurin:21.0.8_9-jre

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:model_manager
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=

ENV BRAND_API=http://localhost:8082

EXPOSE 8080

COPY target/*.jar model-management.jar

CMD ["java", "-jar", "model-management.jar"]