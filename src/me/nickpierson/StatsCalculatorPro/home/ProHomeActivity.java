package me.nickpierson.StatsCalculatorPro.home;

import me.nickpierson.StatsCalculator.home.HomeActivity;
import me.nickpierson.StatsCalculator.home.HomeModel;
import me.nickpierson.StatsCalculator.home.HomeView;
import me.nickpierson.StatsCalculatorPro.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ProHomeActivity extends HomeActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		view = new HomeView(this);
		model = new HomeModel();
		ProHomePresenter.create(this, model, view);

		setContentView(view.getView());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pro_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == R.id.home_settings) {
			// TODO
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
}
