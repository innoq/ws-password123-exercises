plugins {
    java
    id("org.springframework.boot") version "2.0.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.5.RELEASE"
}

group = "com.innoq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile("com.sendgrid:sendgrid-java:4.2.1")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}