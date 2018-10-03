plugins {
    java
}

group = "com.innoq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile("org.apache.httpcomponents", "httpclient", "4.5.6")
    compile("com.squareup.okhttp3", "okhttp", "3.11.0")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}