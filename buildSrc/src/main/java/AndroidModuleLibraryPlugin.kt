import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.delegateClosureOf

class AndroidModuleLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        if (project.hasProperty("android")) {
            with(project) {
                configureAndroidLibrary()
            }
        }
    }
}

fun Project.configureAndroidLibrary(
    moreConfigure: (LibraryExtension.() -> Unit)? = null,
    defaultConfig: (DefaultConfig.() -> Unit)? = null,
    releaseBuildTypeConfig: (BuildType.(libraryExtension: LibraryExtension) -> Unit)? = null,
    homolBuildTypeConfig: (BuildType.(libraryExtension: LibraryExtension) -> Unit)? = null,
    debugBuildTypeConfig: (BuildType.(libraryExtension: LibraryExtension) -> Unit)? = null
) {
    configure<LibraryExtension> {
        configureAndroidGeneric(
            project = this@configureAndroidLibrary,
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

                moreConfigure?.invoke(this as LibraryExtension)
            },
            releaseBuildTypeConfig = {
                releaseBuildTypeConfig?.invoke(this, this@configure)
            },
            homolBuildTypeConfig = {
                homolBuildTypeConfig?.invoke(this, this@configure)
            },
            debugBuildTypeConfig = {
                debugBuildTypeConfig?.invoke(this, this@configure)
            },
            defaultConfig = defaultConfig
        )
        buildFeatures.dataBinding = true
    }
}