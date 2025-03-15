package com.devkelvin.asciireplacer.listeners

import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.project.Project
import com.devkelvin.asciireplacer.utils.CharacterReplacer
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.util.TextRange

class AutoReplaceListener(val project: Project): DocumentListener {

    private fun isVmFile(event: DocumentEvent): Boolean{
        val document = event.document
        val file = FileDocumentManager.getInstance().getFile(document)
        return file?.extension?.equals("vm", ignoreCase = true) == true
    }

    override fun documentChanged(event: DocumentEvent) {
        if (!isVmFile(event)) return

        val document = event.document
        val startOffset = event.offset
        val endOffset = event.offset + event.newLength

        if (startOffset >= endOffset) return

        val textRange = TextRange(startOffset, endOffset)
        val text = document.getText(textRange)
        val replacedText = CharacterReplacer.replaceCharacters(text)

        if(text != replacedText){
            ApplicationManager.getApplication().invokeLater {
                WriteCommandAction.runWriteCommandAction(project){
                    document.replaceString(startOffset, endOffset, replacedText)
                }
            }
        }
    }
}