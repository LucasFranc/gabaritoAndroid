object Versions {
    const val ANDROIDX_TEST_EXT = "1.1.2"
    const val ANDROIDX_TEST = "1.3.0"
    const val APPCOMPAT = "1.2.0"
    const val CONSTRAINT_LAYOUT = "2.0.1"
    const val CORE_KTX = "1.3.1"
    const val COROUTINES = "1.3.9"
    const val ESPRESSO_CORE = "3.3.0"
    const val JUNIT = "4.13"
    const val KODEIN = "7.0.0"
    const val KOTLINX_SERIALIZATION = "1.0.0-RC"
    const val KTLINT = "0.39.0"
    const val KTLINT_PLUGIN = "9.4.1"
    const val DETEKT = "1.12.0"
    const val COROUTINES_VERSION = "1.3.9"
    const val LIFECYCLE_KTX = "2.2.0"
    const val MATERIAL_DESIGN = "1.2.0"
    const val NAVIGATION_ARGS = "1.0.0"
    const val OKHTTP_LOGGING_INTERCEPTOR = "4.8.1"
    const val PICASSO = "2.71828"
    const val RECYCLERVIEW = "1.1.0"
    const val RETROFIT = "2.9.0"
    const val RETROFIT_KOTLINX_SERIALIZATION_CONVERTER = "0.7.0"
    const val KOTLIN = "1.4.0"
    const val BEN_MANES_DEPENDENCY_VERSIONS = "0.33.0"
    const val GOOGLE_SERVICES_VERSION = "4.3.4"
    const val AGP = "4.1.1"
    const val LOTTIE_VERSION = "3.4.4"


    const val ANDROID_X_CORE_VERSION = "1.3.2"
    const val APP_COMPAT_VERSION = "1.2.0"
    const val ACTIVITY_KTX_VERSION = "1.1.0"
    const val ANDROIDX_FRAGMENT_KTX_VERSION = "1.2.5"
    const val CIRCLE_IMAGE_VIEW_VERSION = "3.1.0"
    const val CONSTRAINT_LAYOUT_VERSION = "2.0.2"
    const val GLIDE_VERSION = "4.11.0"
    const val GSON_VERSION = "2.8.6"
    const val OK_HTTP_VERSION = "4.9.0"
    const val RECYCLER_VERSION = "1.1.0"
    const val RETROFIT_VERSION = "2.9.0"
    const val LIFECYCLE_VERSION = "2.2.0"
    const val TIMBER_VERSION = "4.7.1"
    const val MATERIAL_VERSION = "1.2.1"
    const val KOIN_VERSION = "2.2.0-rc-2"
    const val NAVIGATION_VERSION = "2.3.0"
    const val ANNOTATION_VERSION = "1.1.0"
    const val SWIPE_REFRESH_LAYOUT_VERSION = "1.1.0"

    //TEST DEPENDENCIES
    const val MOCKITO_VERSION = "3.5.13"
    const val MOCKK_VERSION = "1.10.2"
    const val COROUTINES_TEST_VERSION = "1.3.9"
    const val J_UNIT_VERSION = "4.13"
    const val MOCKITO_KOTLIN_VERSION = "2.2.0"
    const val TEST_RULES_VERSION = "1.3.0"
    const val TEST_RUNNER_VERSION = "1.3.0"

    //PDF VIEWER
    const val PDF_VIEWER = "2.8.2"
}

object Dependencies {
    //jetbrains
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"
    const val ANDROIDXCORE = "androidx.core:core-ktx:${Versions.ANDROID_X_CORE_VERSION}"
    const val ACTIVITYKTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX_VERSION}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
    const val FRAGMENTKTX = "androidx.fragment:fragment-ktx:${Versions.ANDROIDX_FRAGMENT_KTX_VERSION}"
    const val RECYCLER = "androidx.recyclerview:recyclerview:${Versions.RECYCLER_VERSION}"
    const val LOTTIE = "com.airbnb.android:lottie:${Versions.LOTTIE_VERSION}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VERSION}"
    const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_VERSION}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime:${Versions.LIFECYCLE_VERSION}"
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_VERSION}"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_VERSION}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    const val ANNOTATION = "androidx.annotation:annotation:${Versions.ANNOTATION_VERSION}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON_VERSION}"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_LAYOUT_VERSION}"

    //square
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OK_HTTP_VERSION}"
    const val OKHTTPLOGINTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OK_HTTP_VERSION}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val RETROFITSCALARS = "com.squareup.retrofit2:converter-scalars:${Versions.RETROFIT_VERSION}"
    const val RETROFITGSONCONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSION}"
    const val RETROFITRXADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT_VERSION}"

    //others
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER_VERSION}"
    // Todo Temporary for toolbar compatibility only digio
    const val CIRCLE_IMAGE_VIEW = "de.hdodenhof:circleimageview:${Versions.CIRCLE_IMAGE_VIEW_VERSION}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE_VERSION}"
    // DI
    const val KOIN = "org.koin:koin-android:${Versions.KOIN_VERSION}"
    const val KOINSCOPE = "org.koin:koin-android-scope:${Versions.KOIN_VERSION}"
    const val KOINVIEWMODEL = "org.koin:koin-android-viewmodel:${Versions.KOIN_VERSION}"
    const val KOINEXT = "org.koin:koin-android-ext:${Versions.KOIN_VERSION}"
    // Navigation
    const val NAV_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_VERSION}"
    const val NAV_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_VERSION}"
    const val NAV_DYNAMIC_FEATURE = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.NAVIGATION_VERSION}"
    const val PDF_VIEWER = "com.github.barteksc:android-pdf-viewer:${Versions.PDF_VIEWER}"
}

object TestDependencies {
    const val JUNIT = "junit:junit:${Versions.J_UNIT_VERSION}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK_VERSION}"

    //test androidx
    const val TESTRULES = "androidx.test:rules:${Versions.TEST_RULES_VERSION}"
    const val TESTRUNNER = "androidx.test:runner:${Versions.TEST_RUNNER_VERSION}"
    //test mockito
    const val MOCKITOCORE = "org.mockito:mockito-core:${Versions.MOCKITO_VERSION}"
    const val MOCKITOINLINE = "org.mockito:mockito-inline:${Versions.MOCKITO_VERSION}"

    //test others
    const val MOCKITOKOTLIN =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.MOCKITO_KOTLIN_VERSION}"
    const val COROUTINESTEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_TEST_VERSION}"
}
