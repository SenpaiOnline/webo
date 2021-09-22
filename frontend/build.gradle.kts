import com.moowork.gradle.node.yarn.YarnTask

plugins {
    kotlin("js")
    id(Plugins.nodeGradle) version Plugins.Versions.nodeGradle // TODO
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    /*implementation(project(":common"))*/
}

kotlin {
    js(IR) {
        browser {
            binaries.executable()
        }
    }
}

node {
    download = true

    version = "12.17.0"
    workDir = file("${project.rootProject.projectDir}/.gradle/nodejs")
    yarnVersion = "1.22.4"
    yarnWorkDir = file("${project.rootProject.projectDir}/.gradle/yarn")
}

tasks.create<YarnTask>("runDev") {
    args = listOf("dev", "--mode spa", "--modern")
    setEnvironment(mapOf("NODE_ENV" to "development"))
}
