package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicAdapter;
import android.content.Context;

public class ProBasicAdapter extends BasicAdapter {

	private int selectedPos;

	public ProBasicAdapter(Context context, int resource) {
		super(context, resource);
		selectedPos = -1;
	}

	public int getSelectedPosition() {
		return selectedPos;
	}

	public void setSelectedPos(int position) {
		this.selectedPos = position;
	}
}
