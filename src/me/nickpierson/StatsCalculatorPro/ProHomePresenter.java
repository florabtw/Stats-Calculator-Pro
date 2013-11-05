package me.nickpierson.StatsCalculatorPro;

import me.nickpierson.StatsCalculator.HomeModel;
import me.nickpierson.StatsCalculator.HomePresenter;
import me.nickpierson.StatsCalculator.HomeView;
import me.nickpierson.StatsCalculatorPro.basic.ProBasicActivity;
import me.nickpierson.StatsCalculatorPro.pc.ProPCActivity;
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

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				activity.startActivity(new Intent(activity, ProPCActivity.class));
			}
		}, HomeView.Types.PERM_COMB_BUTTON);
	}

}
