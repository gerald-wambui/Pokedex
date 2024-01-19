package com.jaguh.pokedex.ui.main

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import com.jaguh.pokedex.R
import com.jaguh.pokedex.databinding.ActivityMainBinding
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main){

	@get:VisibleForTesting
	internal val viewModel: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		onTransformationStartContainer()
		super.onCreate(savedInstanceState)
		binding {
			adapter = PokemonAdapter()
			vm = viewModel
		}
	}
}