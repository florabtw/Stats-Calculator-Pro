package me.nickpierson.StatsCalculatorPro;

import me.nickpierson.StatsCalculator.HomeActivity;
import me.nickpierson.StatsCalculator.HomeModel;
import me.nickpierson.StatsCalculator.HomeView;
import android.os.Bundle;

public class ProHomeActivity extends HomeActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		view = new HomeView(this);
		model = new HomeModel();
		ProHomePresenter.create(this, model, view);

		setContentView(view.getView());
	}
}
