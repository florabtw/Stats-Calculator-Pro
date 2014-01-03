package me.nickpierson.StatsCalculatorPro.pc;

import java.util.HashMap;

import me.nickpierson.StatsCalculator.pc.PCModel;
import me.nickpierson.StatsCalculator.pc.PCPresenter;
import me.nickpierson.StatsCalculatorPro.ProHelper;
import android.app.Activity;

import com.thecellutioncenter.mvplib.DataActionListener;

public class ProPCPresenter extends PCPresenter {

	static ProHelper proHelper = new ProHelper();

	public static void create(final Activity activity, final PCModel model, final ProPCView view) {
		setup(model, view);

		handleWakeLock(activity, view);

		view.addListener(new DataActionListener() {
			@Override
			public void fire(HashMap<Enum<?>, ?> data) {
				int oldPosition = view.getSelectedPosition();
				int newPosition = (Integer) data.get(ProPCView.ProTypes.ITEM_CLICK);

				if (oldPosition == -1) {
					view.showController();
					view.setSelectedPosition(newPosition);
				} else if (oldPosition != newPosition) {
					view.setSelectedPosition(newPosition);
				} else {
					deselect(view);
				}
			}
		}, ProPCView.ProTypes.ITEM_CLICK);
	}

	private static void handleWakeLock(Activity activity, ProPCView view) {
		proHelper.handleWakeLock(activity, view);
	}

	protected static void deselect(ProPCView view) {
		view.setSelectedPosition(-1);
		view.clearChoices();
		view.hideController();
	}
}