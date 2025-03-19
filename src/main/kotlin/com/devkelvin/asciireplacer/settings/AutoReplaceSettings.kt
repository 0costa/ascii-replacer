package com.devkelvin.asciireplacer.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.APP)
@State(name = "AutoReplaceSettings", storages = [Storage("AutoReplacePlugin.xml")])
class AutoReplaceSettings : PersistentStateComponent<AutoReplaceSettings.State>{
    var myState = State()

    class State{
        var autoReplaceEnabled: Boolean = true
    }

    override fun getState(): State {
        return myState
    }

    override fun loadState(state: State) {
        this.myState = state
    }

    companion object {
        fun getInstance(): AutoReplaceSettings{
            return ApplicationManager.getApplication().getService(AutoReplaceSettings::class.java)
        }
    }

}