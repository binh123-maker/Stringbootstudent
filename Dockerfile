# Bước 1: Build ứng dụng
FROM maven:3.8.5-openjdk-17 AS build
# Chép mọi thứ vào
COPY . .
# Di chuyển vào thư mục lab3 để build
WORKDIR /lab3
RUN mvn clean package -DskipTests

# Bước 2: Chạy ứng dụng
FROM openjdk:17-jdk-slim
# Lấy file jar từ trong thư mục lab3/target
COPY --from=build /lab3/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]