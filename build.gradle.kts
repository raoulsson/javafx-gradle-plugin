import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `java-gradle-plugin`
    `maven-publish`
//    id("com.gradle.build-scan") version "2.1"
//    id("java-gradle-plugin")
//    id("com.github.hierynomus.license") version "0.15.0"
//    id("com.gradle.plugin-publish") version "0.12.0"
//    id("com.github.ben-manes.versions") version "0.20.0"
//    id("maven-publish")
}

//buildScan {
//    termsOfServiceUrl   = "https://gradle.com/terms-of-service"
//    termsOfServiceAgree = "yes"
//}


repositories {
    mavenCentral()
    maven(
        "https://plugins.gradle.org/m2/"
    )
}

dependencies {
//    implementation gradleApi()

    implementation("com.google.gradle:osdetector-gradle-plugin:1.7.0")
    implementation("org.javamodularity:moduleplugin:1.8.12")

//    testImplementation gradleTestKit()
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

tasks.named<Copy>("processResources") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

gradlePlugin {
    plugins {
        create("javafxPlugin") {
            id = "com.github.javafx-gradle-plugin"
            displayName = "JavaFX Gradle Plugin - raoulsson-tweak"
            description = "Plugin that makes it easy to work with JavaFX"
            implementationClass = "org.openjfx.gradle.JavaFXPlugin"
        }
    }
}

//pluginBundle {
//    website = "https://github.com/openjfx/javafx-gradle-plugin"
//    vcsUrl = "https://github.com/openjfx/javafx-gradle-plugin"
//    tags = listOf( "java", "javafx" )
//}

group = "com.github.raoulsson"
version = "0.0.17-raoulsson"

publishing {
    publications {
        create<MavenPublication>("maven"){
            groupId = "com.github.raoulsson"
            artifactId = "javafx-gradle-plugin"
            version = "0.0.17-raoulsson"

            from(components["java"])
        }
    }
    repositories {
        maven {
            // change to point to your repo, e.g. http://my.org/repo
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
//    repositories {
//        maven {
//            if (project.hasProperty("sonatypeUsername") && project.hasProperty("sonatypePassword")) {
//                credentials {
//                    username project.property("sonatypeUsername")
//                    password project.property("sonatypePassword")
//                }
//                url = "https://oss.sonatype.org/content/repositories/snapshots/"
//            }
//        }
//    }
}

//license {
//    skipExistingHeaders = true
//    mapping {
//        java = "SLASHSTAR_STYLE"
//    }
//}
