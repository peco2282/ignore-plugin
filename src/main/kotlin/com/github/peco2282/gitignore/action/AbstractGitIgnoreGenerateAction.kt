package com.github.peco2282.gitignore.action

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.NotNull

abstract class AbstractGitIgnoreGenerateAction<T>(
  @NotNull
  private val project: Project
) {

  protected abstract fun compute(): T
  fun execute(): T {
    return WriteCommandAction
      .writeCommandAction(project)
      .compute<T, RuntimeException> { compute() }
  }
}
