plugins {
    id("org.jetbrains.kotlin.plugin.jpa") version "1.6.10"
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("org.jetbrains.kotlin.kapt") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("me.qoomon.git-versioning") version "5.1.5"
    id("io.micronaut.application") version "3.2.2"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.10"
}

repositories {
    mavenCentral()
}

micronaut {
    version("3.3.4")
    runtime("netty")
    testRuntime("kotest")
    processing {
        incremental(true)
        annotations("online.senpai.webo.*")
    }
}

allOpen {
    annotations(
        "io.micronaut.core.annotation.Introspected",
        "io.micronaut.validation.Validated"
    )
}

dependencies {
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")
    kapt("io.micronaut:micronaut-http-validation")
    kapt("io.micronaut.data:micronaut-data-processor")
    kapt("io.micronaut.openapi:micronaut-openapi")
    kapt("io.micronaut.security:micronaut-security-annotations")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.data:micronaut-data-r2dbc")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.problem:micronaut-problem-json")
    implementation("io.micronaut.r2dbc:micronaut-r2dbc-core")
    implementation("io.micronaut.reactor:micronaut-reactor")
    implementation("io.micronaut.reactor:micronaut-reactor-http-client")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5")
    implementation("io.micronaut.security:micronaut-security")
    implementation("io.swagger.core.v3:swagger-annotations")
    implementation("javax.annotation:javax.annotation-api")
    implementation("io.micronaut:micronaut-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    compileOnly("javax.inject:javax.inject:1")
    compileOnly("jakarta.persistence:jakarta.persistence-api:2.2.2")
    compileOnly("org.graalvm.nativeimage:svm")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("io.r2dbc:r2dbc-postgresql")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("io.netty:netty-transport-native-epoll")
    testImplementation("org.testcontainers:r2dbc")
    testImplementation("org.testcontainers:testcontainers")
}


application {
    mainClass.set("online.senpai.webo.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

kapt {
    arguments {
        arg("mapstruct.defaultComponentModel", "jsr330")
        arg("mapstruct.defaultInjectionStrategy", "constructor")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
}

tasks {
    compileKotlin {
        kotlinOptions {
            kotlinOptions.languageVersion = "1.6"
            kotlinOptions.jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            kotlinOptions.languageVersion = "1.6"
            kotlinOptions.jvmTarget = "11"
        }
    }
    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin")
        resources.srcDirs("src/main/resources")
    }
    test {
        java.srcDirs("src/test/kotlin")
        resources.srcDirs("src/test/resources")
    }
}

gitVersioning.apply {
    refs {
        branch(".+") {
            version = "\${ref}-SNAPSHOT"
        }
        tag("v(?<version>.*)") {
            version = "\${ref.version}"
        }
    }

    rev {
        version = "\${commit}"
    }
}
