package com.jaguh.pokedex


import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.jaguh.pokedex.ui.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityInjectionTest {

	@get:Rule
	var hiltRule = HiltAndroidRule(this)

	@Test
	fun verifyInjection() {
		ActivityScenario.launch(MainActivity::class.java).use {
			it.moveToState(Lifecycle.State.CREATED)
			it.onActivity { activity ->
				assertThat(activity.viewModel).isNotNull()
			}
		}
	}
}