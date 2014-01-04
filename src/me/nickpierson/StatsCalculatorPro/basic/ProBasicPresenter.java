package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicPresenter;
import me.nickpierson.StatsCalculatorPro.ProHelper;
import android.app.Activity;

public class ProBasicPresenter extends BasicPresenter {

	static ProHelper proHelper = new ProHelper();

	public static void create(final Activity activity, final ProBasicModel model, final ProBasicView view) {
		setup(activity, model, view);

		proHelper.handleWakeLock(activity, view);

		proHelper.listenForItemClick(view, ProBasicView.ProTypes.ITEM_CLICK);

		proHelper.listenForMoveUpClick(view, model, ProBasicView.ProTypes.MOVE_UP);

		proHelper.listenForMoveDownClick(view, model, ProBasicView.ProTypes.MOVE_DOWN);

		proHelper.listenForRemoveClick(view, ProBasicView.ProTypes.REMOVE);

		proHelper.listenForInfoClick(view, model, ProBasicView.ProTypes.INFO);

		proHelper.listenForResetList(view, ProBasicView.ProTypes.MENU_RESET_LIST);

		proHelper.listenForLongItemClick(view, ProBasicView.ProTypes.LONG_ITEM_CLICK);
	}
}
