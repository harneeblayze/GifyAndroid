plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures{
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions{
        kotlinCompilerExtensionVersion = "1.1.0-beta03"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //implementation("androidx.core:core-ktx:1.7.0")
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.ktxCore)
    implementation(Libraries.appCompat)

    implementation(Libraries.androidxComposeRuntime)
    implementation(Libraries.androidxComposeRuntimeLiveData)
    implementation(Libraries.androidxComposeUi)
    implementation(Libraries.androidxComposeConstraintLayout)
    implementation(Libraries.androidxComposeUiTooling)

    implementation(Libraries.androidxComposeUiToolingPreview)
    implementation(Libraries.androidxComposeFoundation)
    implementation(Libraries.androidxComposeFoundationLayout)
    implementation(Libraries.androidxComposeMaterial)
    implementation(Libraries.androidxComposeMaterialIconsExtended)
    implementation(Libraries.androidxComposeAnimation)
    implementation(Libraries.composeNavigation)

    implementation(Libraries.coilBase)
    implementation(Libraries.coilCompose)
    implementation(Libraries.coilComposeGif)

    //implementation("androidx.appcompat:appcompat:1.5.1")
    //implementation("com.google.android.material:material:1.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}