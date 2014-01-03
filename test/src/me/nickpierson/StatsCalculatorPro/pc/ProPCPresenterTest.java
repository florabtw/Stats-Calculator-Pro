package me.nickpierson.StatsCalculatorPro.pc;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

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
	private SharedPreferences sharedPref;

	private static final String WAKE_LOCK = "wake lock";

	@Override
	@Before
	public void setup() {
		super.setup();

		activity = mock(Activity.class);
		proView = mock(ProPCView.class);

		Context context = Robolectric.application.getApplicationContext();
		when(activity.getApplicationContext()).thenReturn(context);
		when(activity.getString(R.string.wake_lock)).thenReturn(WAKE_LOCK);

		sharedPref = ShadowPreferenceManager.getDefaultSharedPreferences(context);
	}

	public void createPresenter() {
		ProPCPresenter.create(activity, model, proView);
	}

	@Test
	public void whenPresenterIsCreated_ViewIsToldToKeepScreenOnIfPreferenceIsSet() {
		sharedPref.edit().putBoolean(WAKE_LOCK, true).commit();

		createPresenter();

		verify(proView).wakeLock();
	}

	@Test
	public void whenPresenterIsCreated_ViewIsNeverToldToKeepScreenOnIfPreferenceIsNotSet() {
		sharedPref.edit().putBoolean(WAKE_LOCK, false).commit();

		createPresenter();

		verify(proView, never()).wakeLock();
	}

	@Test
	public void whenAListItemIsSelectedAndControllerIsNotShown_ThenTheControllerShouldAppear() {
		when(proView.getSelectedPosition()).thenReturn(-1);
		HashMap<Enum<?>, Integer> testMap = new HashMap<Enum<?>, Integer>();
		testMap.put(ProPCView.ProTypes.ITEM_CLICK, 3);
		createPresenter();

		verify(proView).addListener(dataListener.capture(), eq(ProPCView.ProTypes.ITEM_CLICK));

		dataListener.getValue().fire(testMap);

		verify(proView).showController();
	}

	@Test
	public void whenAListItemIsSelectedAndControllerIsNotShown_ThenAdapterShouldSaveSelectedPosition() {
		when(proView.getSelectedPosition()).thenReturn(-1);
		HashMap<Enum<?>, Integer> testMap = new HashMap<Enum<?>, Integer>();
		testMap.put(ProPCView.ProTypes.ITEM_CLICK, 3);
		createPresenter();

		verify(proView).addListener(dataListener.capture(), eq(ProPCView.ProTypes.ITEM_CLICK));

		dataListener.getValue().fire(testMap);

		verify(proView).setSelectedPosition(3);
	}

	@Test
	public void whenANewListItemIsSelectedAndTheControllerIsAlreadyShown_ThenAdapterShouldSaveNewPosition() {
		when(proView.getSelectedPosition()).thenReturn(2);
		HashMap<Enum<?>, Integer> testMap = new HashMap<Enum<?>, Integer>();
		testMap.put(ProPCView.ProTypes.ITEM_CLICK, 3);
		createPresenter();

		verify(proView).addListener(dataListener.capture(), eq(ProPCView.ProTypes.ITEM_CLICK));

		dataListener.getValue().fire(testMap);

		verify(proView).setSelectedPosition(3);
		verify(proView, never()).showController();
	}

	@Test
	public void whenAListItemIsClickedAndAlreadySelected_ThenTheControllerShouldHideAndAdapterClearSavedPosition() {
		when(proView.getSelectedPosition()).thenReturn(3);
		HashMap<Enum<?>, Integer> testMap = new HashMap<Enum<?>, Integer>();
		testMap.put(ProPCView.ProTypes.ITEM_CLICK, 3);
		createPresenter();

		verify(proView).addListener(dataListener.capture(), eq(ProPCView.ProTypes.ITEM_CLICK));

		dataListener.getValue().fire(testMap);

		verify(proView).clearChoices();
		verify(proView).setSelectedPosition(-1);
		verify(proView).hideController();
	}
}
