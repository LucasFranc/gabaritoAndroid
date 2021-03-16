import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.provideDelegate
fun TestedExtension.configureAndroidGeneric(
    moreConfigure: (TestedExtension.() -> Unit)? = null,
    defaultConfig: (DefaultConfig.() -> Unit)? = null,
    releaseBuildTypeConfig: (BuildType.() -> Unit)? = null,
    homolBuildTypeConfig: (BuildType.() -> Unit)? = null,
    debugBuildTypeConfig: (BuildType.() -> Unit)? = null,
    project: Project
) {
    compileSdkVersion(AppConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(AppConfig.PROJECT_BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(AppConfig.MIN_SDK_VERSION)
        targetSdkVersion(AppConfig.TARGET_SDK_VERSION)

        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        defaultConfig?.invoke(this)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    signingConfigs {
        val buildTypeChoose: String by project
        when (buildTypeChoose) {
            "exampleRelease" -> {
                maybeCreate("release")
                getByName("release") {
                    storeFile = project.project(":app").file("../config/android_keystore.jks")
                    storePassword = "r4bhvp2h37"
                    keyAlias = "example"
                    keyPassword = "r4bhvp2h37"
                }
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isZipAlignEnabled = true
            isDebuggable = false
            ndk.debugSymbolLevel = "SYMBOL_TABLE"
            if (signingConfigs.names.contains("release")) {
                signingConfig = signingConfigs.getByName("release")
            }
            releaseBuildTypeConfig?.invoke(this)
        }

        val homolDefaultActionBuildType: BuildType.() -> Unit = {
            isMinifyEnabled = false
            isZipAlignEnabled = true
            isDebuggable = false
            if (signingConfigs.names.contains("debug")) {
                signingConfig = signingConfigs.getByName("debug")
            }
            homolBuildTypeConfig?.invoke(this)
        }
        if (buildTypes.names.contains("homol")) {
            getByName("homol", homolDefaultActionBuildType)
        } else {
            create("homol", homolDefaultActionBuildType)
        }

        getByName("debug") {
            isDebuggable = true
            if (signingConfigs.names.contains("debug")) {
                signingConfig = signingConfigs.getByName("debug")
            }
            debugBuildTypeConfig?.invoke(this)
        }
    }

    flavorDimensions("environment")
    productFlavors {
        maybeCreate("example")
    }

    variantFilter {
        val buildTypeChoose: String by project
        if (name != buildTypeChoose) {
            ignore = true
        }
    }

    moreConfigure?.invoke(this)
}