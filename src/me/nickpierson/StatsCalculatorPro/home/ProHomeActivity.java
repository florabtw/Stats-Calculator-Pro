package me.nickpierson.StatsCalculatorPro.home;

import me.nickpierson.StatsCalculator.home.HomeActivity;
import me.nickpierson.StatsCalculator.home.HomeModel;
import me.nickpierson.StatsCalculatorPro.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

public class ProHomeActivity extends HomeActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		if (sharedPref.getBoolean(getString(R.string.dark_mode), false)) {
			setTheme(R.style.DarkHomeTheme);
		}

		view = new ProHomeView(this);
		model = new HomeModel();
		ProHomePresenter.create(this, model, view);

		setContentView(view.getView());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pro_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == R.id.home_settings) {
			((ProHomeView) view).menuSettings();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
}
