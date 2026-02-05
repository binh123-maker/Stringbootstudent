# Bước 1: Build ứng dụng với JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Chạy build trực tiếp với file pom.xml nằm trong thư mục lab3
RUN mvn -f lab3/pom.xml clean package -DskipTests

# Bước 2: Chạy ứng dụng với JRE 21 (nhẹ và bảo mật)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Lấy file jar từ thư mục target của lab3
COPY --from=build /app/lab3/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]