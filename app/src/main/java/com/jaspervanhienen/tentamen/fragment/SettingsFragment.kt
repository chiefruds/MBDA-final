package com.jaspervanhienen.tentamen.fragment

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.preference.*
import com.jaspervanhienen.tentamen.R


class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

   var context = activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        updateSummary(preferenceScreen)
    }

    override fun onSharedPreferenceChanged(
        sharedPreferences: SharedPreferences?,
        key: String?
    ) {
        updateSummary(preferenceScreen)
    }

    private fun updateSummary(p: Preference): String {
        if(context == null)
            context = activity
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            p.summary = ""
        //Log.d("preference", "p" + preferences.all)
        if (p is EditTextPreference) {
            p.setSummary(preferences.getString(p.getKey(), ""))
        } else if (p is ListPreference) {
            val value = preferences.getString(p.getKey(), "")
            val index: Int = (p).findIndexOfValue(value)
            if (index >= 0) {
                p.setSummary((p).entries[index])
            }
        } else if (p is MultiSelectListPreference) {
            val values = preferences.getStringSet(
                p.getKey(),
                emptySet()
            )
            for (value in values!!) {
                val index: Int = (p).findIndexOfValue(value)
                if (index >= 0) {
                    p.setSummary(
                        p.getSummary().toString() + (if (p.getSummary().isEmpty()
                        ) "" else ", ") + (p).entries.get(index)
                    )
                }
            }
        } else if (p is PreferenceCategory) {
            val preference: PreferenceCategory = p
            for (i in 0 until preference.preferenceCount) {
                updateSummary(preference.getPreference(i))
            }
        } else if (p is PreferenceGroup) {
            val preference: PreferenceGroup = p
            for (i in 0 until preference.preferenceCount) {
                p.setSummary(
                    p.getSummary().toString() + (if (p.getSummary().isEmpty()
                    ) "" else ", ") + updateSummary(preference.getPreference(i))
                )
            }
        }
       // Log.d("summary", p.summary.toString())
        return p.summary.toString()
    }
}
