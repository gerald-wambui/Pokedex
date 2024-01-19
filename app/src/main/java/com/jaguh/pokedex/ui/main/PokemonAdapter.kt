package com.jaguh.pokedex.ui.main

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.jaguh.pokedex.R
import com.jaguh.pokedex.databinding.ItemPokemonBinding
import com.jaguh.pokedex.core.model.Pokemon
import com.jaguh.pokedex.ui.details.DetailActivity
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class PokemonAdapter : BindingListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(diffUtil) {

	private val onClickedAt = 0L

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): PokemonViewHolder = parent.binding<ItemPokemonBinding>(R.layout.item_pokemon).let(::PokemonViewHolder)

	override fun onBindViewHolder(holder: PokemonAdapter.PokemonViewHolder, position: Int) =
		holder.bindPokemon(getItem(position))

	inner class PokemonViewHolder constructor(
		private val binding: ItemPokemonBinding,
	) : RecyclerView.ViewHolder(binding.root){

		init {
			binding.root.setOnClickListener {
				val position = bindingAdapterPosition.takeIf { it != NO_POSITION }
					?: return@setOnClickListener
				val currentClickedAt = SystemClock.elapsedRealtime()
				if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
					DetailActivity.startActivity(binding.transformationLayout, getItem(position))
					onClickedAt = currentClickedAt
				}
			}
		}

		fun bindPokemon(pokemon: Pokemon) {
			binding.pokemon = pokemon
			binding.executePendingBindings()
		}
	}

	companion object {
		private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>(){

			override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
				oldItem.name == newItem.name

			override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
				oldItem == newItem


		}
	}
}