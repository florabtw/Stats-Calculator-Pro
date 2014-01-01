package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicActivity;
import me.nickpierson.StatsCalculator.utils.Constants;
import me.nickpierson.StatsCalculatorPro.utils.ProConstants;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProBasicActivity extends BasicActivity {

	private static final String RESULTS_KEY = "basic results order";
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		prefs = getPreferences(MODE_PRIVATE);
		String[] resultsOrder = loadResults(prefs.getString(RESULTS_KEY, null));

		view = new ProBasicView(this, resultsOrder);
		model = new ProBasicModel(this);
		ProBasicPresenter.create(this, (ProBasicModel) model, (ProBasicView) view);

		setContentView(view.getView());
	}

	protected String[] loadResults(String joinedString) {
		String[] resultsOrder;
		if (joinedString == null) {
			resultsOrder = new String[Constants.BASIC_TITLES.length + ProConstants.PRO_BASIC_TITLES.length];
			System.arraycopy(Constants.BASIC_TITLES, 0, resultsOrder, 0, Constants.BASIC_TITLES.length);
			System.arraycopy(ProConstants.PRO_BASIC_TITLES, 0, resultsOrder, Constants.BASIC_TITLES.length, ProConstants.PRO_BASIC_TITLES.length);
		} else {
			resultsOrder = joinedString.split(",");
		}
		return resultsOrder;
	}

	@Override
	protected void onPause() {
		super.onPause();

		SharedPreferences.Editor editor = prefs.edit();
		StringBuilder builder = new StringBuilder();
		String[] items = ((ProBasicView) view).getAllItems();

		for (int i = 0; i < items.length; i++) {
			builder.append(items[i] + ",");
		}

		editor.putString(RESULTS_KEY, builder.toString());
		editor.commit();
	}

	@Override
	public void keypadPress(View button) {
		((ProBasicView) view).keypadPress((Button) button);
	}

	@Override
	public void backSpace(View button) {
		((ProBasicView) view).backspace();
	}
}
