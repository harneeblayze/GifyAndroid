const val kotlinVersion = "1.5.10"
object Libraries{
    private object Versions{
        const val livedata = ""
        const val lottie = "3.0.7"
        const val glide = "4.11.0"
        const val ktx = "1.6.0"
        const val swipeToRefresh = "1.1.0"
        const val lifecycle = "2.2.0"
        const val coroutines = "1.4.2"
        const val jetpack = "1.3.0"
        const val legacy_support = "1.0.0"
        const val google_material = "1.4.0"
        const val appcompat = "1.3.0"
        const val constraintLayout = "2.0.4"
        const val retrofit = "2.9.0"
        const val interceptor = "3.12.1"
        const val hilt = "2.35"
        const val palette = "28.0.0"
        const val coroutines_lifecycle = "2.4.0-alpha01"
        const val okHttp = "3.12.1"
        const val moshi = "1.9.3"
        const val paging = "2.1.2"
        const val navigation = "2.3.5"
        const val paging3 = "3.0.0-beta01"

    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val designLibary = "com.google.android.material:material:${Versions.google_material}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val paging3 = "androidx.paging:paging-runtime-ktx:${Versions.paging3}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    //    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxjava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val coroutinesLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.coroutines_lifecycle}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val livedata = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val supportPalette = "com.android.support:palette-v7${Versions.palette}"

}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13.2"
        const val testRunner = "1.2.0"
        const val testUnitExt = "1.1.3"
        const val espressor = "3.4.0"
        const val mockito = "1.5.0"
        const val assertJ = "3.8.0"
    }
    const val junit4 = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val testUnitExt = "androidx.test.ext:junit:${Versions.testUnitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressor}"
    const val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockito}"
    const val assertJ = "org.assertj:assertj-core:${Versions.assertJ}"
    const val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${kotlinVersion}"
}