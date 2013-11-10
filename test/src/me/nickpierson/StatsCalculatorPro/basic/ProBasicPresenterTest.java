package me.nickpierson.StatsCalculatorPro.basic;

import me.nickpierson.StatsCalculator.basic.BasicPresenterTest;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ProBasicPresenterTest extends BasicPresenterTest {

	@Override
	public void createPresenter() {
		ProBasicPresenter.create(activity, model, view);
	}

}
