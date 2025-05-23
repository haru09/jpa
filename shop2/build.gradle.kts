plugins {
    id("org.springframework.boot") version "3.2.5"           // Spring Boot 플러그인
    id("io.spring.dependency-management") version "1.1.4"    // 의존성 일괄 관리
    java
}

group = "com"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // 웹, 템플릿, JPA, 보안
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    // H2 데이터베이스
    runtimeOnly("com.h2database:h2")
    // logback-classic (Spring Boot Starter에서 포함되므로 생략 가능)
    implementation("ch.qos.logback:logback-classic")
    // 테스트 (JUnit 5), spring-boot-starter-test에 이미 포함됨, 명시적 지정 가능
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.springframework.security:spring-security-test")

    developmentOnly("org.springframework.boot:spring-boot-devtools") // 템플릿(HTML 등)은 수정 즉시 반영, Java 소스 변경은 서버 자동 재시작
}

tasks.withType<Test> {
    useJUnitPlatform()
}