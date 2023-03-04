package com.github.peco2282.gitignore

import com.intellij.AbstractBundle
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.PropertyKey
import java.util.*

class GitIgnoreBundle {
  companion object {
    private const val BUNDLE_NAME: String = "bundle.GitIgnoreBundle"
    private val BUNDLE: ResourceBundle = ResourceBundle.getBundle(BUNDLE_NAME)
    fun message(
      @NotNull @PropertyKey(resourceBundle = BUNDLE_NAME) key: String,
      vararg params: Any
    ): @Nls String {
      return AbstractBundle.message(BUNDLE, key, params)
    }
  }
}