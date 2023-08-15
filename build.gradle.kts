plugins {
    kotlin("jvm") version "1.8.21"
    application
    id("io.ktor.plugin") version "2.3.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"


}

group = "me.mosta"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}

dependencies {
    val ktorVersion="2.3.3"
    testImplementation(kotlin("test"))
    // List of artifacts, e.g.:
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-cio:$ktorVersion")
    implementation ("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation ("io.ktor:ktor-server-default-headers:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-cbor:$ktorVersion")
    implementation("io.ktor:ktor-server-locations:$ktorVersion")
    implementation("io.ktor:ktor-server-resources:$ktorVersion")
    implementation("io.ktor:ktor-server-request-validation:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:1.4.7")
    //
    // kmongo
    val kMongoVersion = "4.10.0"
    implementation("org.litote.kmongo:kmongo:$kMongoVersion")
    implementation("org.litote.kmongo:kmongo-coroutine:$kMongoVersion")
    implementation("org.litote.kmongo:kmongo-async:$kMongoVersion")
    implementation("org.litote.kmongo:kmongo-reactor:$kMongoVersion")
    implementation("org.litote.kmongo:kmongo-rxjava2:$kMongoVersion")
    //
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")

//
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    //mainClass.set("MainKt")
    mainClass.set("io.ktor.server.netty.EngineMain")
}