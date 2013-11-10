package me.nickpierson.StatsCalculatorPro.pc;

import me.nickpierson.StatsCalculator.pc.PCModel;
import me.nickpierson.StatsCalculator.pc.PCPresenter;
import me.nickpierson.StatsCalculator.pc.PCView;
import android.app.Activity;

public class ProPCPresenter extends PCPresenter {

	public static void create(final Activity activity, final PCModel model, final PCView view) {
		setup(model, view);
	}

}
