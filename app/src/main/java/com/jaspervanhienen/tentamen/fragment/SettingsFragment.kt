package com.jaspervanhienen.tentamen.fragment

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.preference.*
import com.jaspervanhienen.tentamen.R
import kotlin.reflect.typeOf


class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

   var context = activity

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

        when(p) {
            is EditTextPreference -> updateEditText(p, preferences)
            is ListPreference -> updateListPreference(p, preferences)
            is MultiSelectListPreference -> updateMultiSelectList(p, preferences)
            is PreferenceCategory -> updateCategory(p)
            is PreferenceGroup -> updateGroup(p)
        }

        return p.summary.toString()
    }


    private fun updateEditText(p: Preference, preferences : SharedPreferences) {
        p.summary = preferences.getString(p.key, "")
    }

    //when list, check if not empty and set summary
    private fun updateListPreference(p: ListPreference, preferences: SharedPreferences) {
        val value = preferences.getString(p.key, "")
        val index = p.findIndexOfValue(value)
        if (index >= 0) {
            p.summary = p.entries[index]
        }
    }

    //when multiselect list, retrieve values, loop over values and update preference summary
    private fun updateMultiSelectList(p: MultiSelectListPreference, preferences : SharedPreferences) {
        val values = preferences.getStringSet(
            p.key,
            emptySet()
        )
        for (value in values!!) {
            val index: Int = (p).findIndexOfValue(value)
            if (index >= 0) {
                p.summary = p.summary.toString() + (if (p.summary.isEmpty()
                ) "" else ", ") + (p).entries[index]
            }
        }
    }

    //when group, loop over all preferences and update summary
    private fun updateGroup(p: PreferenceGroup) {
        val preference: PreferenceGroup = p
        for (i in 0 until preference.preferenceCount) {
            p.summary = p.summary.toString() + (if (p.summary.isEmpty()
            ) "" else ", ") + updateSummary(preference.getPreference(i))
        }
    }

    //when category, update summary for each preference
    private fun updateCategory(p: PreferenceCategory) {
        val preference: PreferenceCategory = p
        for (i in 0 until preference.preferenceCount) {
            updateSummary(preference.getPreference(i))
        }
    }
}
