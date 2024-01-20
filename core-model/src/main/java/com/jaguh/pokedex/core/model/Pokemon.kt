package com.jaguh.pokedex.core.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class Pokemon(
	var page: Int = 0,
	@field:Json(name = "name")
	val name: String,
	@field:Json(name = "url") val url: String,
): Parcelable {

	fun getImageUrl(): String {
		val index = url.split("/".toRegex()).dropLast(1).last()
		return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
				"pokemon/other/official-artwork/$index.png"
	}
}