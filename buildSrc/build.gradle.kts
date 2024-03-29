repositories {
    maven{
        url = uri("${rootDir.parentFile}/repo")
    }
    gradlePluginPortal()
    google()
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/5hmlA/sparkj")
        credentials {
            username = "\u005a\u0075\u0059\u0075\u006e"
            password = "\u0067\u0068\u0070\u005f\u0031\u0063\u0062\u0064\u004d\u004a\u0073\u005a\u0042\u0057\u0033\u006a\u0077\u0057\u0053\u004b\u006e\u0066\u0037\u0042\u0053\u0069\u0044\u006d\u0061\u0030\u0066\u0044\u0048\u0076\u0031\u007a\u0059\u0050\u0073\u0044"
        }
    }
}

val kotlin_version = "1.8.10"
plugins {
    kotlin("jvm") version "1.8.10"
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
}

dependencies {
    ksp("dev.zacsweers.autoservice:auto-service-ksp:+")
    // NOTE: It's important that you _don't_ use compileOnly here, as it will fail to resolve at compile-time otherwise
    implementation("com.google.auto.service:auto-service-annotations:1.0.1")
    implementation(kotlin("stdlib-jdk8"))
//    implementation("osp.sparkj.plugin:surgery:2023.03.19")
    implementation("osp.sparkj.plugin:surgery-doctors:2023.03.19")
//    implementation(kotlin("gradle-plugin", kotlin_version))
//    implementation(kotlin("gradle-plugin-api", kotlin_version))
    implementation("com.android.tools.build:gradle-api:7.4.2")
}

//https://github.com/tschuchortdev/kotlin-compile-testing
//https://bnorm.medium.com/exploring-kotlin-ir-bed8df167c23
//https://github.com/Leifzhang/GradleSample.git

//完成以下功能
//https://github.com/zhuguohui/MehodInterceptor
//bytex
//huntter
//booster
