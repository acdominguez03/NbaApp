// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.46.1" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.1" apply false
    kotlin("kapt") version "1.9.24" apply false
}