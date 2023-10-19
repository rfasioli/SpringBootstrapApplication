import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

springBoot {
    buildInfo()
}

tasks.bootJar {
//    enabled = true
    enabled = false
}

tasks.jar {
    enabled = false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
