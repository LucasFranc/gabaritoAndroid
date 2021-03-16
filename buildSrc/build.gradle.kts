plugins {
    `kotlin-dsl`
}
repositories {
    jcenter()
    google()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
    implementation("com.android.tools.build:gradle:4.1.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.10")

    implementation(gradleApi())
    implementation(localGroovy())
}

allprojects {
    configurations.all {
        resolutionStrategy {
            force("org.objenesis:objenesis:2.6")
        }
    }
}