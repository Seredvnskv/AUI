FROM eclipse-temurin:21.0.8_9-jre

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:brand_manager
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=

ENV CARS_API=http://localhost:8081

EXPOSE 8080

COPY target/*.jar brand-management.jar

CMD ["java", "-jar", "brand-management.jar"]