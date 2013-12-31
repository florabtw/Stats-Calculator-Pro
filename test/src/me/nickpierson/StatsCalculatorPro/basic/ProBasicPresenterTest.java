package me.nickpierson.StatsCalculatorPro.basic;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

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
	ProBasicModel proModel;

	private static final String WAKE_LOCK = "wake lock";

	private SharedPreferences sharedPref;

	@Override
	@Before
	public void setup() {
		super.setup();

		proView = mock(ProBasicView.class);
		proModel = mock(ProBasicModel.class);

		Context context = Robolectric.application.getApplicationContext();
		when(activity.getApplicationContext()).thenReturn(context);
		when(activity.getString(R.string.wake_lock)).thenReturn(WAKE_LOCK);

		sharedPref = ShadowPreferenceManager.getDefaultSharedPreferences(context);
	}

	public void createPresenter() {
		ProBasicPresenter.create(activity, proModel, proView);
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
		testMap.put(ProBasicView.ProTypes.ITEM_CLICK, 3);
		createPresenter();

		verify(proView).addListener(dataListener.capture(), eq(ProBasicView.ProTypes.ITEM_CLICK));

		dataListener.getValue().fire(testMap);

		verify(proView).showController();
	}

	@Test
	public void whenAListItemIsSelectedAndControllerIsNotShown_ThenAdapterShouldSaveSelectedPosition() {
		when(proView.getSelectedPosition()).thenReturn(-1);
		HashMap<Enum<?>, Integer> testMap = new HashMap<Enum<?>, Integer>();
		testMap.put(ProBasicView.ProTypes.ITEM_CLICK, 3);
		createPresenter();

		verify(proView).addListener(dataListener.capture(), eq(ProBasicView.ProTypes.ITEM_CLICK));

		dataListener.getValue().fire(testMap);

		verify(proView).setSelectedPosition(3);
	}

	@Test
	public void whenANewListItemIsSelectedAndTheControllerIsAlreadyShown_ThenAdapterShouldSaveNewPosition() {
		when(proView.getSelectedPosition()).thenReturn(2);
		HashMap<Enum<?>, Integer> testMap = new HashMap<Enum<?>, Integer>();
		testMap.put(ProBasicView.ProTypes.ITEM_CLICK, 3);
		createPresenter();

		verify(proView).addListener(dataListener.capture(), eq(ProBasicView.ProTypes.ITEM_CLICK));

		dataListener.getValue().fire(testMap);

		verify(proView).setSelectedPosition(3);
		verify(proView, never()).showController();
	}

	@Test
	public void whenAListItemIsClickedAndAlreadySelected_ThenTheControllerShouldHideAndAdapterClearSavedPosition() {
		when(proView.getSelectedPosition()).thenReturn(3);
		HashMap<Enum<?>, Integer> testMap = new HashMap<Enum<?>, Integer>();
		testMap.put(ProBasicView.ProTypes.ITEM_CLICK, 3);
		createPresenter();

		verify(proView).addListener(dataListener.capture(), eq(ProBasicView.ProTypes.ITEM_CLICK));

		dataListener.getValue().fire(testMap);

		verify(proView).clearChoices();
		verify(proView).setSelectedPosition(-1);
		verify(proView).hideController();
	}

	@Test
	public void whenMoveUpButtonIsClicked_ThenCurrentSelectedItemShouldMoveUp() {
		int testPos = 1;
		String[] testItems = { "First", "Second", "Third" };
		String[] expectedItems = { "Second", "First", "Third" };
		when(proView.getSelectedPosition()).thenReturn(testPos);
		when(proView.getAllItems()).thenReturn(testItems);
		when(proModel.moveItemUp(testPos, testItems)).thenReturn(expectedItems);
		createPresenter();

		verify(proView).addListener(listener.capture(), eq(ProBasicView.ProTypes.MOVE_UP));

		listener.getValue().fire();

		verify(proModel).moveItemUp(testPos, testItems);
		verify(proView).replaceItems(expectedItems);
	}
	
	@Test
	public void whenMoveUpButtonIsClickedWhenTopItemIsSelected_ThenNothingShouldHappen() {
		when(proView.getSelectedPosition()).thenReturn(0);
		createPresenter();
		
		verify(proView).addListener(listener.capture(), eq(ProBasicView.ProTypes.MOVE_UP));
		
		listener.getValue().fire();
		
		verify(proModel, never()).moveItemUp(any(Integer.class), any(String[].class));
		verify(proView, never()).replaceItems(any(String[].class));
	}

//	@Test
//	public void whenMovedDownButtonIsClicked_ThenViewShouldBeToldToMoveSelectedItemUp() {
//		createPresenter();
//
//		verify(proView).addListener(listener.capture(), eq(ProBasicView.ProTypes.MOVE_DOWN));
//
//		listener.getValue().fire();
//
//		verify(proView).moveSelectedItemDown();
//	}
}
