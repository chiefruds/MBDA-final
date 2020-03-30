package com.jaspervanhienen.tentamen.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.preference.*
import com.jaspervanhienen.tentamen.R


class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        Log.d("rootkey", "root" + rootKey)
        setPreferencesFromResource(R.xml.preferences, rootKey)
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    override fun onSharedPreferenceChanged(
        sharedPreferences: SharedPreferences?,
        key: String?
    ) {
        updateSummary(preferenceScreen)
    }

    private fun updateSummary(p: Preference): String {
        val preferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(activity)
        p.summary = ""
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
        return p.summary.toString()
    }
}
