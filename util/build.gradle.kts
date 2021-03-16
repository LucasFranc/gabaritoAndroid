plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    id("br.com.lucasfranco.exemploarquitetura.plugin.android.library")
}


configureAndroidLibrary(
    releaseBuildTypeConfig = {
    },
    homolBuildTypeConfig = {
    },
    debugBuildTypeConfig = {
    }
)

dependencies {
    implementation(Dependencies.ANDROIDXCORE)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.GLIDE)
    implementation(Dependencies.RETROFITGSONCONVERTER)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.GSON)

    implementation(project(":model"))

    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.MOCKK)
}