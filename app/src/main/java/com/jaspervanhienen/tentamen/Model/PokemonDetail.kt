package com.jaspervanhienen.tentamen.Model

import org.json.JSONObject

class PokemonDetail(name: String, baseExperience : Int, height: Int, id: Int, sprites: JSONObject) {
    private var name = name
    private var baseExperience = baseExperience
    private var height = height
    private var id = id
    private var sprites = sprites

    public val count = 5

    fun getName() : String {
        return this.name
    }

    fun getBaseExperience() : Int {
        return this.baseExperience
    }

    fun getHeight() : Int {
        return this.height
    }

    fun getId() : Int {
        return this.id
    }

    fun getSprites(type : String) : String {
        return when (type) {
            "back_default" -> sprites.getString("back_default")
            "back_female" -> sprites.getString("back_female")
            "back_shiny" -> sprites.getString("back_shiny")
            "back_shiny_female" -> sprites.getString("back_shiny_female")
            "front_default" -> sprites.getString("front_default")
            "front_female" -> sprites.getString("front_female")
            "front_shiny" -> sprites.getString("front_shiny")
            "front_shiny_female" -> sprites.getString("front_shiny_female")
            else -> ""
        }
    }
}