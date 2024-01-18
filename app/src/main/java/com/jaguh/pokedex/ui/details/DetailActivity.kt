package com.jaguh.pokedex.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import com.jaguh.pokedex.R
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationCompat.onTransformationEndContainerApplyParams
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail){

	@Inject
	internal lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

	@get:VisibleForTesting
	internal val viewModel: DetailViewModel by viewModels{
		DetailViewModel.provideFactory(detailViewModelFactory, pokemon.name)
	}

	private val pokemon: Pokemon by bundleNonNull(EXTRA_POKEMON)

	 override fun onCreate(savedInstanceState: Bundle?) {
		onTransformationEndContainerApplyParams(this)
		super.onCreate(savedInstanceState)
		binding.pokemon = pokemon
		binding.vm = viewModel
	}

	companion object{
		@VisibleForTesting
		internal const val EXTRA_POKEMON = "EXTRA_POKEMON"

		fun startActivity(transformationLayout: TransformationLayout, pokemon: Pokemon) =
			transformationLayout.context.intentOf<DetailActivity>
		{
			putExtra(EXTRA_POKEMON to pokemon)
			TransformationCompat.startActivity(transformationLayout, intent)
		}
	}

}