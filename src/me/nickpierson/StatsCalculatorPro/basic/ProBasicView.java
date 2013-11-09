package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicView;
import android.app.Activity;

public class ProBasicView extends BasicView {

	public enum ProTypes {
		MENU_REFERENCE, MENU_SETTINGS;
	}

	public ProBasicView(Activity activity) {
		super(activity);
	}

	public void menuSettings() {
		event(ProTypes.MENU_SETTINGS);
	}
}
