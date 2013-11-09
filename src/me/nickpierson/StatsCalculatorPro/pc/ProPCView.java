package me.nickpierson.StatsCalculatorPro.pc;

import me.nickpierson.StatsCalculator.pc.PCView;
import android.app.Activity;

public class ProPCView extends PCView {

	public enum ProTypes {
		MENU_SETTINGS;
	}

	public ProPCView(Activity activity) {
		super(activity);
	}

	public void menuSettings() {
		event(ProTypes.MENU_SETTINGS);
	}

}
