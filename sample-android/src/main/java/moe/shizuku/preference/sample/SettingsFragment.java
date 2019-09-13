package moe.shizuku.preference.sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;

import moe.shizuku.preference.CheckBoxPreference;
import moe.shizuku.preference.ListPreference;
import moe.shizuku.preference.Preference;
import moe.shizuku.preference.PreferenceCategory;
import moe.shizuku.preference.PreferenceFragment;

/**
 * An example of the usage of {@link PreferenceFragment}.
 */

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        getPreferenceManager().setDefaultPackages(new String[]{BuildConfig.APPLICATION_ID + "."});

        getPreferenceManager().setSharedPreferencesName("settings");
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_PRIVATE);

        setPreferencesFromResource(R.xml.settings, null);

        ListPreference listPreference;
        listPreference = (ListPreference) findPreference("drop_down2");
        listPreference.setEntries(new CharSequence[]{"Item 1", "Item 2"});
        listPreference.setEntryValues(new CharSequence[]{Locale.CHINESE.toString(), Locale.ENGLISH.toString()});
        if (listPreference.getValue() == null) {
            listPreference.setValueIndex(1);
        }
        listPreference.setOnPreferenceChangeListener(this);

        listPreference = (ListPreference) findPreference("drop_down3");
        listPreference.setEntries(new CharSequence[]{"Vertical align selected item", "(｡>﹏<｡)", "(っ╹ ◡ ╹ )っ\uD83D\uDC8A\uFEFF ヽ(✿ﾟ▽ﾟ)ノ", "⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄"});
        listPreference.setEntryValues(new CharSequence[]{"0", "1", "2", "3"});
        if (listPreference.getValue() == null) {
            listPreference.setValueIndex(2);
        }
        listPreference.setOnPreferenceChangeListener(this);

        findPreference("drop_down4").setOnPreferenceChangeListener(this);

        listPreference = (ListPreference) findPreference("drop_down5");
        listPreference.setEntries(new CharSequence[]{"This is a very long item. It will use a simple dialog if its text may wraps to more than one line", "Don't put too many item when use simple dialog", "（<ゝω・）☆"});
        listPreference.setEntryValues(new CharSequence[]{"0", "1", "2"});
        if (listPreference.getValue() == null) {
            listPreference.setValueIndex(2);
        }
        listPreference.setOnPreferenceChangeListener(this);

        PreferenceCategory preferenceCategory = new PreferenceCategory(getContext());
        preferenceCategory.setTitle("Test add dynamically");
        getPreferenceScreen().addPreference(preferenceCategory);

        CheckBoxPreference checkBoxPreference = new CheckBoxPreference(getContext());
        checkBoxPreference.setTitle("Test");
        checkBoxPreference.setSummaryOn("ON");
        checkBoxPreference.setSummaryOff("OFF");
        getPreferenceScreen().addPreference(checkBoxPreference);
    }

    @Override
    public DividerDecoration onCreateItemDecoration() {
        return new CategoryDivideDividerDecoration();
        //return new DefaultDividerDecoration();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(TAG, "onSharedPreferenceChanged " + key);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Log.d(TAG, getString(R.string.on_preference_change_toast_message, preference.getKey(), newValue.toString()));
        return true;
    }

}
