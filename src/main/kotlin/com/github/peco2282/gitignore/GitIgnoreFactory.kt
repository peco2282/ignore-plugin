package com.github.peco2282.gitignore

import com.intellij.openapi.fileTypes.UnknownFileType
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import org.apache.commons.lang3.StringUtils
import org.jetbrains.annotations.NotNull


class GitIgnoreFactory(private val ignoreType: GitIgnoreType) {
  fun createFromTemplate(@NotNull directory: PsiDirectory): PsiFile {
    val filename: String = ignoreType.getFileName()
    val currentFile: PsiFile? = directory.findFile(filename)
    if (currentFile != null) {
      return currentFile
    }
    val factory = PsiFileFactory.getInstance(directory.project)

    val ignore: GitIgnore = GitIgnoreResource.getGitIgnore(ignoreType)
    val content: String = StringUtils.defaultIfBlank(ignore.getContent(), "")

    val file = factory.createFileFromText(".gitignore", UnknownFileType.INSTANCE, content)
    return directory.add(file) as PsiFile
  }
}