plugins {
    kotlin("jvm") version "1.4.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.guava:guava:29.0-jre")
    implementation("org.apache.commons:commons-math3:3.0")
    implementation("com.github.dpaukov:combinatoricslib3:3.3.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "MainKt"
}
