import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.navigation.safeargs)
//    id("kotlin-parcelize")
    kotlin("kapt")

}

val localProperties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

val apiKey = localProperties.getProperty("API_KEY") ?: System.getenv("API_KEY") ?: ""

android {
    namespace = "com.alihafez.orangetask"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.alihafez.orangetask"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_KEY", "\"$apiKey\"")

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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
//        viewBinding = true
        dataBinding = true
    }
    buildFeatures {
        buildConfig =true
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)

    // JSON serialization library, works with the Kotlin serialization plugin.
    implementation(libs.kotlinx.serialization.json)

    // retrofit
    api(libs.retrofit)
    implementation(libs.converter.gson)

    // okhttp3
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)



}