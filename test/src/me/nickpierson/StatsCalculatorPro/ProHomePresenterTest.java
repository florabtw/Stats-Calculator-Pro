package me.nickpierson.StatsCalculatorPro;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import me.nickpierson.StatsCalculator.HomePresenterTest;
import me.nickpierson.StatsCalculator.HomeView;
import me.nickpierson.StatsCalculatorPro.basic.ProBasicActivity;
import me.nickpierson.StatsCalculatorPro.pc.ProPCActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.content.Intent;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ProHomePresenterTest extends HomePresenterTest {

	@Override
	public void createPresenter() {
		ProHomePresenter.create(activity, model, view);
	}

	@Test
	public void whenBasicButtonIsClicked_ThenBasicCalculatorIsOpened() {
		createPresenter();

		verify(view).addListener(listener.capture(), eq(HomeView.Types.DESCRIPTIVE_BUTTON));

		listener.getValue().fire();

		verify(activity, times(2)).startActivity(new Intent(activity, ProBasicActivity.class));
	}

	@Test
	public void whenPermCombButtonIsClicked_ThenPermCombCalculatorIsOpened() {
		createPresenter();

		verify(view).addListener(listener.capture(), eq(HomeView.Types.PERM_COMB_BUTTON));

		listener.getValue().fire();

		verify(activity, times(2)).startActivity(new Intent(activity, ProPCActivity.class));
	}
}
