package me.nickpierson.StatsCalculatorPro.basic;

import java.util.HashMap;

import me.nickpierson.StatsCalculator.basic.BasicModel;
import me.nickpierson.StatsCalculator.basic.BasicPresenter;
import me.nickpierson.StatsCalculatorPro.ProHelper;
import android.app.Activity;

import com.thecellutioncenter.mvplib.ActionListener;
import com.thecellutioncenter.mvplib.DataActionListener;

public class ProBasicPresenter extends BasicPresenter {

	static ProHelper proHelper = new ProHelper();

	public static void create(final Activity activity, final BasicModel model, final ProBasicView view) {
		setup(activity, model, view);

		handleWakeLock(activity, view);

		view.addListener(new DataActionListener() {

			@Override
			public void fire(HashMap<Enum<?>, ?> data) {
				int oldPosition = view.getSelectedPosition();
				int newPosition = (Integer) data.get(ProBasicView.ProTypes.ITEM_CLICK);

				if (oldPosition == -1) {
					view.showController();
					view.setSelectedPosition(newPosition);
				} else if (oldPosition != newPosition) {
					view.setSelectedPosition(newPosition);
				} else {
					view.setSelectedPosition(-1);
					view.clearChoices();
					view.hideController();
				}
			}
		}, ProBasicView.ProTypes.ITEM_CLICK);

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				view.moveSelectedItemUp();
			}
		}, ProBasicView.ProTypes.MOVE_UP);

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				view.moveSelectedItemDown();
			}
		}, ProBasicView.ProTypes.MOVE_DOWN);
	}

	private static void handleWakeLock(Activity activity, ProBasicView view) {
		proHelper.handleWakeLock(activity, view);
	}
}
