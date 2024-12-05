FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/patient-record-service.jar patient-record-service.jar

EXPOSE 8080

# Set environment variables from build arguments
ENV DB_URL=${DB_URL}
ENV DB_USER_NAME=${DB_USER_NAME}
ENV DB_PASSWORD=${DB_PASSWORD}

CMD ["java", "-Dspring.datasource.url=${DB_URL}", "-Dspring.datasource.username=${DB_USER_NAME}", "-Dspring.datasource.password=${DB_PASSWORD}", "-jar", "patient-record-service.jar"]
