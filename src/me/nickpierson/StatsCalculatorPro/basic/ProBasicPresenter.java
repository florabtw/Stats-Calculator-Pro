package me.nickpierson.StatsCalculatorPro.basic;

import java.util.HashMap;

import me.nickpierson.StatsCalculator.basic.BasicPresenter;
import me.nickpierson.StatsCalculatorPro.ProHelper;
import me.nickpierson.StatsCalculatorPro.utils.ProConstants;
import android.app.Activity;

import com.thecellutioncenter.mvplib.ActionListener;
import com.thecellutioncenter.mvplib.DataActionListener;

public class ProBasicPresenter extends BasicPresenter {

	static ProHelper proHelper = new ProHelper();

	public static void create(final Activity activity, final ProBasicModel model, final ProBasicView view) {
		setup(activity, model, view);

		proHelper.handleWakeLock(activity, view);

		proHelper.listenForItemClick(view, ProBasicView.ProTypes.ITEM_CLICK);

		proHelper.listenForMoveUpClick(view, model, ProBasicView.ProTypes.MOVE_UP);

		proHelper.listenForMoveDownClick(view, model, ProBasicView.ProTypes.MOVE_DOWN);

		proHelper.listenForRemoveClick(view, ProBasicView.ProTypes.REMOVE);

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				String selection = view.getSelectedItem();
				String url = model.getEquationUrl(selection);
				view.displayItemInfo(url);
			}
		}, ProBasicView.ProTypes.INFO);

		proHelper.listenForResetList(view, ProBasicView.ProTypes.MENU_RESET_LIST);

		view.addListener(new DataActionListener() {

			@Override
			public void fire(HashMap<Enum<?>, ?> data) {
				int itemPos = (Integer) data.get(ProBasicView.ProTypes.LONG_ITEM_CLICK);
				view.copyItemToClipboard(itemPos);
				view.showToast(ProConstants.COPY_NOTIFICATION);
			}
		}, ProBasicView.ProTypes.LONG_ITEM_CLICK);
	}
}
