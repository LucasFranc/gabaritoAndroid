plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("br.com.lucasfranco.exemploarquitetura.plugin.android")
}

configureAndroid(
        moreConfigure = {
            packagingOptions {
                exclude("META-INF/DEPENDENCIES")
                exclude("META-INF/LICENSE")
                exclude("META-INF/LICENSE.txt")
                exclude("META-INF/license.txt")
                exclude("META-INF/NOTICE")
                exclude("META-INF/NOTICE.txt")
                exclude("META-INF/notice.txt")
                exclude("META-INF/AL2.0")
                exclude("META-INF/ASL2.0")
                exclude("META-INF/LGPL2.1")
                exclude("META-INF/*.kotlin_module")
            }
        }
)


dependencies {

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.ANDROIDXCORE)
    implementation(Dependencies.ACTIVITYKTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.FRAGMENTKTX)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.KOIN)
    implementation(Dependencies.KOINSCOPE)
    implementation(Dependencies.ANNOTATION)
    implementation(Dependencies.KOINEXT)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.KOINVIEWMODEL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.NAV_FRAGMENT)
    implementation(Dependencies.NAV_UI)
    implementation(Dependencies.NAV_DYNAMIC_FEATURE)

    testImplementation(TestDependencies.TESTRULES)
    testImplementation(TestDependencies.TESTRUNNER)
    testImplementation(TestDependencies.MOCKITOCORE)
    testImplementation(TestDependencies.MOCKITOINLINE)
    testImplementation(TestDependencies.MOCKITOKOTLIN)
    testImplementation(TestDependencies.COROUTINESTEST)

    androidTestImplementation(TestDependencies.JUNIT)

    implementation(Dependencies.KOIN)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.KOINSCOPE)
    implementation(Dependencies.KOINVIEWMODEL)
    implementation(Dependencies.KOINEXT)
    implementation(Dependencies.CIRCLE_IMAGE_VIEW)

    implementation(project(":di"))

}
android {
    dynamicFeatures = mutableSetOf(":login")
}
