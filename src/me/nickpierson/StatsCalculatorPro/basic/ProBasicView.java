package me.nickpierson.StatsCalculatorPro.basic;

import java.util.HashMap;

import me.nickpierson.StatsCalculator.basic.BasicView;
import me.nickpierson.StatsCalculator.utils.Constants;
import me.nickpierson.StatsCalculatorPro.IHelperView;
import me.nickpierson.StatsCalculatorPro.R;
import me.nickpierson.StatsCalculatorPro.utils.ProConstants;
import me.nickpierson.StatsCalculatorPro.utils.ProKeypadHelper;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ProBasicView extends BasicView implements IHelperView {

	public enum ProTypes {
		ITEM_CLICK;
	}

	ProKeypadHelper proKeypadHelper;
	private RelativeLayout proResults;
	private LinearLayout controller;

	public ProBasicView(Activity activity) {
		super(activity);

		proResults = (RelativeLayout) LayoutInflater.from(activity).inflate(R.layout.pro_basic, null);
		controller = (LinearLayout) proResults.findViewById(R.id.basic_controller);

		/*
		 * Align with parent top. Put above the 'controller' and align with
		 * parent (bottom) if 'controller' is missing
		 */
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 0);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.ABOVE, R.id.basic_controller);
		params.alignWithParent = true;

		proResults.addView(lvResults, 0, params);

		resultsAdapter = new ProBasicAdapter(activity, R.layout.basic_result_item);
		resultsAdapter.addAll(Constants.BASIC_TITLES);
		resultsAdapter.addAll(ProConstants.PRO_BASIC_TITLES);

		lvResults.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lvResults.setAdapter(resultsAdapter);

		proKeypadHelper = new ProKeypadHelper(activity);
		ImageButton btnBackspace = (ImageButton) tlKeypad.findViewById(R.id.keypad_backspace);

		proKeypadHelper.disableSoftInputFromAppearing(etInput);
		proKeypadHelper.watchEditText(etInput);

		btnBackspace.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				proKeypadHelper.longPressBackspace(etInput);
				return true;
			}
		});

		lvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
				HashMap<Enum<?>, Integer> map = new HashMap<Enum<?>, Integer>();
				map.put(ProTypes.ITEM_CLICK, pos);
				dataEvent(ProTypes.ITEM_CLICK, map);
			}
		});
	}

	@Override
	public void showResults() {
		flFrame.removeAllViews();
		flFrame.addView(proResults);
	}

	public void showController() {
		controller.setVisibility(View.VISIBLE);
	}

	public void hideController() {
		controller.setVisibility(View.GONE);
	}

	public void setSelectedPosition(int pos) {
		((ProBasicAdapter) resultsAdapter).setSelectedPos(pos);
	}

	public int getSelectedPosition() {
		return ((ProBasicAdapter) resultsAdapter).getSelectedPosition();
	}

	public void clearChoices() {
		lvResults.clearChoices();
	}

	public void wakeLock() {
		view.setKeepScreenOn(true);
	}

	public void keypadPress(Button button) {
		/* Skips MVP */
		proKeypadHelper.keypadPress(etInput, button.getText().charAt(0));
	}

	public void backspace() {
		/* Skips MVP */
		proKeypadHelper.backspace(etInput);
	}
}