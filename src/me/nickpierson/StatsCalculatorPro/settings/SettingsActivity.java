package me.nickpierson.StatsCalculatorPro.settings;

import me.nickpierson.StatsCalculatorPro.R;
import me.nickpierson.StatsCalculatorPro.utils.ProThemeHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.ListView;

public class SettingsActivity extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ProThemeHelper.handleTheme(this);

		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);

		// ListView list = getListView();
		// list.setDivider(new ColorDrawable(Color.RED));
		// list.setDividerHeight(10);
	}
}
