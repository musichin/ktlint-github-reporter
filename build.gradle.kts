plugins {
    kotlin("jvm") version "1.9.10"
    jacoco
    id("com.vanniktech.maven.publish") version "0.25.3"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.pinterest.ktlint:ktlint-cli-reporter-core:1.0.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

jacoco {
    toolVersion = "0.8.10"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(false)
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}
