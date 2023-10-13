# 베이스 이미지 설정
FROM node:18 AS frontend_build_stage

WORKDIR /app

# 프론트엔드 소스 복사
COPY frontend/ ./

# 프론트엔드 빌드
RUN npm install
RUN npm run build

# 백엔드 이미지 설정
FROM openjdk:17-jre-slim

WORKDIR /app

# 프론트엔드 빌드 결과물 복사
COPY --from=frontend_build_stage /app/build /app/static

# 백엔드 빌드 결과물 복사
COPY backend/build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
