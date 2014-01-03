package me.nickpierson.StatsCalculatorPro.pc;

import java.util.ArrayList;

import me.nickpierson.StatsCalculator.pc.PCModel;
import me.nickpierson.StatsCalculatorPro.IHelperModel;

public class ProPCModel extends PCModel implements IHelperModel {

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
}
