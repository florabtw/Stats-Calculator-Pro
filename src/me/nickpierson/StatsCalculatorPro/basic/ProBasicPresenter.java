package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicModel;
import me.nickpierson.StatsCalculator.basic.BasicPresenter;
import me.nickpierson.StatsCalculator.basic.BasicView;
import android.app.Activity;

public class ProBasicPresenter extends BasicPresenter {

	public static void create(final Activity activity, final BasicModel model, final BasicView view) {
		setup(activity, model, view);
	}
}
