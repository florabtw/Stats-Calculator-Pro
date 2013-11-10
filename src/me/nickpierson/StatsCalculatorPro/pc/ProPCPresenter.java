package me.nickpierson.StatsCalculatorPro.pc;

import me.nickpierson.StatsCalculator.pc.PCModel;
import me.nickpierson.StatsCalculator.pc.PCPresenter;
import me.nickpierson.StatsCalculatorPro.ProHelper;
import android.app.Activity;

public class ProPCPresenter extends PCPresenter {

	static ProHelper proHelper = new ProHelper();

	public static void create(final Activity activity, final PCModel model, final ProPCView view) {
		setup(model, view);

		handleWakeLock(activity, view);
	}

	private static void handleWakeLock(Activity activity, ProPCView view) {
		proHelper.handleWakeLock(activity, view);
	}
}
