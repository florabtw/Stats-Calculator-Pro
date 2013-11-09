package me.nickpierson.StatsCalculatorPro.home;

import me.nickpierson.StatsCalculator.home.HomeView;
import android.app.Activity;

public class ProHomeView extends HomeView {

	public enum ProTypes {
		MENU_SETTINGS
	}

	public ProHomeView(Activity activity) {
		super(activity);
	}

	public void menuSettings() {
		event(ProTypes.MENU_SETTINGS);
	}
}
