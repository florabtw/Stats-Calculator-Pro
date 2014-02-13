package me.nickpierson.StatsCalculatorPro.utils;

import me.nickpierson.StatsCalculatorPro.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ProThemeHelper {

	// untested
	public static void handleTheme(Activity activity) {
		if (getSharedPref(activity).getBoolean(activity.getString(R.string.dark_mode), false)) {
			activity.setTheme(R.style.DarkNotHomeTheme);
		}
	}

	public static boolean isDarkTheme(Activity activity) {
		return getSharedPref(activity).getBoolean(activity.getString(R.string.dark_mode), false);
	}

	private static SharedPreferences getSharedPref(Activity activity) {
		return PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
	}
}
