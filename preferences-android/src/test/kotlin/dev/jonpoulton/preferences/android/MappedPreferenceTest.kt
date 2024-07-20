package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class MappedPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getString("key", default = "1000").map(String::toInt, Int::toString)
    assertThat(preference1.get()).isEqualTo(1000)

    val preference2 = androidSharedPreferences.getInt("key", default = 5000).map(Int::toString, String::toInt)
    assertThat(preference2.get()).isEqualTo("5000")
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getString("key", default = "").map(String::toInt, Int::toString)

    preference.set(1000)
    assertThat(preference.get()).isEqualTo(1000)

    runBlocking {
      preference.setAndCommit(5000)
      assertThat(preference.get()).isEqualTo(5000)
    }
  }
}
