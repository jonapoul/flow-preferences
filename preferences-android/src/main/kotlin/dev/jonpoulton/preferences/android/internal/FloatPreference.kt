package dev.jonpoulton.preferences.android.internal

import android.content.SharedPreferences
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class FloatPreference(
  override val key: String,
  override val default: Float,
  keyFlow: KeyFlow,
  private val sharedPreferences: SharedPreferences,
  private val coroutineContext: CoroutineContext,
) : BasePreference<Float>(key, keyFlow, sharedPreferences, coroutineContext) {
  override fun get() = sharedPreferences.getFloat(key, default)

  override fun set(value: Float) = sharedPreferences.edit().putFloat(key, value).apply()

  override suspend fun setAndCommit(value: Float) = withContext(coroutineContext) {
    sharedPreferences.edit().putFloat(key, value).commit()
  }
}
