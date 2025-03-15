package com.devkelvin.asciireplacer.utils

import java.util.*

object CharacterReplacer {
    private val mapping = mutableMapOf<String, String>()

    init {
        loadMapping()
    }

    private fun loadMapping(){
        val inputStream = javaClass.getResourceAsStream("/mapping.properties")
        if(inputStream != null){
            val properties = Properties()
            properties.load(inputStream)
            for((key, value) in properties){
                mapping[key.toString()] = value.toString()
            }
        }
    }

    fun replaceCharacters(text: String): String{
        var replacedText = ""

        for (i in text.indices){
            var char = text[i].toString()
            var code = mapping[char]?.toString() ?: char

            replacedText += code
        }
        return replacedText
    }
}