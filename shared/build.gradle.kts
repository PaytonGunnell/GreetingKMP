
plugins {
    id("org.jetbrains.kotlin.multiplatform").version("1.9.22")
    id("com.android.library").version("8.2.1")
    kotlin("plugin.serialization").version("1.9.22")
    id("com.google.devtools.ksp")
    id("com.rickclephas.kmp.nativecoroutines")
    id("app.cash.sqldelight").version("2.0.1")
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.example")
        }
    }
}

kotlin {
    targets.all {
        compilations.all {
            compilerOptions.configure {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
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
        val ktorVersion = "2.3.8"
        val sqldelightVersion = "2.0.1"

        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
        commonMain.dependencies {
            // Datetime
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

            // Coroutines
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

            // Ktor
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

            // SQLDelight
            implementation("app.cash.sqldelight:runtime:$sqldelightVersion")

            // Koin
            implementation("io.insert-koin:koin-core:3.5.3")
            implementation("io.insert-koin:koin-test:3.5.3")
        }
        commonTest.dependencies {
            implementation("org.jetbrains.kotlin:kotlin-test:1.9.22")
        }
        androidMain.dependencies {
            // Ktor
            implementation("io.ktor:ktor-client-android:$ktorVersion")

            // SQLDelight
            implementation("app.cash.sqldelight:android-driver:$sqldelightVersion")
        }
        iosMain.dependencies {
            // Ktor
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")

            // SQLDelight
            implementation("app.cash.sqldelight:native-driver:$sqldelightVersion")
        }
        jvmMain.dependencies {
            // SQLDelight
            implementation(libs.sqldelight.driver)
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

