package me.nickpierson.StatsCalculatorPro.pc;

import me.nickpierson.StatsCalculator.pc.PCActivity;
import me.nickpierson.StatsCalculator.pc.PCModel;
import me.nickpierson.StatsCalculatorPro.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ProPCActivity extends PCActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		model = new PCModel();
		view = new ProPCView(this);
		ProPCPresenter.create(this, model, view);

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
			((ProPCView) view).menuSettings();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
}
