/* Apply Module base configurations */
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
    id("br.com.lucasfranco.exemploarquitetura.plugin.android.library")
}

dependencies {
    kapt(Dependencies.GLIDE_COMPILER)

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.ANDROIDXCORE)
    implementation(Dependencies.ACTIVITYKTX)
    implementation(Dependencies.LIFECYCLE_LIVEDATA)
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.LOTTIE)
    implementation(Dependencies.FRAGMENTKTX)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.KOIN)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.KOINSCOPE)
    implementation(Dependencies.KOINVIEWMODEL)
    implementation(Dependencies.KOINEXT)
    implementation(Dependencies.GLIDE)
    implementation(Dependencies.NAV_FRAGMENT)
    implementation(Dependencies.NAV_UI)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)

    implementation(project(":util"))
    implementation(project(":model"))
    implementation(project(":domain"))

    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.MOCKK)
    testImplementation(TestDependencies.COROUTINESTEST)
}