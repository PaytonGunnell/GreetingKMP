
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization").version(libs.versions.serialization)
    id("com.google.devtools.ksp")
    id("com.rickclephas.kmp.nativecoroutines")
//    id("app.cash.sqldelight") version "2.0.1"
}

//sqldelight {
//    databases {
//        create("Database") {
//            packageName.set("com.example")
//        }
//    }
//}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    jvm()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
        commonMain.dependencies {
            // Datetime
            implementation(libs.datetime)

            // Coroutines
            implementation(libs.coroutines.core)

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serialization)

            // SQLDelight
//            implementation("com.squareup.sqldelight:runtime:2.0.1")

            // Koin (Dependency Injection)
        //    implementation(libs.koin.core)
        //    implementation(libs.koin.test)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            // Ktor
            implementation(libs.ktor.android)

            // SQLDelight
//            implementation(libs.sqldelight.android.driver)
        }
        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.darwin)

            // SQLDelight
//            implementation(libs.sqldelight.native.driver)
        }
        jvmMain.dependencies {
            // SQLDelight
//            implementation(libs.sqldelight.driver)
        }
    }
}

android {
    namespace = "com.example.greetingkmp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

