plugins {
    kotlin("jvm") version KOTLIN_VERSION apply false
    kotlin("js") version KOTLIN_VERSION apply false
    kotlin("multiplatform") version KOTLIN_VERSION apply false
    id(Plugins.benManesVersions) version Plugins.Versions.benManesVersions apply false
}

subprojects {
    apply(plugin = Plugins.benManesVersions)
}
