/* Apply Module base configurations */
plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    id("br.com.lucasfranco.exemploarquitetura.plugin.android.library")

}

val apiKey: String by project
val debugApiKey: String by project
val homolApiKey: String by project

configureAndroidLibrary(
    releaseBuildTypeConfig = {
        buildConfigField("Boolean", "SHOW_HTTP_LOGGIN", "false")
        buildConfigField("Boolean", "SHOW_HTTP_LOGGIN_ENCRYPTED", "false")
        buildConfigField("Boolean", "SSL_PINNING_ENABLE", "true")
        buildConfigField("String", "API_KEY", apiKey)
    },
    homolBuildTypeConfig = {
        buildConfigField("Boolean", "SHOW_HTTP_LOGGIN", "true")
        buildConfigField("Boolean", "SHOW_HTTP_LOGGIN_ENCRYPTED", "true")
        buildConfigField("Boolean", "SSL_PINNING_ENABLE", "true")
        buildConfigField("String", "API_KEY", homolApiKey)
    },
    debugBuildTypeConfig = {
        buildConfigField("Boolean", "SHOW_HTTP_LOGGIN", "true")
        buildConfigField("Boolean", "SHOW_HTTP_LOGGIN_ENCRYPTED", "true")
        buildConfigField("Boolean", "SSL_PINNING_ENABLE", "false")
        buildConfigField("String", "API_KEY", debugApiKey)
    }
)

dependencies {

    implementation(Dependencies.KOIN)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTPLOGINTERCEPTOR)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFITSCALARS)
    implementation(Dependencies.RETROFITGSONCONVERTER)
    implementation(Dependencies.RETROFITRXADAPTER)
    implementation(Dependencies.TIMBER)

    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.MOCKK)

    // MODULE
    implementation(project(":util"))
    implementation(project(":model"))
}