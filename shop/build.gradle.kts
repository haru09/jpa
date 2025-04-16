plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.shop"
version = "0.0.1-SNAPSHOT"

var lombok = "org.projectlombok:lombok:1.18.30"

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
	//Spring Data JPA 및 Hibernate를 통해 데이터베이스와 연동할 수 있도록 지원
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	//서버 사이드 템플릿 엔진인 Thymeleaf를 이용한 뷰 렌더링 기능을 제공
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

	//RESTful 웹 서비스를 구축하기 위한 기능(내장 톰캣, Spring MVC 등)을 제공
	implementation("org.springframework.boot:spring-boot-starter-web")

	//코드 보일러플레이트(게터, 세터, 생성자 등)를 줄여주는 라이브러리로 컴파일 시에만 필요한 의존성
	compileOnly(lombok)            // 컴파일만 할 수 있게 가져오고(컴파일시 @Getter, @Setter등 에러 안나게)
	annotationProcessor(lombok)    // 컴파일할 때 @Getter 같은 거 해석하게 함(실제 어노테이션을 읽고 실행)

	//경량의 인메모리 데이터베이스
	runtimeOnly("com.h2database:h2")

	//MySQL과 연결할 수 있는 드라이버
	runtimeOnly("com.mysql:mysql-connector-j")

	//JUnit, Mockito 등 Spring Boot 기반 테스트를 위한 종합적인 테스트 의존성
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//JUnit 5의 테스트 실행을 위한 런처 역할을 하는 라이브러리
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
