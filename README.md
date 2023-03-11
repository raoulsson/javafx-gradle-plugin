# Temporary Fix for Kotlin 1.8 Compatibility

Fixes issues I run into on my M1 Mac and Kotlin 1.8+. The original project is [here](https://github.com/openjfx/javafx-gradle-plugin) and we expect them to do a similar fix soon. So make sure to check back later and switch over again.

In the meantime, hope this may help.

### Run EITHER directly from github [jitpack.io](https://jitpack.io/#raoulsson/javafx-gradle-plugin/v0.0.15-raoulsson) repo:


Add to your settings.gradle.kts:

    pluginManagement {
        // https://github.com/jitpack/jitpack.io/issues/1459
        resolutionStrategy {
            eachPlugin {
                if(requested.id.toString() == "com.github.javafx-gradle-plugin")
                    useModule("com.github.raoulsson:javafx-gradle-plugin:v0.0.15-raoulsson")
            }
        }
        repositories {
            maven("https://jitpack.io")
            mavenCentral()
            gradlePluginPortal()
        }
    }

And use it in your build.gradle.kts:

    plugins {
        id("com.github.javafx-gradle-plugin") version "v0.0.15-raoulsson"
    }

### ...OR, clone and build and run locally:

You can glone this repo and run 

    ./gradlew publishToMavenLocal

Then, in your actual project, make sure to defined the local .m2 repo in settings.gradle.kts:

    pluginManagement {
    repositories {
        mavenLocal() // <--- add this
        mavenCentral()
        gradlePluginPortal()
    }
}

Then, in your actual project, use it by changing the plugin setup from

    id("org.openjfx.javafxplugin") version "0.0.13"

to 

    id("com.github.javafx-gradle-plugin") version "0.0.15-raoulsson"

Go back later to original project and use their releases > 0.0.13, if it works.

--- 

# JavaFX Gradle Plugin

Simplifies working with JavaFX 11+ for gradle projects.

[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/org/openjfx/javafxplugin/org.openjfx.javafxplugin.gradle.plugin/maven-metadata.xml.svg?label=Gradle%20Plugin)](https://plugins.gradle.org/plugin/org.openjfx.javafxplugin)
[![Travis CI](https://api.travis-ci.com/openjfx/javafx-gradle-plugin.svg?branch=master)](https://travis-ci.com/openjfx/javafx-gradle-plugin)
[![BSD-3 license](https://img.shields.io/badge/license-BSD--3-%230778B9.svg)](https://opensource.org/licenses/BSD-3-Clause)

## Getting started

To use the plugin, apply the following two steps:

### 1. Apply the plugin

##### Using the `plugins` DSL:

**Groovy**

    plugins {
        id 'org.openjfx.javafxplugin' version '0.0.13'
    }

**Kotlin**

    plugins {
        id("org.openjfx.javafxplugin") version "0.0.13"
    }

##### Alternatively, you can use the `buildscript` DSL:

**Groovy**

    buildscript {
        repositories {
            maven {
                url "https://plugins.gradle.org/m2/"
            }
        }
        dependencies {
            classpath 'org.openjfx:javafx-plugin:0.0.13'
        }
    }
    apply plugin: 'org.openjfx.javafxplugin'

**Kotlin**

    buildscript {
        repositories {
            maven {
                setUrl("https://plugins.gradle.org/m2/")
            }
        }
        dependencies {
            classpath("org.openjfx:javafx-plugin:0.0.13")
        }
    }
    apply(plugin = "org.openjfx.javafxplugin")


### 2. Specify JavaFX modules

Specify all the JavaFX modules that your project uses:

**Groovy**

    javafx {
        modules = [ 'javafx.controls', 'javafx.fxml' ]
    }

**Kotlin**

    javafx {
        modules("javafx.controls", "javafx.fxml")
    }
    
### 3. Specify JavaFX version

To override the default JavaFX version, a version string can be declared.
This will make sure that all the modules belong to this specific version:

**Groovy**

    javafx {
        version = '12'
        modules = [ 'javafx.controls', 'javafx.fxml' ]
    }

**Kotlin**

    javafx {
        version = "12"
        modules("javafx.controls", "javafx.fxml")
    }

### 4. Cross-platform projects and libraries

JavaFX modules require native binaries for each platform. The plugin only
includes binaries for the platform running the build. By declaring the 
dependency configuration **compileOnly**, the native binaries will not be 
included. You will need to provide those separately during deployment for 
each target platform.

**Groovy**

    javafx {
        version = '12'
        modules = [ 'javafx.controls', 'javafx.fxml' ]
        configuration = 'compileOnly'
    }

**Kotlin**

    javafx {
        version = "12"
        modules("javafx.controls", "javafx.fxml")
        configuration = "compileOnly"
    }

### 5. Using a local JavaFX SDK

By default, JavaFX modules are retrieved from Maven Central. 
However, a local JavaFX SDK can be used instead, for instance in the case of 
a custom build of OpenJFX.

Setting a valid path to the local JavaFX SDK will take precedence:

**Groovy**

    javafx {
        sdk = '/path/to/javafx-sdk'
        modules = [ 'javafx.controls', 'javafx.fxml' ]
    }

**Kotlin**

    javafx {
        sdk = "/path/to/javafx-sdk"
        modules("javafx.controls", "javafx.fxml")
    }
    
## Issues and Contributions ##

Issues can be reported to the [Issue tracker](https://github.com/openjfx/javafx-gradle-plugin/issues/).

Contributions can be submitted via [Pull requests](https://github.com/openjfx/javafx-gradle-plugin/pulls/), 
providing you have signed the [Gluon Individual Contributor License Agreement (CLA)](https://cla.gluonhq.com/).
