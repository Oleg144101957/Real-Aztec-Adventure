plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.gms.google-services")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {

    signingConfigs {
        create("release") {
            if (project.hasProperty("MYAPP_RELEASE_STORE_FILE")) {
                storeFile = file(project.findProperty("MYAPP_RELEASE_STORE_FILE"))
                storePassword = project.findProperty("MYAPP_RELEASE_STORE_PASSWORD") as String
                keyAlias = project.findProperty("MYAPP_RELEASE_KEY_ALIAS") as String
                keyPassword = project.findProperty("MYAPP_RELEASE_KEY_PASSWORD") as String
            }
        }
    }


    namespace = "com.arbtemey.solo.aztecgold2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arbtemey.solo.aztecgold2"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")



    implementation ("androidx.compose.runtime:runtime-livedata:1.5.3")
    implementation ("androidx.navigation:navigation-compose:2.7.4")
    implementation ("com.google.dagger:hilt-android:2.46.1")
    kapt ("com.google.dagger:hilt-compiler:2.46.1")

    //Storage
    implementation ("io.github.pilgr:paperdb:2.7.2")


    //Track
    implementation ("com.google.android.gms:play-services-ads-identifier:18.0.1")
    implementation ("com.appsflyer:af-android-sdk:6.12.0")
    implementation ("com.facebook.android:facebook-android-sdk:16.1.3")
    implementation ("com.onesignal:OneSignal:[4.0.0, 4.99.99]")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
}