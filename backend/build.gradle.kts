plugins {
    kotlin("jvm")
    application
    id("io.kotest") version Plugins.Versions.kotest
    id("org.openapi.generator") version Plugins.Versions.openapiGenerator
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { url = uri("https://philanthropist.touk.pl/nexus/content/repositories/releases") }
    maven { url = uri("https://dl.bintray.com/arrow-kt/arrow-kt/") }
    maven { url = uri("https://oss.jfrog.org/artifactory/oss-snapshot-local/") }
    maven { url = uri("https://dl.bintray.com/konform-kt/konform") }
}

dependencies {
    // Ktor
    implementation(Libraries.ktorServerCore)
    implementation(Libraries.ktorServerNetty)
    implementation(Libraries.ktorHtmlBuilder)
    implementation(Libraries.ktorFreemarker)
    implementation(Libraries.ktorServerHostCommon)
    implementation(Libraries.ktorLocations)
    implementation(Libraries.ktorMetrics)
    implementation(Libraries.ktorServerSessions)
    implementation(Libraries.ktorWebsockets)
    implementation(Libraries.ktorAuth)
    implementation(Libraries.ktorAuthJwt)
    implementation(Libraries.ktorJackson)
    implementation(Libraries.ktorSerialization)
    implementation(Libraries.mpierceKtorCsrf)

    // Ktor client
    implementation(Libraries.ktorClientApache)
    implementation(Libraries.ktorClientJson)
    implementation(Libraries.ktorClientJackson)

    // Logging
    implementation(Libraries.kotlinLogging)
    implementation(Libraries.logbackClassic)

    // Database
    implementation(Libraries.hikariCP)
    implementation(Libraries.postgres)
    implementation(Libraries.exposedCore)
    implementation(Libraries.exposedDao)
    implementation(Libraries.exposedJdbc)
    implementation(Libraries.exposedJavaTime)
    /*api(Libraries.krushAnnotationProcessor)
    kapt(Libraries.krushAnnotationProcessor)
    api(Libraries.krushRuntime)*/
    implementation(Libraries.kmongoCoroutine)
    /*implementation(Libraries.kotlinxSerialization)*/

    // Dependency injection
    implementation(Libraries.koinCore)
    implementation(Libraries.koinKtor)
    implementation(Libraries.koinSlf4j)

    // Arrow
    implementation(Libraries.arrowFx)
    implementation(Libraries.arrowFxCoroutines)
    implementation(Libraries.arrowSyntax)

    // Validation
    implementation(Libraries.kalidation)
    implementation(Libraries.valiktor)
    implementation(Libraries.konform)

//    implementation(project(":common"))

    testImplementation(TestLibraries.junitJupiter)
    testImplementation(TestLibraries.ktorServerTests)
    testImplementation(TestLibraries.koinTest)
    testImplementation(TestLibraries.testContainersJupiter)
    testImplementation(TestLibraries.testContainersNginx)
    testImplementation(TestLibraries.testContainersMongodb)
    testImplementation(TestLibraries.testContainersToxiproxy)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.striktArrow)
    testImplementation(TestLibraries.striktJackson)
    testImplementation(TestLibraries.striktMockk)
    testImplementation(TestLibraries.kotlinXCoroutinesDebug)
    testImplementation(TestLibraries.kotlinXCoroutinesTest)
    testImplementation(TestLibraries.kotestTestContainers)
    testImplementation(TestLibraries.kotestAssertionsArrow)
    testImplementation(TestLibraries.kotestAssertionsKtor)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin", "build/generated/openapi/src/main/kotlin")
        resources.srcDirs("src/main/resources")
    }
    test {
        java.srcDirs("src/test/kotlin")
        resources.srcDirs("src/test/resources")
    }
}

openApiGenerate {
    generatorName.set("kotlin-server")
    inputSpec.set("$rootDir/OpenAPI-schema.yaml")
    templateDir.set("$rootDir/openapi-templates")
    outputDir.set("$buildDir/generated/openapi")

    apiPackage.set("online.senpai.codegen.api")
    modelPackage.set("online.senpai.codegen.model")
    packageName.set("online.senpai.codegen")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "enumPropertyNaming" to "UPPERCASE",
            "generateModelTests" to "false",
            "generateModelDocumentation" to "false",
            "generateApiTests" to "false",
            "generateApiDocumentation" to "false",
            "modelMutable" to "true",
            "serializationLibrary" to "jackson"
        )
    )
    globalProperties.set(
        mapOf(
            "supportingFiles" to "Paths.kt",
            "apis" to "",
            "models" to ""
        )
    )
    typeMappings.set(
        mapOf(
            "array" to "kotlin.collections.List"
        )
    )
}
