package com.jaspervanhienen.tentamen.model

class Pokemon(private var name: String, private var url: String) {
    fun getName() : String {
        return this.name
    }

    fun getUrl() : String {
        return this.url
    }
}