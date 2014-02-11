package me.nickpierson.StatsCalculatorPro.pc;

import java.util.ArrayList;
import java.util.Arrays;

import me.nickpierson.StatsCalculator.pc.PCActivity;
import me.nickpierson.StatsCalculator.utils.Constants;
import me.nickpierson.StatsCalculatorPro.R;
import me.nickpierson.StatsCalculatorPro.utils.ProConstants;
import me.nickpierson.StatsCalculatorPro.utils.ProThemeHelper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ProPCActivity extends PCActivity {

	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ProThemeHelper.handleTheme(this);

		super.onCreate(savedInstanceState);

		prefs = getPreferences(MODE_PRIVATE);
		ArrayList<String> resultsOrder = loadResults(prefs.getString(ProConstants.RESULTS_KEY, null));

		model = new ProPCModel();
		view = new ProPCView(this, resultsOrder);
		ProPCPresenter.create(this, (ProPCModel) model, (ProPCView) view);

		setContentView(view.getView());
	}

	protected ArrayList<String> loadResults(String joinedString) {
		ArrayList<String> resultsOrder;
		if (joinedString == null) {
			resultsOrder = new ArrayList<String>(Arrays.asList(Constants.PC_TITLES));
		} else if (joinedString.length() <= 1) {
			resultsOrder = new ArrayList<String>();
		} else {
			resultsOrder = new ArrayList<String>(Arrays.asList(joinedString.split(",")));
		}

		return resultsOrder;
	}

	@Override
	protected void onPause() {
		super.onPause();

		SharedPreferences.Editor editor = prefs.edit();
		StringBuilder builder = new StringBuilder();
		ArrayList<String> items = ((ProPCView) view).getAllItems();

		for (int i = 0; i < items.size(); i++) {
			builder.append(items.get(i) + ",");
		}

		editor.putString(ProConstants.RESULTS_KEY, builder.toString());
		editor.commit();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt(ProConstants.SELECTED_POS_KEY, ((ProPCView) view).getSelectedPosition());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		if (savedInstanceState != null) {
			int lastPosition = savedInstanceState.getInt(ProConstants.SELECTED_POS_KEY);
			if (lastPosition != -1) {
				((ProPCView) view).setSelectedPosition(lastPosition);
				((ProPCView) view).showController();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pro_shared, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == R.id.menu_basic_reset) {
			((ProPCView) view).menuListReset();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void keypadPress(View button) {
		((ProPCView) view).keypadPress((Button) button);
	}

	@Override
	public void backSpace(View button) {
		((ProPCView) view).backSpace();
	}
}
