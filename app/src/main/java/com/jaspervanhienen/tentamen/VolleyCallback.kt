package com.jaspervanhienen.tentamen

import com.jaspervanhienen.tentamen.model.Pokemon

public interface VolleyCallback {
    fun onSuccess(result : MutableList<Pokemon>)
}