package com.devkelvin.asciireplacer

import com.devkelvin.asciireplacer.listeners.AutoReplaceListener
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class AsciiReplacer : ProjectActivity {
    override suspend fun execute(project: Project) {
        val editorFactory = EditorFactory.getInstance()
        val listener = AutoReplaceListener(project)

        editorFactory.eventMulticaster.addDocumentListener(listener, project)
    }
}