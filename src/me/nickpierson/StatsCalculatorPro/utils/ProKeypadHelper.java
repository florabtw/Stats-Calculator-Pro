package me.nickpierson.StatsCalculatorPro.utils;

import me.nickpierson.StatsCalculator.utils.KeypadHelper;
import me.nickpierson.StatsCalculatorPro.R;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.EditText;

public class ProKeypadHelper extends KeypadHelper {

	boolean vibrateFeedback;

	public ProKeypadHelper(View view) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
		vibrateFeedback = sharedPref.getBoolean(view.getResources().getString(R.string.to_vibrate), false);
	}

	@Override
	public void keypadPress(EditText etInput, char character) {
		if (vibrateFeedback) {
			etInput.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
		}

		super.keypadPress(etInput, character);
	}
}
