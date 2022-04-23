package com.example.cruddypizza.ui.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.cruddypizza.R;

import java.util.Locale;

public class SettingsFragment extends PreferenceFragmentCompat {
    private static final String TAG = "SettingsFragment";

    private SharedPreferences sharedPreferences;
    private String currentLanguageValue;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        ListPreference mListPreference = (ListPreference) getPreferenceManager().findPreference("language");

        sharedPreferences = getContext().getSharedPreferences("cruddypizza", Activity.MODE_PRIVATE);
        currentLanguageValue = sharedPreferences.getString("language", "en");
        mListPreference.setValue(currentLanguageValue);

        String[] langValues = getResources().getStringArray(R.array.language_values);
        String[] langEntries = getResources().getStringArray(R.array.language_entries);

        for (int i = 0; i < langValues.length; i++) {
            if (currentLanguageValue.equals(langValues[i])) {
                mListPreference.setSummary(langEntries[i]);
                break;
            }
        }

        mListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String languageValue = (String) newValue;
                if (!currentLanguageValue.equals(languageValue)) {
                    currentLanguageValue = languageValue;

                    Locale locale = new Locale(languageValue);
                    Locale.setDefault(locale);
                    Resources resources = getActivity().getResources();
                    Configuration config = resources.getConfiguration();
                    config.setLocale(locale);
                    resources.updateConfiguration(config, resources.getDisplayMetrics());

                    sharedPreferences.edit().putString("language", (String) newValue).apply();
                    getActivity().onConfigurationChanged(getActivity().getResources().getConfiguration());
                }
                return true;
            }
        });
    }
}