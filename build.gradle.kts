import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.9.10"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion

    id("org.springframework.boot") version "3.2.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.3"

    id("io.gitlab.arturbosch.detekt") version "1.20.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("org.flywaydb.flyway") version "8.5.11"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"
    id("jacoco")
    id("com.palantir.docker") version "0.33.0" apply false
}

group = "br.com.rfasioli"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_20
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
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "20"
        }
    }
}

dependencies {
    implementation("org.postgresql:postgresql")
}
