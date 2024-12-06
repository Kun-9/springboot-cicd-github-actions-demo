# 이미지 베이스 설정
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 환경변수로 프로필 설정
ENV SPRING_PROFILES_ACTIVE=prod

# Jar 파일 변수 설정
ARG JAR_FILE=build/libs/*.jar

# Jar 파일 컨테이너 내부로 복사
COPY ${JAR_FILE} app.jar

# 포트 설정
EXPOSE 8080

# 애플리케이션 실행 (경로 수정)
ENTRYPOINT ["java", "-jar", "app.jar"]