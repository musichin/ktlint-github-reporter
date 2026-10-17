import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm") version "2.4.21"
    id("org.jetbrains.kotlinx.kover") version "0.9.11"
    id("com.vanniktech.maven.publish") version "0.37.0"
}

kotlin {
    compilerOptions {
        apiVersion.set(KotlinVersion.KOTLIN_2_0)
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        jvmTarget.set(JvmTarget.JVM_1_8)
        freeCompilerArgs.add("-Xjdk-release=1.8")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.pinterest.ktlint:ktlint-cli-reporter-core:1.8.0")
    testImplementation("com.pinterest.ktlint:ktlint-cli-reporter-core:1.8.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kover {
    useJacoco("0.8.15")
    reports {
        total {
            xml { onCheck = true }
        }
    }
}
