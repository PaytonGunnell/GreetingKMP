plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application

}

group = "com.example.greetingkmp"
version = "1.0.0"
application {
    mainClass.set("com.example.greetingkmp.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}