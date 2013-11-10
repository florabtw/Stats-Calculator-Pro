package me.nickpierson.StatsCalculatorPro.basic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import me.nickpierson.StatsCalculator.basic.BasicPresenterTest;
import me.nickpierson.StatsCalculatorPro.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowPreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ProBasicPresenterTest extends BasicPresenterTest {

	ProBasicView proView;

	private static final String WAKE_LOCK = "wake lock";

	@Override
	@Before
	public void setup() {
		super.setup();

		proView = mock(ProBasicView.class);
	}

	public void createPresenter() {
		ProBasicPresenter.create(activity, model, proView);
	}

	@Test
	public void whenPresenterIsCreated_ViewIsToldToKeepScreenOnIfPreferenceIsSet() {
		Context context = Robolectric.application.getApplicationContext();
		when(activity.getApplicationContext()).thenReturn(context);
		when(activity.getString(R.string.wake_lock)).thenReturn(WAKE_LOCK);
		SharedPreferences sharedPref = ShadowPreferenceManager.getDefaultSharedPreferences(context);
		sharedPref.edit().putBoolean(WAKE_LOCK, true).commit();

		createPresenter();

		verify(proView).wakeLock();
	}

	@Test
	public void whenPresenterIsCreated_ViewIsNeverToldToKeepScreenOnIfPreferenceIsNotSet() {
		Context context = Robolectric.application.getApplicationContext();
		when(activity.getApplicationContext()).thenReturn(context);
		when(activity.getString(R.string.wake_lock)).thenReturn(WAKE_LOCK);
		SharedPreferences sharedPref = ShadowPreferenceManager.getDefaultSharedPreferences(context);
		sharedPref.edit().putBoolean(WAKE_LOCK, false).commit();

		createPresenter();

		verify(proView, never()).wakeLock();
	}
}
