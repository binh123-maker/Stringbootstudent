# Bước 1: Build ứng dụng
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Chép toàn bộ thư mục vào container
COPY . .
# Chạy build trực tiếp với file pom.xml nằm trong thư mục lab3
RUN mvn -f lab3/pom.xml clean package -DskipTests

# Bước 2: Chạy ứng dụng
# Thay đổi image sang bản alpine để ổn định và nhẹ hơn
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Lấy file jar từ thư mục target của lab3
COPY --from=build /app/lab3/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]