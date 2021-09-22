plugins {
    kotlin("multiplatform")
    id("net.akehurst.kotlin.kt2ts") version "1.6.0"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        withJava()
    }
    /*js(LEGACY) {
        browser()
        moduleName = "common"
        browser {}
        binaries.executable()
    }*/

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
    }
}

kt2ts {
    classPatterns.set(listOf(
        "api.*"
    ))
}
