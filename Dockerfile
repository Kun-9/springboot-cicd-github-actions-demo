# 빌드 스테이지
FROM gradle:jdk17 AS build
WORKDIR /app

COPY build.gradle settings.gradle . /
# 소스코드 복사
COPY src ./src

# Workflow에서 환경변수 가져오기
ARG CURRENT_ENV
ENV CURRENT_ENV=$CURRENT_ENV

# 애플리케이션 빌드
RUN gradle build -x test

# 실행 스테이지
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# 빌드된 결과물만 가져오기
COPY --from=build /app/build/libs/*.jar app.jar
# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]