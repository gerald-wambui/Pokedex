package com.jaguh.pokedex.ui.main

import androidx.databinding.Bindable
import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import com.skydoves.bindables.asBindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import com.jaguh.pokedex.core.model.Pokemon
import com.jaguh.pokedex.core.repository.MainRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val mainRepository: MainRepository,
) : BindingViewModel(){

	@get:Bindable
	var isLoading: Boolean by bindingProperty(false)
		private set

	@get:Bindable
	var toastMessage: String? by bindingProperty(null)
		private set

	private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
	private val pokemonListFlow = pokemonFetchingIndex.flatMapLatest { page ->
		mainRepository.fetchPokemonList(
			page = page,
			onStart = {isLoading = true},
			onComplete = {isLoading = false},
			onError = {toastMessage = it},
		)
	}

	@get:Bindable
	val pokemonList: List<Pokemon> by pokemonListFlow.asBindingProperty(viewModelScope, emptyList())

	init {
		Timber.d("init MainViewModel")
	}

	@MainThread
	fun fetchNextPokemonList(){
		if (!isLoading){
			pokemonFetchingIndex.value++
		}
	}
}