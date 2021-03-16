version = AppConfig.VERSION_CODE

plugins {
    id("java-library")
    kotlin("jvm")
    id("kotlin-android-extensions")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.GSON)
}