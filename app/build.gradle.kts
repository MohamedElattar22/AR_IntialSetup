plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

}

android {
    namespace = "com.example.ar_intialsetup"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ar_intialsetup"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //noinspection GradleCompatible
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //ARCore
    implementation ("com.google.ar:core:1.40.0")
    //SceneForm
    implementation ("com.google.ar:core:1.17.1")

    // Provides ArFragment, and other UX resources.
    implementation ("com.google.ar.sceneform.ux:sceneform-ux:1.17.1")

    // Alternatively, use ArSceneView without the UX dependency.
    implementation ("com.google.ar.sceneform:core:1.17.1")
}