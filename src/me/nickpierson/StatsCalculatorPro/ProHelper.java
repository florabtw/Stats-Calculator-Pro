package me.nickpierson.StatsCalculatorPro;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ProHelper implements IProHelper {

	@Override
	public <T extends IHelperView> void handleWakeLock(Activity activity, T view) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		if (sharedPref.getBoolean(activity.getString(R.string.wake_lock), false)) {
			view.wakeLock();
		}
	}

}
