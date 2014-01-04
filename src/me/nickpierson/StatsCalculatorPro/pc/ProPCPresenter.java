package me.nickpierson.StatsCalculatorPro.pc;

import me.nickpierson.StatsCalculator.pc.PCPresenter;
import me.nickpierson.StatsCalculatorPro.ProHelper;
import android.app.Activity;

public class ProPCPresenter extends PCPresenter {

	static ProHelper proHelper = new ProHelper();

	public static void create(final Activity activity, final ProPCModel model, final ProPCView view) {
		setup(model, view);

		proHelper.handleWakeLock(activity, view);

		proHelper.listenForItemClick(view, ProPCView.ProTypes.ITEM_CLICK);

		proHelper.listenForMoveUpClick(view, model, ProPCView.ProTypes.MOVE_UP);

		proHelper.listenForMoveDownClick(view, model, ProPCView.ProTypes.MOVE_DOWN);

		proHelper.listenForRemoveClick(view, ProPCView.ProTypes.REMOVE);

		proHelper.listenForResetList(view, ProPCView.ProTypes.MENU_RESET_LIST);

		proHelper.listenForInfoClick(view, model, ProPCView.ProTypes.INFO);

		proHelper.listenForLongItemClick(view, ProPCView.ProTypes.LONG_ITEM_CLICK);
	}
}