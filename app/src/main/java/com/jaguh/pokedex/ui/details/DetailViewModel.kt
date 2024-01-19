package com.jaguh.pokedex.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import androidx.databinding.Bindable
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import androidx.lifecycle.viewModelScope

class DetailViewModel @AssistedInject constructor(
	detailRepository: DetailRepository,
	@Assisted private val pokemonName: String,
): BindingViewModel(){

	@get:Bindable
	val isLoading: Boolean by bindingProperty(true)
		private set

	@get:Bindable
	val toastMessage: String? by bindingProperty(null)
		private set

	private val pokemonInfoFlow: Flow<PokemonInfo?> = detailRepository.fetchPokemonInfo(
		name = pokemonName,
		onComplete = { isLoading = false },
		onError = { toastMessage = it },
	)

	@get:Bindable
	val pokemonInfo: PokemonInfo by pokemonInfoFlow.asBindingProperty(viewModelScope, null)

	init {
		Timber.d("init DetailViewModel")
	}

	@dagger.assisted.AssistedFactory
	interface AssistedFactory {
		fun create(pokemonName: String): DetailViewModel
	}

	companion object{
		fun provideFactory(
			assistedFactory: AssistedFactory,
			pokemonName: String,
		): ViewModelProvider.Factory = object : ViewModelProvider.Factory{

			@Suppress("UNCHECKED_CAST")
			override fun <T : ViewModel> create(modelClass: Class<T>): T {
				return assistedFactory.create(pokemonName) as T
			}
		}
	}
}