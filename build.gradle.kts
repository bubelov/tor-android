buildscript {
    repositories {
        maven("https://repo.maven.apache.org/maven2/")
        maven("https://dl.google.com/dl/android/maven2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }
}

task("clean") {
    delete(rootProject.buildDir)
}