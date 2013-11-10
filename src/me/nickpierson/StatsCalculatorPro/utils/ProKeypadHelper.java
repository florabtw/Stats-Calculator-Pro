package me.nickpierson.StatsCalculatorPro.utils;

import me.nickpierson.StatsCalculator.utils.KeypadHelper;
import me.nickpierson.StatsCalculatorPro.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.view.HapticFeedbackConstants;
import android.widget.EditText;

public class ProKeypadHelper extends KeypadHelper {

	boolean vibrateFeedback;
	private AudioManager audioManager;

	public ProKeypadHelper(Activity activity) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
		vibrateFeedback = sharedPref.getBoolean(activity.getString(R.string.to_vibrate), false);

		if (sharedPref.getBoolean(activity.getString(R.string.to_sound), false)) {
			audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
		}
	}

	@Override
	public void keypadPress(EditText etInput, char character) {
		if (vibrateFeedback) {
			etInput.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
		}

		if (audioManager != null) {
			audioManager.playSoundEffect(AudioManager.FX_KEY_CLICK, -1.0f);
		}

		super.keypadPress(etInput, character);
	}

	@Override
	public void backspace(EditText etInput) {
		if (vibrateFeedback) {
			etInput.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
		}

		super.backspace(etInput);
	}
}
