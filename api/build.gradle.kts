plugins {
    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
}

springBoot {
    buildInfo()
}

tasks.bootJar {
    enabled = true
}

tasks.jar {
    enabled = false
}

val archunitVersion = "1.1.0"
val coroutinesVersion = "1.7.3"
val fixtureVersion = "1.2.0"
val h2Version = "2.1.214"
val kotlinFakerVersion = "1.11.0"
val r2dbcH2Version = "1.0.0.RELEASE"
val serializationCoreVersion = "1.6.0"
val springDocVersion = "1.7.0"
val springmockkVersion = "3.1.1"

//val datetimeVersion = "0.4.0"
//val uuidVersion = "0.0.15"

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationCoreVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    runtimeOnly("org.postgresql:postgresql")

    implementation("org.springdoc:springdoc-openapi-webflux-ui:$springDocVersion")
    implementation("org.springdoc:springdoc-openapi-kotlin:$springDocVersion")

//    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
//    implementation("org.springframework.cloud:spring-cloud-stream")
//    implementation("org.springframework.cloud:spring-cloud-stream-binder-rabbit")

    implementation("org.springframework.boot:spring-boot-properties-migrator:3.1.5")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.tngtech.archunit:archunit-junit5:$archunitVersion")
//    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
//    testImplementation("org.springframework.cloud:spring-cloud-stream-test-support")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("com.ninja-squad:springmockk:$springmockkVersion")
    testImplementation("io.github.serpro69:kotlin-faker:$kotlinFakerVersion")
    testImplementation("com.appmattus.fixture:fixture:$fixtureVersion")
    testImplementation("com.appmattus.fixture:fixture-generex:$fixtureVersion")
    testImplementation("com.h2database:h2:$h2Version")
    testImplementation("io.r2dbc:r2dbc-h2:$r2dbcH2Version")
}

tasks.test {
    useJUnitPlatform()
}
