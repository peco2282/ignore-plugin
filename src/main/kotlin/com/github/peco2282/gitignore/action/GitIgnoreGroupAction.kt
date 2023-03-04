package com.github.peco2282.gitignore.action

import com.github.peco2282.gitignore.GitIgnoreBundle
import com.github.peco2282.gitignore.GitIgnoreType
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.project.DumbAware
import java.util.*


class GitIgnoreGroupAction : DefaultActionGroup(), DumbAware {
  init {
    isPopup = true
    createPresentation()
    Arrays.stream(
      GitIgnoreType.values()
    ).map {
      GitIgnoreFileAction(it)
    }.forEach(this::add)
  }

  private fun createPresentation() {
    val presentation: Presentation = templatePresentation
    presentation.text = GitIgnoreBundle.message("gitignore.group.bundle")
  }
}