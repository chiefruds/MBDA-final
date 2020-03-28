package com.jaspervanhienen.tentamen

import com.jaspervanhienen.tentamen.model.PokemonDetail

interface DetailCallback {
    fun onSuccess(result : PokemonDetail)
}