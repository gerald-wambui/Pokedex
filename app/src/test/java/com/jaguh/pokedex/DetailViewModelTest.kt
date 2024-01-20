package com.jaguh.pokedex


class DetailViewModelTest {

	private lateinit var viewModel: DetailViewModel
	private lateinit var detailRepository: DetailRepository
	private val pokedexService: PokedexService = mock()
	private val pokedexClient: PokedexClient = PokedexClient
	private val pokemonInfoDao: PokemonInfoDao = mock()
}