val ktorVersion = "2.3.5"
val logbackVersion = "1.4.11"
val jwtVersion = "4.4.0"

plugins {
    kotlin("jvm") version "1.9.10"                  // Kotlin
    kotlin("plugin.serialization") version "1.9.10" // Para @Serializable
    application                                     // Para ejecutar la app
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // Kotlin Test
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("backend.AppKt") // Cambia al paquete correcto de tu App.kt
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
