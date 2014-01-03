package me.nickpierson.StatsCalculatorPro.pc;

import me.nickpierson.StatsCalculator.pc.PCActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProPCActivity extends PCActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		model = new ProPCModel();
		view = new ProPCView(this);
		ProPCPresenter.create(this, (ProPCModel) model, (ProPCView) view);

		setContentView(view.getView());
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
