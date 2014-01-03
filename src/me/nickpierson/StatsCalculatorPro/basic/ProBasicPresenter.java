package me.nickpierson.StatsCalculatorPro.basic;

import java.util.ArrayList;
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

		view.addListener(new ActionListener() {
			@Override
			public void fire() {
				int currPos = view.getSelectedPosition();
				ArrayList<String> currItems = view.getAllItems();
				if (currPos != currItems.size() - 1) {
					model.moveItemDown(currPos, currItems);
					view.replaceItems(currItems);
					view.highlightAndSelect(currPos + 1);
				}
			}
		}, ProBasicView.ProTypes.MOVE_DOWN);

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				int currPos = view.getSelectedPosition();
				ArrayList<String> currItems = view.getAllItems();
				currItems.remove(currPos);
				view.replaceItems(currItems);

				if (currItems.size() == 0) {
					view.hideController();
				} else if (currPos == currItems.size()) {
					view.highlightAndSelect(currPos - 1);
				}
			}
		}, ProBasicView.ProTypes.REMOVE);

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				String selection = view.getSelectedItem();
				String url = model.getEquationUrl(selection);
				view.displayItemInfo(url);
			}
		}, ProBasicView.ProTypes.INFO);

		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				view.resetList();
				deselect(view);
			}
		}, ProBasicView.ProTypes.MENU_RESET_LIST);

		view.addListener(new DataActionListener() {

			@Override
			public void fire(HashMap<Enum<?>, ?> data) {
				int itemPos = (Integer) data.get(ProBasicView.ProTypes.LONG_ITEM_CLICK);
				view.copyItemToClipboard(itemPos);
				view.showToast(ProConstants.COPY_NOTIFICATION);
			}
		}, ProBasicView.ProTypes.LONG_ITEM_CLICK);
	}

	private static void deselect(final ProBasicView view) {
		view.setSelectedPosition(-1);
		view.clearChoices();
		view.hideController();
	}
}
