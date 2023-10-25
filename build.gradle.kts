import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }
}

plugins {
    val kotlinVersion = "1.9.10"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion

    id("org.springframework.boot") version "3.2.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.3"

    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("org.flywaydb.flyway") version "9.22.3"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"
    id("jacoco")
    id("com.palantir.docker") version "0.35.0" apply false
}

tasks.bootJar {
    enabled = false
}
tasks.jar {
    enabled = false
}

allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    group = "br.com.rfasioli"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "20"
        targetCompatibility = "20"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "20"
        }
    }
}

subprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    apply {
        plugin("io.spring.dependency-management")
        plugin("io.gitlab.arturbosch.detekt")
        plugin("org.jlleitschuh.gradle.ktlint")
    }
}

dependencies {
    implementation("org.postgresql:postgresql")
}
