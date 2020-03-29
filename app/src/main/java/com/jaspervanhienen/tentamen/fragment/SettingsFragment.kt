package com.jaspervanhienen.tentamen.fragment

import android.os.Bundle
import android.preference.PreferenceFragment
import android.provider.Settings
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import com.jaspervanhienen.tentamen.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        Log.d("rootkey", "root" + rootKey)
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

//    override fun onNavigateToScreen(preferenceScreen: PreferenceScreen?) {
//        val settings = SettingsFragment()
//        val arguments = Bundle()
//        arguments.putString("rootKey", preferenceScreen?.key)
//        settings.arguments = arguments
//
//        Log.d("subsetting", "create" + settings.arguments)
//
//        activity!!.getSupportFragmentManager().beginTransaction()
//            .replace(R.id.settings, settings, preferenceScreen?.key)
//            .addToBackStack(preferenceScreen?.key)
//            .commit();
//    }
}
