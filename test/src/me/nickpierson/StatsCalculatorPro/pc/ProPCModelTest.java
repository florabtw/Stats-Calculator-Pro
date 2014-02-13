package me.nickpierson.StatsCalculatorPro.pc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;

import me.nickpierson.StatsCalculator.pc.PCModelTest;
import me.nickpierson.StatsCalculator.utils.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ProPCModelTest extends PCModelTest {

	ProPCModel proModel;
	Activity activity;

	@Override
	@Before
	public void setup() {
		super.setup();

		activity = mock(Activity.class);
		proModel = spy(new ProPCModel(activity));
	}

	@Test
	public void moveItemUp_ReturnsDesiredItemMovedUpByOne() {
		int testPos = 1;
		ArrayList<String> testItems = makeStringList("First", "Second", "Third");
		ArrayList<String> expectedOutput = makeStringList("Second", "First", "Third");

		proModel.moveItemUp(testPos, testItems);

		assertEquals(expectedOutput, testItems);
	}

	@Test
	public void moveItemDown_ReturnsDesiredItemMovedDownByOne() {
		int testPos = 1;
		ArrayList<String> testItems = makeStringList("First", "Second", "Third");
		ArrayList<String> expectedOutput = makeStringList("First", "Third", "Second");

		proModel.moveItemDown(testPos, testItems);

		assertEquals(expectedOutput, testItems);
	}

	@Test
	public void getEquationUrlWhenLight_ReturnsCorrectUrl() {
		doReturn("light/").when(proModel).getThemePath();

		assertEquals("file:///android_asset/light/n_fact.html", proModel.getEquationUrl(Constants.N_FACT));
		assertEquals("file:///android_asset/light/r_fact.html", proModel.getEquationUrl(Constants.R_FACT));
		assertEquals("file:///android_asset/light/n_subfact.html", proModel.getEquationUrl(Constants.N_SUBFACT));
		assertEquals("file:///android_asset/light/r_subfact.html", proModel.getEquationUrl(Constants.R_SUBFACT));
		assertEquals("file:///android_asset/light/perm.html", proModel.getEquationUrl(Constants.PERM));
		assertEquals("file:///android_asset/light/rep_perm.html", proModel.getEquationUrl(Constants.REP_PERM));
		assertEquals("file:///android_asset/light/comb.html", proModel.getEquationUrl(Constants.COMB));
		assertEquals("file:///android_asset/light/rep_comb.html", proModel.getEquationUrl(Constants.REP_COMB));
		assertEquals("file:///android_asset/light/indistinct_perm.html", proModel.getEquationUrl(Constants.INDISTINCT_PERM));
		assertEquals("file:///android_asset/light/pigeonhole.html", proModel.getEquationUrl(Constants.PIGEONHOLE));
	}

	@Test
	public void getEquationUrlWhenDark_ReturnsCorrectUrl() {
		doReturn("dark/").when(proModel).getThemePath();

		assertEquals("file:///android_asset/dark/n_fact.html", proModel.getEquationUrl(Constants.N_FACT));
		assertEquals("file:///android_asset/dark/r_fact.html", proModel.getEquationUrl(Constants.R_FACT));
		assertEquals("file:///android_asset/dark/n_subfact.html", proModel.getEquationUrl(Constants.N_SUBFACT));
		assertEquals("file:///android_asset/dark/r_subfact.html", proModel.getEquationUrl(Constants.R_SUBFACT));
		assertEquals("file:///android_asset/dark/perm.html", proModel.getEquationUrl(Constants.PERM));
		assertEquals("file:///android_asset/dark/rep_perm.html", proModel.getEquationUrl(Constants.REP_PERM));
		assertEquals("file:///android_asset/dark/comb.html", proModel.getEquationUrl(Constants.COMB));
		assertEquals("file:///android_asset/dark/rep_comb.html", proModel.getEquationUrl(Constants.REP_COMB));
		assertEquals("file:///android_asset/dark/indistinct_perm.html", proModel.getEquationUrl(Constants.INDISTINCT_PERM));
		assertEquals("file:///android_asset/dark/pigeonhole.html", proModel.getEquationUrl(Constants.PIGEONHOLE));
	}

	private ArrayList<String> makeStringList(String... args) {
		ArrayList<String> result = new ArrayList<String>();
		for (String item : args) {
			result.add(item);
		}
		return result;
	}
}
