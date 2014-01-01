package me.nickpierson.StatsCalculatorPro.basic;

import java.util.ArrayList;
import java.util.HashMap;

import me.nickpierson.StatsCalculator.basic.BasicPresenter;
import me.nickpierson.StatsCalculatorPro.ProHelper;
import android.app.Activity;

import com.thecellutioncenter.mvplib.ActionListener;
import com.thecellutioncenter.mvplib.DataActionListener;

public class ProBasicPresenter extends BasicPresenter {

	static ProHelper proHelper = new ProHelper();

	public static void create(final Activity activity, final ProBasicModel model, final ProBasicView view) {
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
				int currPos = view.getSelectedPosition();
				if (currPos != 0) {
					ArrayList<String> currItems = view.getAllItems();
					model.moveItemUp(currPos, currItems);
					view.replaceItems(currItems);
					view.highlightAndSelect(currPos - 1);
				}
			}
		}, ProBasicView.ProTypes.MOVE_UP);

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
				view.resetList();
				view.hideController();
				view.clearChoices();
				view.setSelectedPosition(-1);
			}
		}, ProBasicView.ProTypes.MENU_RESET_LIST);
	}

	private static void handleWakeLock(Activity activity, ProBasicView view) {
		proHelper.handleWakeLock(activity, view);
	}
}
