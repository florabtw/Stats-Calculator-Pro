package me.nickpierson.StatsCalculatorPro.home;

import me.nickpierson.StatsCalculator.home.HomeModel;
import me.nickpierson.StatsCalculator.home.HomePresenter;
import me.nickpierson.StatsCalculator.home.HomeView;
import me.nickpierson.StatsCalculatorPro.basic.ProBasicActivity;
import me.nickpierson.StatsCalculatorPro.pc.ProPCActivity;
import me.nickpierson.StatsCalculatorPro.settings.SettingsActivity;
import android.app.Activity;
import android.content.Intent;

import com.thecellutioncenter.mvplib.ActionListener;

public class ProHomePresenter extends HomePresenter {
	public static void create(final Activity activity, HomeModel model, final HomeView view) {
		setup(activity, model, view);

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				activity.startActivity(new Intent(activity, ProBasicActivity.class));
			}
		}, HomeView.Types.DESCRIPTIVE_BUTTON);

		// TODO: The enum is wrong. It should be pulled into the ProHomeView.
		// Same for
		// the other screens. Make sure it isn't doing anything in
		// StatsCalculator library
		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				activity.startActivity(new Intent(activity, ProPCActivity.class));
			}
		}, HomeView.Types.PERM_COMB_BUTTON);

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				activity.startActivity(new Intent(activity, SettingsActivity.class));
			}
		}, ProHomeView.ProTypes.MENU_SETTINGS);
	}

}
