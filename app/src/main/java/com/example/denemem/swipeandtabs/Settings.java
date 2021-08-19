package com.example.denemem.swipeandtabs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.NumberPicker;


public class Settings extends AppCompatPreferenceActivity implements Preference.OnPreferenceClickListener {

    final static int GOAL_DEFAULT = 4000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_screen);


        final SharedPreferences prefs = getSharedPreferences("bunehacı", Context.MODE_PRIVATE);

        Preference goal= findPreference("goal");
        goal.setOnPreferenceClickListener(this);
        goal.setSummary(getString(R.string.goal_summary, prefs.getInt("goal", GOAL_DEFAULT)));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onPreferenceClick(final Preference preference) {

        AlertDialog.Builder builder;

        final SharedPreferences prefs = getSharedPreferences("bunehacı", Context.MODE_PRIVATE);

        switch (preference.getTitleRes()) {
            case R.string.goal:
                builder = new AlertDialog.Builder(this);
                final NumberPicker np = new NumberPicker(this);
                np.setMinValue(1);
                np.setMaxValue(100000);
                np.setValue(prefs.getInt("goal", 10000));
                builder.setView(np);
                builder.setTitle(R.string.set_goal);
                builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    np.clearFocus();
                    prefs.edit().putInt("goal", np.getValue()).apply();
                    preference.setSummary(getString(R.string.goal_summary, np.getValue()));
                    dialog.dismiss();
                });
                builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss());
                Dialog dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                dialog.show();
                break;

        }
        return false;
    }


}