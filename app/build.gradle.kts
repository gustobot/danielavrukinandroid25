plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.jetbrains.kotlin.compose)
}

android {
    namespace = "com.gusto.lunchmenu"
	compileSdk = 36

    defaultConfig {
        applicationId = "com.gusto.lunchmenu"
        minSdk = 24
	    targetSdk = 36
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
        }
    }
    compileOptions {
	    // Flag to enable support for newer language APIs
	    isCoreLibraryDesugaringEnabled = true
	    sourceCompatibility = JavaVersion.VERSION_17
	    targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
	kotlin {
		jvmToolchain(17)
	}
}

dependencies {

	// Dependency for desugaring APIs
	coreLibraryDesugaring(libs.android.desugar.jdk.libs)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
	implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.appcompat)
	implementation(libs.androidx.viewmodel.compose)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
