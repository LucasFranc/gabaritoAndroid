import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.delegateClosureOf

class AndroidModuleDynamicLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        if (project.hasProperty("android")) {
            with(project) {
                configureAndroidDynamicLibrary()
            }
        }
    }
}

fun Project.configureAndroidDynamicLibrary(
    suffix: String = name,
    moreConfigure: (AppExtension.() -> Unit)? = null,
    defaultConfig: (DefaultConfig.() -> Unit)? = null,
    releaseBuildTypeConfig: (BuildType.() -> Unit)? = null,
    homolBuildTypeConfig: (BuildType.() -> Unit)? = null,
    debugBuildTypeConfig: (BuildType.() -> Unit)? = null
) {
    configure<AppExtension> {
        configureAndroidGeneric(
            project = this@configureAndroidDynamicLibrary,
            defaultConfig = {
                applicationId = "${AppConfig.APP_ID}${if (suffix.isNotEmpty()) "." else String()}$suffix"
                defaultConfig?.invoke(this)
            },
            releaseBuildTypeConfig = {
                releaseBuildTypeConfig?.invoke(this)
            },
            homolBuildTypeConfig = {
                versionNameSuffix = "-homol"
                homolBuildTypeConfig?.invoke(this)
            },
            debugBuildTypeConfig = {
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

                moreConfigure?.invoke(this as AppExtension)
            }
        )
        dataBinding {
            isEnabled = true
        }
        buildFeatures.viewBinding = true
    }
}