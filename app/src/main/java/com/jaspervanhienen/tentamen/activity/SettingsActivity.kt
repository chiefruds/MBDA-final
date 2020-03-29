package com.jaspervanhienen.tentamen.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.jaspervanhienen.tentamen.R


abstract class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("settings", "create settings")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

    }

}
