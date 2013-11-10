package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicModel;
import me.nickpierson.StatsCalculator.basic.BasicPresenter;
import me.nickpierson.StatsCalculatorPro.ProHelper;
import android.app.Activity;

public class ProBasicPresenter extends BasicPresenter {

	static ProHelper proHelper = new ProHelper();

	public static void create(final Activity activity, final BasicModel model, final ProBasicView view) {
		setup(activity, model, view);

		handleWakeLock(activity, view);
	}

	private static void handleWakeLock(Activity activity, ProBasicView view) {
		proHelper.handleWakeLock(activity, view);
	}
}
