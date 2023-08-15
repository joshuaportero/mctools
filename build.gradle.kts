import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "xyz.feuxy.neon"
version = "1.3.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

tasks.register<Copy>("updatePluginYml") {
    from(sourceSets.main.get().resources.srcDirs) {
        include("**/plugin.yml")
        expand("version" to project.version)
    }
    into(File(buildDir, "resources/main"))
}

tasks.named<ShadowJar>("shadowJar") {
    dependsOn("updatePluginYml")
    archiveFileName.set("Neon.jar")
}