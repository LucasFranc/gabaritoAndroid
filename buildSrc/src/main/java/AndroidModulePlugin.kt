import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.delegateClosureOf

class AndroidModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        if (project.hasProperty("android")) {
            with(project) {
                configureAndroid()
            }
        }
    }
}

fun Project.configureAndroid(
    moreConfigure: (BaseAppModuleExtension.() -> Unit)? = null,
    defaultConfig: (DefaultConfig.() -> Unit)? = null,
    releaseBuildTypeConfig: (BuildType.() -> Unit)? = null,
    homolBuildTypeConfig: (BuildType.() -> Unit)? = null,
    debugBuildTypeConfig: (BuildType.() -> Unit)? = null
) {
    configure<BaseAppModuleExtension> {
        configureAndroidGeneric(
            project = this@configureAndroid,
            defaultConfig = {
                applicationId = AppConfig.APP_ID
                bundle {
                    language{
                        enableSplit = false
                    }
                }
                defaultConfig?.invoke(this)
                ndk {
                    abiFilters.addAll(listOf("armeabi-v7a", "x86", "x86_64", "arm64-v8a"))
                }
            },
            releaseBuildTypeConfig = {
                resValue("string", "app_name", "@string/app_name_main")

                resValue("drawable", "launch", "@mipmap/ic_launcher")
                resValue("drawable", "launch_round", "@mipmap/ic_launcher_round")

                releaseBuildTypeConfig?.invoke(this)
            },
            homolBuildTypeConfig = {
                resValue("string", "app_name", "@string/app_name_homol")

                resValue("drawable", "launch", "@mipmap/ic_launcher_homol")
                resValue("drawable", "launch_round", "@mipmap/ic_launcher_round_homol")

                versionNameSuffix = "-homol"
                homolBuildTypeConfig?.invoke(this)
            },
            debugBuildTypeConfig = {
                resValue("string", "app_name", "@string/app_name_dev")

                resValue("drawable", "launch", "@mipmap/ic_launcher_dev")
                resValue("drawable", "launch_round", "@mipmap/ic_launcher_round_dev")

                versionNameSuffix = "-dev"
                applicationIdSuffix = ".dev"
                debugBuildTypeConfig?.invoke(this)
            },
            moreConfigure = {
                tasks.withType(
                    org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java,
                    delegateClosureOf<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
                        kotlinOptions {
                            jvmTarget = "1.8"
                        }
                    }
                )

                configure<org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension> {
                    isExperimental = true
                }

                moreConfigure?.invoke(this as BaseAppModuleExtension)
            }
        )
        buildFeatures.dataBinding = true
    }
}