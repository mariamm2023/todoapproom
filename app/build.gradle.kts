plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.todoapproom"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.todoapproom"
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
    val roomVersion = "2.5.2" // يمكنك تغيير النسخة إلى الأحدث عند التحديثات

    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // اختياري: إذا كنت ترغب في استخدام Room مع RxJava
    implementation("androidx.room:room-rxjava3:$roomVersion")

    // اختياري: إذا كنت ترغب في استخدام Room مع Guava
    implementation("androidx.room:room-guava:$roomVersion")

    // اختياري: Room Kotlin Extensions and Coroutines support
    implementation("androidx.room:room-ktx:$roomVersion")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("com.google.android.material:material:1.9.0") // أو آخر نسخة متوفرة

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    //implementation("com.github.prolificinteractive:material-calendarview:2.0.0")
    implementation ("com.github.zerobranch:SwipeLayout:1.3.1")
    implementation ("com.github.zerobranch:SwipeLayout:1.0.0")



}
