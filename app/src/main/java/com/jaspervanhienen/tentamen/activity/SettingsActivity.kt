package com.jaspervanhienen.tentamen.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.jaspervanhienen.tentamen.R
import com.jaspervanhienen.tentamen.fragment.PokemonList
import com.jaspervanhienen.tentamen.fragment.SettingsFragment


class SettingsActivity : AppCompatActivity(),
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("settings", "create settings")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val actionBar = getActionBar(); // or getSupportActionBar() if you are using the suport library

        if(actionBar !== null)
        actionBar.setDisplayHomeAsUpEnabled(true)

        if(savedInstanceState == null) {
            val fragment = SettingsFragment()
            this.getSupportFragmentManager().beginTransaction()
                .add(R.id.settings, fragment, "standard frag")
                .commit();
        }

    }

    override fun onPreferenceStartFragment(caller: PreferenceFragmentCompat, pref: Preference): Boolean {
        // Instantiate the new Fragment
        val args = pref.extras
        args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, pref.key)
        val fragment = SettingsFragment()
        fragment.arguments = args
        Log.d("args are", "t" + fragment.arguments)
        fragment.setTargetFragment(caller, 0)
        // Replace the existing Fragment with the new Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.settings, fragment, pref.key)
            .addToBackStack(pref.key)
            .commit()
        return true
    }



}
