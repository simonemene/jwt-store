FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY target/backend-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=dev

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

RUN apt-get update && apt-get install -y netcat-traditional

ENTRYPOINT ["/wait-for-it.sh", "mysql", "3306", "--", "java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]