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
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.KOINSCOPE)
    implementation(Dependencies.KOINVIEWMODEL)
    implementation(Dependencies.KOINEXT)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.GSON)

    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.MOCKK)
    testImplementation(TestDependencies.COROUTINESTEST)

    implementation(project(":model"))
    implementation(project(":util"))
}