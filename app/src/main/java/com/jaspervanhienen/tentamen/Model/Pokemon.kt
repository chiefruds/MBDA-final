package com.jaspervanhienen.tentamen.Model

class Pokemon(name : String, url : String) {
    private var name = name
    private var url = url

    fun getName() : String {
        return this.name
    }

    fun getUrl() : String {
        return this.url
    }

}