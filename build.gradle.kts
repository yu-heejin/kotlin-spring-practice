import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// 미리 구성해둔 task들의 그룹
// 빌드 과정에서 필요한 정보들을 포함하고 있으며, 필요에 따라 커스터마이징이 가능하다.
plugins {
	val kotlinVersion = "1.8.22"

	id("org.springframework.boot") version "3.1.8"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

// 의존성 관리
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-validation")
}

// 플러그인에서 오픈해주는 것 외에 추가로 오픈해주는 것을 명시
allOpen {
	annotation("jakarta.persistence.Entity")
}

// 매개변수가 없는 생성자를 자동으로 추가함
noArg {
	annotation("jakarta.persistence.Entity")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}
