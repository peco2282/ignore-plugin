package com.github.peco2282.gitignore.action

import com.github.peco2282.gitignore.GitIgnoreFactory
import com.github.peco2282.gitignore.GitIgnoreType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import org.jetbrains.annotations.NotNull

class GitIgnoreGenerateAction(
  @NotNull project: Project,
  @NotNull private val directory: PsiDirectory,
  @NotNull private val ignoreType: GitIgnoreType
): AbstractGitIgnoreGenerateAction<PsiFile>(project) {

  override fun compute(): PsiFile {
    val factory = GitIgnoreFactory(this.ignoreType)
    return factory.createFromTemplate(this.directory)
  }
}