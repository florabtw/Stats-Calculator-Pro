package me.nickpierson.StatsCalculatorPro.basic;

import java.util.HashMap;

import me.nickpierson.StatsCalculator.basic.BasicModel;
import me.nickpierson.StatsCalculator.basic.BasicPresenter;
import me.nickpierson.StatsCalculatorPro.ProHelper;
import android.app.Activity;

import com.thecellutioncenter.mvplib.DataActionListener;

public class ProBasicPresenter extends BasicPresenter {

	static ProHelper proHelper = new ProHelper();

	public static void create(final Activity activity, final BasicModel model, final ProBasicView view) {
		setup(activity, model, view);

		handleWakeLock(activity, view);

		view.addListener(new DataActionListener() {

			@Override
			public void fire(HashMap<Enum<?>, ?> data) {
				int newPosition = (Integer) data.get(ProBasicView.ProTypes.ITEM_CLICK);
				if (view.getSelectedPosition() == -1) {
					view.showController();
					view.setSelectedPosition(newPosition);
				}
			}
		}, ProBasicView.ProTypes.ITEM_CLICK);
	}

	private static void handleWakeLock(Activity activity, ProBasicView view) {
		proHelper.handleWakeLock(activity, view);
	}
}
