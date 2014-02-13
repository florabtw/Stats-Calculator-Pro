package me.nickpierson.StatsCalculatorPro.pc;

import java.util.ArrayList;

import me.nickpierson.StatsCalculator.pc.PCModel;
import me.nickpierson.StatsCalculatorPro.IHelperModel;
import me.nickpierson.StatsCalculatorPro.utils.ProConstants;
import me.nickpierson.StatsCalculatorPro.utils.ProThemeHelper;
import android.app.Activity;

public class ProPCModel extends PCModel implements IHelperModel {

	private Activity activity;

	public ProPCModel(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void moveItemUp(int pos, ArrayList<String> currItems) {
		moveItem(currItems, pos, pos - 1);
	}

	@Override
	public void moveItemDown(int pos, ArrayList<String> currItems) {
		moveItem(currItems, pos, pos + 1);
	}

	private void moveItem(ArrayList<String> items, int pos, int desiredPos) {
		String item = items.remove(pos);
		items.add(desiredPos, item);
	}

	@Override
	public String getEquationUrl(String key) {
		return "file:///android_asset/" + getThemePath() + ProConstants.PCInfoPaths.getPath(key);
	}

	// This is to make testing easier
	String getThemePath() {
		if (ProThemeHelper.isDarkTheme(activity)) {
			return "dark/";
		} else {
			return "light/";
		}
	}
}
