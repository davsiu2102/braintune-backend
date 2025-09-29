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
    // Ktor - Bill of Materials (para manejar versiones automáticamente)
    implementation(platform("io.ktor:ktor-bom:$ktor_version"))

    // Ktor - Módulos necesarios para el backend
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-auth-jwt")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Dependencias de Testing
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
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
