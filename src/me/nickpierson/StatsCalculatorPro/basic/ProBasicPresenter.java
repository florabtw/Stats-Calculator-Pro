package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicModel;
import me.nickpierson.StatsCalculator.basic.BasicPresenter;
import me.nickpierson.StatsCalculatorPro.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ProBasicPresenter extends BasicPresenter {

	public static void create(final Activity activity, final BasicModel model, final ProBasicView view) {
		setup(activity, model, view);

		handleWakeLock(activity, view);
	}

	private static void handleWakeLock(Activity activity, ProBasicView view) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		if (sharedPref.getBoolean(activity.getString(R.string.wake_lock), false)) {
			view.wakeLock();
		}
	}
}
