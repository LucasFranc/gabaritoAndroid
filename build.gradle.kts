
plugins {
    id("io.gitlab.arturbosch.detekt") version Versions.DETEKT
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT_PLUGIN
    id("com.github.ben-manes.versions") version Versions.BEN_MANES_DEPENDENCY_VERSIONS
}

buildscript{
    dependencies {
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION_ARGS}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("com.github.ben-manes:gradle-versions-plugin:${Versions.BEN_MANES_DEPENDENCY_VERSIONS}")
        classpath("com.google.gms:google-services:${Versions.GOOGLE_SERVICES_VERSION}")
        classpath("com.android.tools.build:gradle:${Versions.AGP}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    ktlint {
        debug.set(false)
        version.set(Versions.KTLINT)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    detekt {
        config = rootProject.files("config/detekt/detekt.yml")
        reports {
            html {
                enabled = true
                destination = file("build/reports/detekt.html")
            }
        }
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}