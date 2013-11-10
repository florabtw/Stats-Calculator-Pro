package me.nickpierson.StatsCalculatorPro.pc;

import me.nickpierson.StatsCalculator.pc.PCView;
import me.nickpierson.StatsCalculatorPro.utils.ProKeypadHelper;
import android.app.Activity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ProPCView extends PCView {

	ProKeypadHelper proKeypadHelper;

	public ProPCView(Activity activity) {
		super(activity);

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
}
