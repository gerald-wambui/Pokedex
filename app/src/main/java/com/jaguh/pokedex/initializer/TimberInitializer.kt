package com.jaguh.pokedex.initializer

import android.content.Context
import androidx.startup.Initializer
import com.jaguh.pokedex.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

	override fun create(context: Context) {
		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
			Timber.d("TimberInitializer is initiated")
		}
	}

	override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}