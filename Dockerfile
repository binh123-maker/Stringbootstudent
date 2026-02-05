# Bước 1: Build ứng dụng
FROM maven:3.8.5-openjdk-21 AS build
# Sửa dòng này trong Dockerfile nếu pom.xml nằm trong lab3
COPY . .
RUN cd lab3 && ./mvnw clean package -DskipTests
# Di chuyển vào thư mục lab3 để build
WORKDIR /lab3
RUN mvn clean package -DskipTests

# Bước 2: Chạy ứng dụng
FROM openjdk:17-jdk-slim
# Lấy file jar từ trong thư mục lab3/target
COPY --from=build /lab3/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]