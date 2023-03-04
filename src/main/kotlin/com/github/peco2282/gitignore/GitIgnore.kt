package com.github.peco2282.gitignore

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

class GitIgnore(
  @NotNull
  private val type: GitIgnoreType,
  @Nullable private val content: String?
) {
  @NotNull
  fun getType(): GitIgnoreType {
    return this.type
  }

  @Nullable
  fun getContent(): String? {
    return this.content
  }

}