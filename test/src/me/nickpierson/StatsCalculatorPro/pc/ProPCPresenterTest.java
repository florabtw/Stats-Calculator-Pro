package me.nickpierson.StatsCalculatorPro.pc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import me.nickpierson.StatsCalculator.pc.PCPresenterTest;
import me.nickpierson.StatsCalculatorPro.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowPreferenceManager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ProPCPresenterTest extends PCPresenterTest {

	Activity activity;
	ProPCView proView;

	private static final String WAKE_LOCK = "wake lock";

	@Override
	@Before
	public void setup() {
		super.setup();

		activity = mock(Activity.class);
		proView = mock(ProPCView.class);
	}

	public void createPresenter() {
		ProPCPresenter.create(activity, model, proView);
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
