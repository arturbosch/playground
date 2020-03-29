plugins {
    groovy
    java
    kotlin("jvm") version "1.3.70"
}

group = "io.gitlab.arturbosch"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { setUrl("https://dl.bintray.com/arturbosch/generic") }
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:3.0.2")
    implementation(kotlin("stdlib"))
    implementation("io.gitlab.arturbosch:kutils:0.6.10")
    testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB:0.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
