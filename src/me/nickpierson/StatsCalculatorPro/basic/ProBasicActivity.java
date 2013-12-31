package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProBasicActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		view = new ProBasicView(this);
		model = new ProBasicModel(this);
		ProBasicPresenter.create(this, (ProBasicModel) model, (ProBasicView) view);

		setContentView(view.getView());
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
