plugins {
    groovy
    java
    kotlin("jvm") version "1.3.70"
}

group = "io.gitlab.arturbosch"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:3.0.1")
    implementation(kotlin("stdlib"))
    testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB:0.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()
}
