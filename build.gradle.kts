plugins {
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "1.2.1" // Plugin Publishing Plugin
}

repositories {
    mavenCentral()
    gradlePluginPortal() // Recommended for plugin publishing
}

dependencies {
    implementation("com.google.gradle:osdetector-gradle-plugin:1.7.0")
    implementation("org.javamodularity:moduleplugin:1.8.12")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.named<Copy>("processResources") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

gradlePlugin {
    plugins {
        create("javafxPlugin") {
            id = "com.github.javafx-gradle-plugin"
            displayName = "JavaFX Gradle Plugin"
            description = "A Gradle plugin that simplifies working with JavaFX projects."
            implementationClass = "org.openjfx.gradle.JavaFXPlugin"
        }
    }
}

group = "com.github.raoulsson"
version = "0.0.17-raoulsson"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.github.raoulsson"
            artifactId = "javafx-gradle-plugin"
            version = "0.0.17-raoulsson"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/raoulsson/javafx-gradle-plugin")
            credentials {
                username = findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

pluginBundle {
    website = "https://github.com/openjfx/javafx-gradle-plugin"
    vcsUrl = "https://github.com/openjfx/javafx-gradle-plugin"
    tags = listOf("java", "javafx", "gradle-plugin")
}