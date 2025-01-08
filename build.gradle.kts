import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import xyz.jpenilla.runpaper.task.RunServer

plugins {
    id("java-library")
    id("com.gradleup.shadow") version "9.0.0-beta4"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "dev.portero.mctools"
version = "5.0.0-SNAPSHOT"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
    maven {
        name = "PaperMC"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "Panda-Lang"
        url = uri("https://repo.panda-lang.org/releases")
    }
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    implementation("org.jetbrains:annotations:24.0.0")
    implementation("dev.rollczi:litecommands-bukkit:3.9.5")
    implementation("dev.rollczi:litecommands-adventure:3.9.5")
    implementation("dev.triumphteam:triumph-gui:3.1.11")
}

bukkit {
    main = "dev.portero.tools.ToolsPlugin"
    version = project.version.toString()
    apiVersion = "1.21.4"
    description = "Tools is development kit for Minecraft plugins. Do not use this plugin in production environment."
    website = "https://joshua.portero.dev/"
    authors = listOf("Portero")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("-Xlint:unchecked", "-Xlint:deprecation", "-parameters"))
    options.encoding = "UTF-8"
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("Tools-${project.version}")
    archiveVersion.set("")
    archiveClassifier.set("")

    relocate("dev.rollczi.litecommands", "dev.portero.atlas.commands")
    relocate("dev.triumphteam.gui", "dev.portero.atlas.triumphgui")
}

tasks.withType<RunServer> {
    minecraftVersion("1.21.4")
}