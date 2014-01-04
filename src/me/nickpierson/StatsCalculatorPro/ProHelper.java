package me.nickpierson.StatsCalculatorPro;

import java.util.ArrayList;
import java.util.HashMap;

import me.nickpierson.StatsCalculatorPro.utils.ProConstants;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.thecellutioncenter.mvplib.ActionListener;
import com.thecellutioncenter.mvplib.DataActionHandler;
import com.thecellutioncenter.mvplib.DataActionListener;

public class ProHelper implements IProHelper {

	@Override
	public <T extends IHelperView> void handleWakeLock(Activity activity, T view) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		if (sharedPref.getBoolean(activity.getString(R.string.wake_lock), false)) {
			view.wakeLock();
		}
	}

	@Override
	public <T extends DataActionHandler & IHelperView> void listenForItemClick(final T view, final Enum<?> type) {
		view.addListener(new DataActionListener() {
			@Override
			public void fire(HashMap<Enum<?>, ?> data) {
				int oldPosition = view.getSelectedPosition();
				int newPosition = (Integer) data.get(type);

				if (oldPosition == -1) {
					view.showController();
					view.setSelectedPosition(newPosition);
				} else if (oldPosition != newPosition) {
					view.setSelectedPosition(newPosition);
				} else {
					deselect(view);
				}
			}
		}, type);
	}

	@Override
	public <T extends DataActionHandler & IHelperView, U extends IHelperModel> void listenForMoveUpClick(final T view, final U model, Enum<?> type) {
		view.addListener(new ActionListener() {
			@Override
			public void fire() {
				int currPos = view.getSelectedPosition();
				if (currPos > 0) {
					ArrayList<String> currItems = view.getAllItems();
					model.moveItemUp(currPos, currItems);
					view.replaceItems(currItems);
					view.highlightAndSelect(currPos - 1);
				}
			}
		}, type);
	}

	@Override
	public <T extends DataActionHandler & IHelperView, U extends IHelperModel> void listenForMoveDownClick(final T view, final U model, Enum<?> type) {
		view.addListener(new ActionListener() {
			@Override
			public void fire() {
				int currPos = view.getSelectedPosition();
				ArrayList<String> currItems = view.getAllItems();
				if (currPos != -1 && currPos != currItems.size() - 1) {
					model.moveItemDown(currPos, currItems);
					view.replaceItems(currItems);
					view.highlightAndSelect(currPos + 1);
				}
			}
		}, type);
	}

	@Override
	public <T extends DataActionHandler & IHelperView> void listenForRemoveClick(final T view, Enum<?> type) {
		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				int currPos = view.getSelectedPosition();
				if (currPos != -1) {
					ArrayList<String> currItems = view.getAllItems();
					currItems.remove(currPos);
					view.replaceItems(currItems);

					if (currItems.size() == 0) {
						view.hideController();
					} else if (currPos == currItems.size()) {
						view.highlightAndSelect(currPos - 1);
					}
				}
			}
		}, type);
	}

	@Override
	public <T extends DataActionHandler & IHelperView> void listenForResetList(final T view, Enum<?> type) {
		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				view.resetList();
				deselect(view);
			}
		}, type);
	}

	@Override
	public <T extends DataActionHandler & IHelperView, U extends IHelperModel> void listenForInfoClick(final T view, final U model, Enum<?> type) {
		view.addListener(new ActionListener() {

			@Override
			public void fire() {
				String selection = view.getSelectedItem();
				if (selection.length() != 0) {
					String url = model.getEquationUrl(selection);
					view.displayItemInfo(url);
				}
			}
		}, type);

	}

	@Override
	public <T extends DataActionHandler & IHelperView> void listenForLongItemClick(final T view, final Enum<?> type) {
		view.addListener(new DataActionListener() {

			@Override
			public void fire(HashMap<Enum<?>, ?> data) {
				int itemPos = (Integer) data.get(type);
				view.copyItemToClipboard(itemPos);
				view.showToast(ProConstants.COPY_NOTIFICATION);
			}
		}, type);
	}

	private <T extends DataActionHandler & IHelperView> void deselect(T view) {
		view.setSelectedPosition(-1);
		view.clearChoices();
		view.hideController();
	}
}
