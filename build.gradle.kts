plugins {
    id("com.android.application") version "8.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version("1.9.10-1.0.13") apply false


}

buildscript{
    dependencies{
        //classpath("androidx.navigation:navigation-safe-args-gradle:2.3.0-rc01")
        classpath ("com.google.devtools.ksp:symbol-processing-gradle-plugin:1.9.0-1.0.13")
    }

    repositories {
        google()
    }
}