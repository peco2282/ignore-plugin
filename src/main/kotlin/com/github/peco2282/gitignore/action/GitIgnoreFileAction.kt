package com.github.peco2282.gitignore.action

import com.github.peco2282.gitignore.GitIgnoreBundle
import com.github.peco2282.gitignore.GitIgnoreType
import com.intellij.ide.IdeView
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import org.jetbrains.annotations.NotNull

class GitIgnoreFileAction(@NotNull private val type: GitIgnoreType) : AnAction(), DumbAware {
  init {
    createPresentation(type)
  }

  companion object {
    private fun openFile(@NotNull project: Project, @NotNull file: PsiFile) {
      FileEditorManager.getInstance(
        project
      ).openFile(
        file.virtualFile, true
      )
      Notifications.Bus.notify(
        Notification(
          "File Detector",
          "Creation succeed!",
          NotificationType.INFORMATION
        )
      )
    }
  }

  /**
   * Implement this method to provide your action handler.
   *
   * @param e Carries information on the invocation place
   */
  override fun actionPerformed(e: AnActionEvent) {
    val project: Project = e.getRequiredData(CommonDataKeys.PROJECT)
    val view: IdeView = e.getRequiredData(LangDataKeys.IDE_VIEW)
    val directory: PsiDirectory = view.orChooseDirectory ?: return
    val file: PsiFile? = directory.findFile(".gitignore")
    val virtualFile: VirtualFile? = getVirtualFile(directory, file)
    if (file == null && virtualFile == null) {
      val createdPsiFile: PsiFile = createFile(project, directory)
      openFile(project, createdPsiFile)

    } else {
      val string: String = virtualFile?.path ?: "`cannot search`"
      Notifications.Bus.notify(
        Notification(
          "File Detector",
          GitIgnoreBundle.message("gitignore.file.exist"),
          GitIgnoreBundle.message("gitignore.file.exist.in", string),
          NotificationType.ERROR
        )
      )
    }
  }

  private fun createFile(project: Project, directory: PsiDirectory): PsiFile {
    val gitIgnoreGenerate = GitIgnoreGenerateAction(project, directory, type)
    return gitIgnoreGenerate.execute()
  }

  private fun getVirtualFile(directory: PsiDirectory, file: PsiFile?): VirtualFile? {
    if (file == null) {
      return directory.virtualFile.findChild(".gitignore")
    }
    return file.virtualFile
  }

  private fun createPresentation(@NotNull type: GitIgnoreType) {
    val presentation: Presentation = templatePresentation
    presentation.text = type.getLangType()
    presentation.description = getGitInoreDescription(type)
  }

  private fun getGitInoreDescription(@NotNull type: GitIgnoreType): String {
    return GitIgnoreBundle.message("gitignore.file.description", type.getDescription())
  }
}