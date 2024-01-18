plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	kotlin("kapt")
	id("kotlin-kapt")
	id("com.google.dagger.hilt.android")
}

android {
	namespace = "com.jaguh.pokedex"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.jaguh.pokedex"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildFeatures {
		dataBinding = true
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

	implementation("androidx.core:core-ktx:1.9.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.11.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	implementation("com.android.databinding:library:8.2.1")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

	implementation("com.github.skydoves:transformationlayout:1.1.3")
	implementation("com.github.skydoves:progressview:1.1.3")
	implementation("com.github.skydoves:androidribbon:1.0.4")

	implementation("com.google.dagger:hilt-android:2.48.1")
	kapt("com.google.dagger:hilt-android-compiler:2.48.1")

	implementation("com.jakewharton.timber:timber:5.0.1")

	 // Check for latest version

	// ViewModel and viewModelScope
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:8.2.1")

	// LiveData
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:8.2.1")

	// Data binding
	implementation("androidx.databinding:databinding-runtime:8.2.1")
	kapt("androidx.databinding:databinding-compiler:8.2.1")
}