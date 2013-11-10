package me.nickpierson.StatsCalculatorPro.pc;

import static org.mockito.Mockito.mock;
import me.nickpierson.StatsCalculator.pc.PCPresenterTest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ProPCPresenterTest extends PCPresenterTest {

	Activity activity;

	@Override
	@Before
	public void setup() {
		super.setup();

		activity = mock(Activity.class);
	}

	@Override
	public void createPresenter() {
		ProPCPresenter.create(activity, model, view);
	}

}
