package me.nickpierson.StatsCalculatorPro.basic;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import me.nickpierson.StatsCalculator.basic.BasicPresenterTest;
import me.nickpierson.StatsCalculator.basic.BasicView;
import me.nickpierson.StatsCalculatorPro.reference.ProBasicReferenceActivity;
import me.nickpierson.StatsCalculatorPro.settings.ProSettingsActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.content.Intent;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ProBasicPresenterTest extends BasicPresenterTest {

	@Override
	public void createPresenter() {
		ProBasicPresenter.create(activity, model, view);
	}

	@Test
	public void whenMenuReferenceGuideIsClicked_ThenReferenceGuideIsShown() {
		createPresenter();

		verify(view).addListener(listener.capture(), eq(BasicView.Types.MENU_REFERENCE));

		listener.getValue().fire();

		verify(activity, times(2)).startActivity(new Intent(activity, ProBasicReferenceActivity.class));
	}

	@Test
	public void whenSettingsMenuIsPressed_SettingsScreenIsOpened() {
		createPresenter();

		verify(view).addListener(listener.capture(), eq(ProBasicView.ProTypes.MENU_SETTINGS));

		listener.getValue().fire();

		verify(activity, times(2)).startActivity(new Intent(activity, ProSettingsActivity.class));
	}
}
