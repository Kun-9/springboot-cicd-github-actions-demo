plugins {
	java
	id("org.springframework.boot") version "3.3.6"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	//implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
	compileOnly("org.projectlombok:lombok")
	//runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.4")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// 프로메테우스
	implementation("io.micrometer:micrometer-registry-prometheus:1.14.2")

	// https://mvnrepository.com/artifact/software.amazon.awssdk/secretsmanager
	implementation("software.amazon.awssdk:secretsmanager:2.29.43")

	// https://mvnrepository.com/artifact/software.amazon.awssdk/kms
	implementation("software.amazon.awssdk:kms:2.29.43")

	// https://mvnrepository.com/artifact/software.amazon.awssdk/aws-core
	implementation("software.amazon.awssdk:aws-core:2.29.45")

	// https://mvnrepository.com/artifact/com.amazonaws/aws-encryption-sdk-java
	implementation("com.amazonaws:aws-encryption-sdk-java:3.0.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
