/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

plugins {
    id("com.android.application")

    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    compileSdk = 35

    namespace = "com.glassdoor.intern"

    defaultConfig {
        minSdk = 26
        targetSdk = 35

        versionCode = 1
        versionName = "1.0.0"

        applicationId = "com.glassdoor.intern"

        buildConfigField(
            type = "String",
            name = "BASE_SERVER_URL",
            value = """"https://script.google.com/macros/s/"""",
        )

        buildConfigField(
            type = "String",
            name = "ENDPOINT_GET_INFO",
            value = """"AKfycby7IE5N9JGBOzuZ-FaXGdHs9tWYkoybW7uKzAuXzd0bbYhV80thWQrGvibs8oQo6DVQ/exec"""",
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"

        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xcontext-receivers",
        )
    }

    buildFeatures.compose = true

    buildFeatures.buildConfig = true

    composeOptions.kotlinCompilerExtensionVersion = "1.5.4"

    packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
}

dependencies {

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation(platform("androidx.compose:compose-bom:2025.01.00"))

    ksp("com.google.dagger:hilt-android-compiler:2.50")
    implementation("com.google.dagger:hilt-android:2.50")

    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.18")

    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))

    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
}