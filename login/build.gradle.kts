/* Apply Module base configurations */
plugins {
    id("com.android.dynamic-feature")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
    id("br.com.lucasfranco.exemploarquitetura.plugin.android.dynamic.library")
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.ANDROIDXCORE)
    implementation(Dependencies.ACTIVITYKTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.FRAGMENTKTX)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.KOIN)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.KOINSCOPE)
    implementation(Dependencies.KOINVIEWMODEL)
    implementation(Dependencies.KOINEXT)
    implementation(Dependencies.NAV_FRAGMENT)
    implementation(Dependencies.NAV_UI)
    implementation(Dependencies.CIRCLE_IMAGE_VIEW)

    implementation(project(":app"))
    implementation(project(":common"))
    implementation(project(":model"))
    implementation(project(":util"))
}