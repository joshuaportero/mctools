import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.io.ByteArrayOutputStream

plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "xyz.feuxy.neon"
version = "3.0.0-SNAPSHOT"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    compileOnly("org.jetbrains:annotations:24.0.0")

    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

val determinePatchVersion: () -> Int = {
    val tagInfo = ByteArrayOutputStream()
    try {
        exec {
            commandLine("git", "describe", "--tags")
            standardOutput = tagInfo
        }
        if (!tagInfo.toString().contains("-")) {
            0
        } else {
            tagInfo.toString().split("-")[1].toInt()
        }
    } catch (e: Exception) {
        0
    }
}

val majorVersion = "3"
val minorVersion = "0"
val patchVersion = determinePatchVersion()
val apiVersion = "$majorVersion.$minorVersion"
val fullVersion = "$apiVersion.$patchVersion-BETA"

tasks.register<Copy>("updatePluginYml") {
    from(sourceSets.main.get().resources.srcDirs) {
        include("**/plugin.yml")
        expand("version" to fullVersion)
    }
    into(File(buildDir, "resources/main"))
}

tasks.named<ShadowJar>("shadowJar") {
    dependsOn("updatePluginYml")
    archiveFileName.set("${project.name}-${fullVersion}.jar")
}