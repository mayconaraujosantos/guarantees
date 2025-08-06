import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
    kotlin("plugin.jpa") version "1.9.20"
    id("io.gitlab.arturbosch.detekt") version "1.24.0"
    id("org.sonarqube") version "4.4.1.3373"

    id("tech.mappie.plugin") version "1.1.1"
}

group = "com.finapp"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Database
    implementation("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")
    implementation("org.flywaydb:flyway-core")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Mappie API
    implementation("tech.mappie:mappie-api:1.1.1")

    // Monitoring & Observability
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")

    // Architecture Tests
    testImplementation("com.tngtech.archunit:archunit-junit5:1.1.0")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Detekt configuration
detekt {
    config.from(files("detekt-config.yml"))
    buildUponDefaultConfig = true
}

// SonarQube configuration
sonarqube {
    properties {
        property("sonar.projectKey", "finapp-guarantee-card-receivables-service")
        property("sonar.organization", "finapp")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

// Mappie configuration
mappie {
    useDefaultArguments = true
    strictness {
        visibility = false
        enums = true
    }
}

