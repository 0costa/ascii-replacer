package com.devkelvin.asciireplacer.actions

import com.devkelvin.asciireplacer.utils.CharacterReplacer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor

class ReplaceCharacterAction: AnAction("Formatar para ASCII HTML code") {
    override fun actionPerformed(e: AnActionEvent) {
        val editor: Editor = e.getData(CommonDataKeys.EDITOR) ?: return

        val selectionModel = editor.selectionModel
        val selectedText = selectionModel.selectedText ?: return
        val replacedText = CharacterReplacer.replaceCharacters(selectedText)

        WriteCommandAction.runWriteCommandAction(e.project){
            val document = editor.document
            val start = selectionModel.selectionStart
            val end = selectionModel.selectionEnd
            document.replaceString(start, end, replacedText)
        }

        fun update(e: AnActionEvent) {
            val newEditor: Editor? = e.getData(CommonDataKeys.EDITOR)
            e.presentation.isEnabledAndVisible = newEditor?.selectionModel?.hasSelection() == true
        }
    }
}