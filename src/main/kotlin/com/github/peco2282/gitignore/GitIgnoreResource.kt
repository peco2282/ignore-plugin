package com.github.peco2282.gitignore

import io.netty.util.internal.StringUtil
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.io.File
import java.io.InputStream
import java.net.URL
import java.util.*

class GitIgnoreResource {

  companion object {
    private const val GIT_IGNORE = "/gitignore/"

    @NotNull
    fun getGitIgnore(@NotNull type: GitIgnoreType): GitIgnore {
      val path = GIT_IGNORE + type.getFileName()
      val file: File? = getResource(path)
      if (file != null) {
        val content: String? = getContent(path)
        print(">>>$content")
        return GitIgnore(type, content)
      }
      return GitIgnore(type, StringUtil.EMPTY_STRING)
    }

    @Nullable
    private fun getContent(path: String): String? {
      val stream: InputStream = Companion::class.java.getResourceAsStream(path) ?: return null
      val scanner = Scanner(stream).useDelimiter("\\A")
      if (scanner.hasNext()) {
        return scanner.next()
      }
      return ""
    }

    private fun getResource(path: String): File? {
      val url: URL = Companion::class.java.getResource(path) ?: return null
      return File(url.path)
    }
  }
}