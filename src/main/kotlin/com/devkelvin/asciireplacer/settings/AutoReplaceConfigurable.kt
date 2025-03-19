package com.devkelvin.asciireplacer.settings


import ai.grazie.detector.ngram.main
import com.intellij.openapi.options.Configurable
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel
import com.intellij.ui.dsl.builder.*
import com.intellij.util.ui.FormBuilder

class AutoReplaceConfigurable: Configurable {

    private var autoReplaceCheckBox = JCheckBox("Habilitar substituição automática em arquivos velocity.")

    override fun getDisplayName(): String {
        return "Ascii-replacer configuração"
    }

    override fun createComponent(): JComponent {
        return panel {
            group("Substituição Automática"){
                row {
                    cell(autoReplaceCheckBox)
                }
            }
        }
    }

    override fun isModified(): Boolean {
        return autoReplaceCheckBox.isSelected != AutoReplaceSettings.getInstance().state.autoReplaceEnabled
    }

    override fun apply() {
        AutoReplaceSettings.getInstance().state.autoReplaceEnabled = autoReplaceCheckBox.isSelected ?: true
    }

    override fun reset() {
        autoReplaceCheckBox.isSelected = AutoReplaceSettings.getInstance().state.autoReplaceEnabled
    }
}