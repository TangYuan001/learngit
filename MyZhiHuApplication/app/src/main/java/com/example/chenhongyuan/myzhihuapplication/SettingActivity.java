package com.example.chenhongyuan.myzhihuapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.microedition.khronos.egl.EGLDisplay;

/**
 * Created by chenhongyuan on 15/7/22.
 */
public class SettingActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener{
    CheckBoxPreference checkBoxPreference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_layout);
        checkBoxPreference = (CheckBoxPreference)findPreference("apply_Wifi");
        Log.e("cBox-initial", checkBoxPreference.toString());
        checkBoxPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Toast.makeText(SettingActivity.this, "Wifi改变值为" + newValue, Toast.LENGTH_LONG).show();
                Log.e("cBox1-change", newValue.toString());
                return true;
            }
        });
//        checkBoxPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                Toast.makeText(SettingActivity.this, "Click apply_Wifi", Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });
        EditTextPreference editTextPreference = (EditTextPreference)findPreference("number_edit");
        Log.e("EditText-initial", editTextPreference.toString());
        editTextPreference.setPositiveButtonText("好的");
        editTextPreference.setNegativeButtonText("再想想");
        editTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.e("EditText", newValue.toString());
                return true;
            }
        });
        ListPreference listPreference = (ListPreference)findPreference("apply_department");
        Log.e("Department-initial", listPreference.toString());
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.e("Department", newValue.toString());
                return true;
            }
        });
        RingtonePreference ringtonePreference = (RingtonePreference)findPreference("ring_key");
        Log.e("sound-initial", ringtonePreference.toString());
        ringtonePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.e("Sound", newValue.toString());
                return true;
            }
        });
        MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference)findPreference("apply_multiDepartment");
        multiSelectListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.e("multi-department change", newValue.toString());
                return true;
            }
        });
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean cBox1 = sharedPreferences.getBoolean("apply_Wifi", false);
        if(cBox1){
            Log.e("cBox1", "true");
        } else {
            Log.e("cBox1", "false");
        }
        String phoneNumber = sharedPreferences.getString("number_edit", "");
        Log.e("EditText", phoneNumber);
        Log.e("Department", sharedPreferences.getString("apply_department", ""));
        Log.e("Sound", sharedPreferences.getString("ring_key", ""));
        Set<String> multiDepart = new HashSet<String>();
        Log.e("muliti-department", sharedPreferences.getStringSet("apply_multiDepartment", multiDepart).toString());








    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        return false;
    }
}
