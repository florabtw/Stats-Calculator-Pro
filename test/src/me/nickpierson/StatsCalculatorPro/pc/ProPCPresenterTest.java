package me.nickpierson.StatsCalculatorPro.pc;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import me.nickpierson.StatsCalculator.pc.PCPresenterTest;
import me.nickpierson.StatsCalculatorPro.settings.SettingsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;
import android.content.Intent;

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

	@Test
	public void whenSettingsMenuIsPressed_SettingsScreenIsOpened() {
		createPresenter();

		verify(view).addListener(listener.capture(), eq(ProPCView.ProTypes.MENU_SETTINGS));

		listener.getValue().fire();

		verify(activity, times(2)).startActivity(new Intent(activity, SettingsActivity.class));
	}
}
