val kotlinVersion = "1.9.10"
val ktorVersion = "2.3.5"
val logbackVersion = "1.4.11"
val jwtVersion = "4.4.0"

plugins {
    kotlin("jvm") version "1.9.10"             // Kotlin
    kotlin("plugin.serialization") version "1.9.10" // Para @Serializable
    application                                     // Para ejecutar la app
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor
    implementation(platform("io.ktor:ktor-bom:$ktor_version"))
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-netty")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation(libs.testng)

    // 🔹 Dependencias de testing
    testImplementation("io.ktor:ktor-server-tests-jvm:${ktorVersion}")
    //testImplementation("io.ktor:ktor-server-test-host:${ktorVersion}")
    testImplementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    testImplementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:${kotlinVersion}")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    implementation(kotlin("test"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("backend.AppKt") // Ajusta al paquete correcto de tu App.kt
}

// ShadowJar actualizado para Gradle 7+
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    manifest {
        attributes(
            "Main-Class" to "backend.AppKt"
        )
    }
}

tasks.test {
    useJUnitPlatform()
}
