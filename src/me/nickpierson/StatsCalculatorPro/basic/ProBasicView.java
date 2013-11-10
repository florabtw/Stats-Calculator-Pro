package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicView;
import me.nickpierson.StatsCalculatorPro.utils.ProKeypadHelper;
import android.app.Activity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.nickpierson.me.StatsCalculator.R;

public class ProBasicView extends BasicView {

	ProKeypadHelper proKeypadHelper;

	public ProBasicView(Activity activity) {
		super(activity);

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
