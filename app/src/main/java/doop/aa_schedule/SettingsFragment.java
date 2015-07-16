package doop.aa_schedule;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;

import com.github.machinarius.preferencefragment.PreferenceFragment;

//import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment //implements SharedPreferences.OnSharedPreferenceChangeListener
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.fragment_preference);
        Preference ps = findPreference("intent");
        ps.setIntent(new Intent(getActivity(), Period.class));
    }

    /*@Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Log.d("SettingsFragment 208", key + ":" + sharedPreferences.getBoolean(key, false));
    }*/


}