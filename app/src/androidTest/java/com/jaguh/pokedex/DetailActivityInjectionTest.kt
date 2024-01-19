package com.jaguh.pokedex

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.jaguh.pokedex.ui.details.DetailActivity
import com.skydoves.transformationlayout.TransformationLayout
import org.junit.Rule
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import com.jaguh.pokedex.core.test.MockUtil
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.lifecycle.Lifecycle

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailActivityInjectionTest {

	@get:Rule
	var hiltRule = HiltAndroidRule(this)

	@Test
	fun verifyInjection() {
		val intent = Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java)
		val transformationLayout = TransformationLayout(ApplicationProvider.getApplicationContext())
		transformationLayout.transitionName = "lapras"
		intent.putExtra("com.skydoves.transformationlayout", transformationLayout.getParcelableParams())
		intent.putExtra(DetailActivity.EXTRA_POKEMON, MockUtil.mockPokemon())
		ActivityScenario.launch<DetailActivity>(intent).use {
			it.moveToState(Lifecycle.State.CREATED)
			it.onActivity { activity ->
				assertThat(activity.detailViewModelFactory).isNotNull()
				assertThat(activity.viewModel).isNotNull()
			}
		}
	}
}