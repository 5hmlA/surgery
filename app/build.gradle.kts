val kotlin_version = "1.8.11"
//https://developer.android.google.cn/studio/intro/studio-config?hl=zh-cn
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("surgery")
}
// ./gradlew transformDebugClassesWithAsm
//apply<ExamplePlugin>()

android {
    compileSdk = BuildConfig.compileSdkVersion

    defaultConfig {
        applicationId = "spark.surgery"
        minSdk = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        kapt {
            arguments { arg("AROUTER_MODULE_NAME", project.name) }
        }

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    namespace = "spark.surgery"
}
//https://github.com/gradle/kotlin-dsl-samples
dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("com.alibaba:arouter-api:1.5.2")
    kapt("com.alibaba:arouter-compiler:1.5.2")
}

