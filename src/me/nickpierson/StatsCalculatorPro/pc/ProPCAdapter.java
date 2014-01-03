package me.nickpierson.StatsCalculatorPro.pc;

import me.nickpierson.StatsCalculator.pc.PCAdapter;
import me.nickpierson.StatsCalculatorPro.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class ProPCAdapter extends PCAdapter {

	private int selectedPos;

	public ProPCAdapter(Context context, int resource) {
		super(context, resource);
		selectedPos = -1;
		
		//TODO refactor all of these adapters. Honestly, there needs to be only one between both PC & Basic for all 3 projects
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
