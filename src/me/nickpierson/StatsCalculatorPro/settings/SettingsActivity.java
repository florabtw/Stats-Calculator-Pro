package me.nickpierson.StatsCalculatorPro.settings;

import me.nickpierson.StatsCalculatorPro.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.ListView;

public class SettingsActivity extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		boolean isDarkTheme = sharedPref.getBoolean(getString(R.string.dark_mode), false);
		if (isDarkTheme) {
			setTheme(R.style.DarkNotHomeTheme);
		}

		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);

		if (isDarkTheme) {
			ListView list = getListView();
			list.setDivider(getResources().getDrawable(R.drawable.divider_drawable));
			list.setDividerHeight((int) (getResources().getDisplayMetrics().density));
		}
	}
}
