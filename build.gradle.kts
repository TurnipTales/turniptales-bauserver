import org.gradle.api.JavaVersion.VERSION_21
import org.gradle.api.file.DuplicatesStrategy.INCLUDE

group = "net.turniptales"
version = "1.0.0"
description = "TurnipTales BuildingServer"

java.sourceCompatibility = VERSION_21

plugins {
    id("java-library")
    id("io.papermc.paperweight.userdev") version "1.7.1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.3.0"
}

repositories {
    mavenLocal()
}

dependencies {
    api("org.springframework", "spring-webflux", "6.1.10")
    api("com.google.code.gson", "gson", "2.11.0")

    compileOnly("org.projectlombok", "lombok", "1.18.34")
    annotationProcessor("org.projectlombok", "lombok", "1.18.34")

    paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<Javadoc> {
        options.encoding = "UTF-8"
    }

    shadowJar {
        archiveFileName.set("turniptales-buildingserver.jar")
    }

    processResources {
        from(sourceSets.main.get().resources.srcDirs) {
            filesMatching("paper-plugin.yml") {
                expand("version" to project.version)
            }
            duplicatesStrategy = INCLUDE
        }
    }

    runServer {
        minecraftVersion("1.20.6")
    }
}
