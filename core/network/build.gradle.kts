import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "2.1.20"

}

val localProperties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.iceman.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildTypes {
            debug {
                buildConfigField(
                    "String",
                    "API_KEY",
                    "${localProperties.getProperty("API_KEY")}"
                )
            }
            release {
                buildConfigField(
                    "String",
                    "API_KEY", "${localProperties.getProperty("API_KEY")}"
                )
                // ... other release configurations ...
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }


    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)

        implementation(libs.kotlinx.serialization.json)
        implementation(libs.squareup.retrofit2.retrofit)
        implementation(libs.squareup.retrofit2.retrofit)
        implementation(libs.retrofit.kotlin.serialization)
        implementation(libs.okhttp)
        implementation(libs.okhttp.logging.interceptor)
        implementation(libs.gson)
        //Koin
        implementation(libs.io.insert.koin.androidx.compose)
        implementation(platform(libs.io.insert.koin.bom))
        implementation(libs.squareup.moshi) // For Kotlin code generation
        implementation(libs.converter.moshi) // For Kotlin code generation
        implementation(libs.moshi.adapters) // For Kotlin code generation
        implementation(libs.androidx.lifecycle.viewmodel.android)
        testImplementation(libs.mockito.core)
        testImplementation(libs.mockito.kotlin)

        testImplementation(libs.junit)
        testImplementation(libs.io.insert.koin.test)
        testImplementation(libs.io.insert.koin.test.junit4)

        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
    }

}