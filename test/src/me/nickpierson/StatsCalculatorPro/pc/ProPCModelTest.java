package me.nickpierson.StatsCalculatorPro.pc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import me.nickpierson.StatsCalculator.pc.PCModelTest;
import me.nickpierson.StatsCalculator.utils.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ProPCModelTest extends PCModelTest {

	ProPCModel proModel;

	@Override
	@Before
	public void setup() {
		super.setup();

		proModel = new ProPCModel();
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
	public void getEquationUrl_ReturnsCorrectUrl() {
		assertEquals("file:///android_asset/n_fact.html", proModel.getEquationUrl(Constants.N_FACT));
		assertEquals("file:///android_asset/r_fact.html", proModel.getEquationUrl(Constants.R_FACT));
		assertEquals("file:///android_asset/perm.html", proModel.getEquationUrl(Constants.PERM));
		assertEquals("file:///android_asset/rep_perm.html", proModel.getEquationUrl(Constants.REP_PERM));
		assertEquals("file:///android_asset/comb.html", proModel.getEquationUrl(Constants.COMB));
		assertEquals("file:///android_asset/rep_comb.html", proModel.getEquationUrl(Constants.REP_COMB));
		assertEquals("file:///android_asset/indistinct_perm.html", proModel.getEquationUrl(Constants.INDISTINCT_PERM));
		assertEquals("file:///android_asset/pigeonhole.html", proModel.getEquationUrl(Constants.PIGEONHOLE));
	}

	private ArrayList<String> makeStringList(String... args) {
		ArrayList<String> result = new ArrayList<String>();
		for (String item : args) {
			result.add(item);
		}
		return result;
	}
}
