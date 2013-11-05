package me.nickpierson.StatsCalculatorPro.basic.reference;

import me.nickpierson.StatsCalculator.basic.reference.BasicReferenceView;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class ProBasicReferenceActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		BasicReferenceView view = new BasicReferenceView(this);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setContentView(view.getView());
	}

}
