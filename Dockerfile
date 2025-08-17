# FROM openjdk:17
# ARG JAR_FILE=build/libs/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

# Giai đoạn 1: Build dự án với Gradle
FROM gradle:latest as builder

# Đặt thư mục làm việc bên trong container
WORKDIR /app

# Copy các file của dự án
COPY --chown=gradle:gradle . /app

# Chạy Gradle để build dự án
RUN gradle clean build -x test

# Giai đoạn 2: Tạo image cuối cùng nhỏ gọn
FROM eclipse-temurin:17-jre-alpine

# Copy file JAR đã được build từ giai đoạn 'builder'
COPY --from=builder /app/build/libs/*.jar /app.jar

# Chạy ứng dụng khi container khởi động
ENTRYPOINT ["java","-jar","/app.jar"]