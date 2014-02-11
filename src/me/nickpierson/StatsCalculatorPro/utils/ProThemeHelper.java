package me.nickpierson.StatsCalculatorPro.utils;

import me.nickpierson.StatsCalculatorPro.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ProThemeHelper {

	// untested
	public static void handleTheme(Activity activity) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		if (sharedPref.getBoolean(activity.getString(R.string.dark_mode), false)) {
			activity.setTheme(R.style.DarkNotHomeTheme);
		}
	}
}
