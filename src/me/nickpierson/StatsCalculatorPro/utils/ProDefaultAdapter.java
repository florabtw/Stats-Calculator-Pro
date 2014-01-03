package me.nickpierson.StatsCalculatorPro.utils;

import me.nickpierson.StatsCalculator.utils.DefaultAdapter;
import me.nickpierson.StatsCalculatorPro.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class ProDefaultAdapter extends DefaultAdapter {

	private int selectedPos;

	public ProDefaultAdapter(Context context, int resource, int titleId, int resultId) {
		super(context, resource, titleId, resultId);
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
