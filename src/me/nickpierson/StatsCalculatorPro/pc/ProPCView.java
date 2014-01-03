package me.nickpierson.StatsCalculatorPro.pc;

import java.util.HashMap;

import me.nickpierson.StatsCalculator.pc.PCView;
import me.nickpierson.StatsCalculatorPro.IHelperView;
import me.nickpierson.StatsCalculatorPro.R;
import me.nickpierson.StatsCalculatorPro.utils.ProKeypadHelper;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ProPCView extends PCView implements IHelperView {

	public enum ProTypes {
		ITEM_CLICK;
	}

	ProKeypadHelper proKeypadHelper;
	private RelativeLayout proResults;
	private ListView lvResults;
	private LinearLayout controller;

	public ProPCView(Activity activity) {
		super(activity, new ProPCAdapter(activity, R.layout.perm_comb_results_item));

		proResults = (RelativeLayout) LayoutInflater.from(activity).inflate(R.layout.pro_results_list, null);
		controller = (LinearLayout) proResults.findViewById(R.id.pro_results_controller);
		lvResults = (ListView) proResults.findViewById(R.id.pro_lv_results);

		lvResults.setAdapter(resultsAdapter);
		lvResults.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		flFrame.addView(proResults);

		proKeypadHelper = new ProKeypadHelper(activity);
		proKeypadHelper.disableSoftInputFromAppearing(etNVal);
		proKeypadHelper.disableSoftInputFromAppearing(etRVal);
		proKeypadHelper.disableSoftInputFromAppearing(etNVals);

		btnBackspace.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				EditText etSelected = getSelectedEditText();
				if (etSelected != null) {
					proKeypadHelper.longPressBackspace(etSelected);
				}
				return true;
			}
		});

		lvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
				// TODO this is duplicate code from ProBasicView
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

	@Override
	public void wakeLock() {
		view.setKeepScreenOn(true);
	}

	public void keypadPress(Button button) {
		/* Skips MVP */
		EditText etSelected = getSelectedEditText();

		if (etSelected != null) {
			proKeypadHelper.keypadPress(etSelected, button.getText().charAt(0));
		}
	}

	public void backSpace() {
		/* Skips MVP */
		EditText etSelected = getSelectedEditText();

		if (etSelected != null) {
			proKeypadHelper.backspace(etSelected);
		}
	}

	private EditText getSelectedEditText() {
		EditText etSelected = null;

		if (etNVal.isFocused()) {
			etSelected = etNVal;
		} else if (etRVal.isFocused()) {
			etSelected = etRVal;
		} else if (etNVals.isFocused()) {
			etSelected = etNVals;
		}

		return etSelected;
	}

	public int getSelectedPosition() {
		return ((ProPCAdapter) resultsAdapter).getSelectedPosition();
	}

	public void showController() {
		controller.setVisibility(View.VISIBLE);
	}

	public void hideController() {
		controller.setVisibility(View.GONE);
	}

	public void setSelectedPosition(int pos) {
		((ProPCAdapter) resultsAdapter).setSelectedPos(pos);
	}

	public void clearChoices() {
		lvResults.clearChoices();
	}
}
