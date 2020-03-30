package com.jaspervanhienen.tentamen

import com.jaspervanhienen.tentamen.model.Pokemon

public interface PokemonListCallback {
    fun onSuccess(result : MutableList<Pokemon>)
}