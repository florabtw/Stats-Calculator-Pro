package me.nickpierson.StatsCalculatorPro.pc;

import me.nickpierson.StatsCalculator.pc.PCModel;
import me.nickpierson.StatsCalculator.pc.PCPresenter;
import me.nickpierson.StatsCalculator.pc.PCView;
import me.nickpierson.StatsCalculatorPro.settings.ProSettingsActivity;
import android.app.Activity;
import android.content.Intent;

import com.thecellutioncenter.mvplib.ActionListener;

public class ProPCPresenter extends PCPresenter {

	public static void create(final Activity activity, final PCModel model, final PCView view) {
		setup(model, view);

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				activity.startActivity(new Intent(activity, ProSettingsActivity.class));
			}
		}, ProPCView.ProTypes.MENU_SETTINGS);
	}

}
