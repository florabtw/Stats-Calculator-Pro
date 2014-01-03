package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicAdapter;
import me.nickpierson.StatsCalculatorPro.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class ProBasicAdapter extends BasicAdapter {

	private int selectedPos;

	public ProBasicAdapter(Context context, int resource) {
		super(context, resource);
		selectedPos = -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);

		if (position == selectedPos) {
			view.setBackgroundResource(R.color.white_selection);
		} else {
			view.setBackgroundResource(R.color.fifa);
		}

		return view;
	}

	public int getSelectedPosition() {
		return selectedPos;
	}

	public void setSelectedPos(int position) {
		notifyDataSetChanged();
		this.selectedPos = position;
	}
}
