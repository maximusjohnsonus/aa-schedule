package doop.aa_schedule;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.github.machinarius.preferencefragment.PreferenceFragment;

//import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.fragment_preference);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Log.d("SettingsFragment 208", key + ":" + sharedPreferences.getBoolean(key, false));
    }

    @Override
    public void onPause(){
        super.onPause();
        CustomMethods cm = new CustomMethods();
        Log.d("SettingsFragment 402",""+cm.time24(getActivity()));
    }
}