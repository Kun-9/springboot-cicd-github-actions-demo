# 이미지 베이스 설정
FROM openjdk:17

# 서버 시간과 일치시키기 위서 tzdata 설치
RUN apt-get update && apt-get install -y tzdata

# 타임존 서울 설정
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Jar 파일 변수 설정
ARG JAR_FILE=build/libs/*.jar

# Jar 파일 Docker에 복사
COPY ${JAR_FILE} cicd-demo-app.jar

ENTRYPOINT ["java","-jar","/app.jar"]