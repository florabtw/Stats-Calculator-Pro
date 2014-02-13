package me.nickpierson.StatsCalculatorPro.settings;

import me.nickpierson.StatsCalculatorPro.R;
import me.nickpierson.StatsCalculatorPro.utils.ProConstants;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	Toast toast;

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

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(getString(R.string.dark_mode))) {
			showToast(ProConstants.SETTINGS_NOTIFICATION);
		}
	}

	public void showToast(String message) {
		try {
			toast.getView().isShown();
			toast.setText(message);
		} catch (Exception e) {
			toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
		}
		toast.show();
	}
}
