package me.nickpierson.StatsCalculatorPro.pc;

import java.util.ArrayList;
import java.util.HashMap;

import me.nickpierson.StatsCalculator.pc.PCModel;
import me.nickpierson.StatsCalculator.utils.Constants;
import me.nickpierson.StatsCalculatorPro.IHelperModel;

public class ProPCModel extends PCModel implements IHelperModel {

	private HashMap<String, String> urlMap;

	public ProPCModel() {
		super();

		String path = "file:///android_asset/";
		urlMap = new HashMap<String, String>();
		urlMap.put(Constants.N_FACT, path + "n_fact.html");
		urlMap.put(Constants.R_FACT, path + "r_fact.html");
		urlMap.put(Constants.PERM, path + "perm.html");
		urlMap.put(Constants.REP_PERM, path + "rep_perm.html");
		urlMap.put(Constants.COMB, path + "comb.html");
		urlMap.put(Constants.REP_COMB, path + "rep_comb.html");
		urlMap.put(Constants.INDISTINCT_PERM, path + "indistinct_perm.html");
		urlMap.put(Constants.PIGEONHOLE, path + "pigeonhole.html");
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
		return urlMap.get(key);
	}
}
