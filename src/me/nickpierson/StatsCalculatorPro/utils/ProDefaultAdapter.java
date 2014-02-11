package me.nickpierson.StatsCalculatorPro.utils;

import me.nickpierson.StatsCalculator.utils.DefaultAdapter;
import me.nickpierson.StatsCalculatorPro.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

public class ProDefaultAdapter extends DefaultAdapter {

	private int selectedPos;
	private int itemColor, selectedItemColor;

	public ProDefaultAdapter(Context context, int resource, int titleId, int resultId) {
		super(context, resource, titleId, resultId);
		selectedPos = -1;

		TypedArray attrs = context.getTheme().obtainStyledAttributes(new int[] { R.attr.defaultListItemColor, R.attr.defaultSelectedListItemColor });
		itemColor = attrs.getResourceId(0, Color.WHITE);
		selectedItemColor = attrs.getResourceId(1, R.color.white_selection);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);

		if (position == selectedPos) {
			view.setBackgroundResource(selectedItemColor);
		} else {
			view.setBackgroundResource(itemColor);
		}

		return view;
	}

	public int getSelectedPosition() {
		return selectedPos;
	}

	public void setSelectedPosition(int position) {
		notifyDataSetChanged();
		this.selectedPos = position;
	}
}
