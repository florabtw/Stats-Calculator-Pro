package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicActivity;
import me.nickpierson.StatsCalculator.basic.BasicModel;
import me.nickpierson.StatsCalculatorPro.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ProBasicActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		view = new ProBasicView(this);
		model = new BasicModel(this);
		ProBasicPresenter.create(this, model, view);

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
			((ProBasicView) view).menuSettings();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
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
