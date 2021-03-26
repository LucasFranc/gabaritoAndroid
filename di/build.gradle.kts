plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    id("br.com.lucasfranco.exemploarquitetura.plugin.android.library")
}

dependencies {

    implementation(Dependencies.KOIN)
    implementation(Dependencies.KOINSCOPE)
    implementation(Dependencies.KOINVIEWMODEL)
    implementation(Dependencies.KOINEXT)

    // DEPENDENCIES FROM FEATURES
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.OKHTTP)

    implementation(project(":gateway"))
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":util"))
    implementation(project(":model"))
}