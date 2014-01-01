package me.nickpierson.StatsCalculatorPro.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
		ArrayList<String> resultsOrder = loadResults(prefs.getString(RESULTS_KEY, null));

		view = new ProBasicView(this, resultsOrder);
		model = new ProBasicModel(this);
		ProBasicPresenter.create(this, (ProBasicModel) model, (ProBasicView) view);

		setContentView(view.getView());
	}

	protected ArrayList<String> loadResults(String joinedString) {
		ArrayList<String> resultsOrder;
		if (joinedString == null) {
			resultsOrder = new ArrayList<String>(Arrays.asList(Constants.BASIC_TITLES));
			Collections.addAll(resultsOrder, ProConstants.PRO_BASIC_TITLES);
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
		ArrayList<String> items = ((ProBasicView) view).getAllItems();

		for (int i = 0; i < items.size(); i++) {
			builder.append(items.get(i) + ",");
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
