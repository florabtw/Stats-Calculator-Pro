package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicModel;
import me.nickpierson.StatsCalculator.basic.BasicPresenter;
import me.nickpierson.StatsCalculator.basic.BasicView;
import me.nickpierson.StatsCalculatorPro.basic.reference.ProBasicReferenceActivity;
import android.app.Activity;
import android.content.Intent;

import com.thecellutioncenter.mvplib.ActionListener;

public class ProBasicPresenter extends BasicPresenter {

	public static void create(final Activity activity, final BasicModel model, final BasicView view) {
		setup(activity, model, view);

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				activity.startActivity(new Intent(activity, ProBasicReferenceActivity.class));
			}
		}, BasicView.Types.MENU_REFERENCE);
	}
}
