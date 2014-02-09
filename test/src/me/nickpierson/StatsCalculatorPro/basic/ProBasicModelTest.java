package me.nickpierson.StatsCalculatorPro.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import me.nickpierson.StatsCalculator.basic.BasicModelTest;
import me.nickpierson.StatsCalculator.utils.Constants;
import me.nickpierson.StatsCalculatorPro.utils.ProConstants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ProBasicModelTest extends BasicModelTest {

	ProBasicModel proModel;

	@Override
	@Before
	public void setup() {
		super.setup();

		proModel = new ProBasicModel(activity);
	}

	@Test
	public void additionalResultsAreAddedToEmptyResultsWhenRequested() {
		HashMap<String, Double> testResults = proModel.getEmptyResults();

		assertTrue(Double.isNaN(testResults.get(ProConstants.STD_ERROR)));
		assertTrue(Double.isNaN(testResults.get(ProConstants.SUM_SQRS)));
		assertTrue(Double.isNaN(testResults.get(ProConstants.RMS)));
	}

	@Test
	public void additionalCalculationsAreCorrect() {
		HashMap<String, Double> testResults = proModel.calculateResults(makeValidList(36.9, 32.228, 39.01, 37.65));

		assertEquals(1.472564, testResults.get(ProConstants.STD_ERROR), DELTA);
		assertEquals(26.021347, testResults.get(ProConstants.SUM_SQRS), DELTA);
		assertEquals(36.536134, testResults.get(ProConstants.RMS), DELTA);
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
		assertEquals("file:///android_asset/size.html", proModel.getEquationUrl(Constants.SIZE));
		assertEquals("file:///android_asset/sum.html", proModel.getEquationUrl(Constants.SUM));
		assertEquals("file:///android_asset/min.html", proModel.getEquationUrl(Constants.MIN));
		assertEquals("file:///android_asset/max.html", proModel.getEquationUrl(Constants.MAX));
		assertEquals("file:///android_asset/arith_mean.html", proModel.getEquationUrl(Constants.ARITH_MEAN));
		assertEquals("file:///android_asset/geo_mean.html", proModel.getEquationUrl(Constants.GEO_MEAN));
		assertEquals("file:///android_asset/mode.html", proModel.getEquationUrl(Constants.MODE));
		assertEquals("file:///android_asset/range.html", proModel.getEquationUrl(Constants.RANGE));
		assertEquals("file:///android_asset/first_quart.html", proModel.getEquationUrl(Constants.FIRST_QUART));
		assertEquals("file:///android_asset/median.html", proModel.getEquationUrl(Constants.MEDIAN));
		assertEquals("file:///android_asset/third_quart.html", proModel.getEquationUrl(Constants.THIRD_QUART));
		assertEquals("file:///android_asset/iqr.html", proModel.getEquationUrl(Constants.IQR));
		assertEquals("file:///android_asset/sample_var.html", proModel.getEquationUrl(Constants.SAMPLE_VAR));
		assertEquals("file:///android_asset/pop_var.html", proModel.getEquationUrl(Constants.POP_VAR));
		assertEquals("file:///android_asset/sample_dev.html", proModel.getEquationUrl(Constants.SAMPLE_DEV));
		assertEquals("file:///android_asset/pop_dev.html", proModel.getEquationUrl(Constants.POP_DEV));
		assertEquals("file:///android_asset/coeff_var.html", proModel.getEquationUrl(Constants.COEFF_VAR));
		assertEquals("file:///android_asset/skewness.html", proModel.getEquationUrl(Constants.SKEWNESS));
		assertEquals("file:///android_asset/kurtosis.html", proModel.getEquationUrl(Constants.KURTOSIS));
		assertEquals("file:///android_asset/std_error.html", proModel.getEquationUrl(ProConstants.STD_ERROR));
		assertEquals("file:///android_asset/sum_sqrs.html", proModel.getEquationUrl(ProConstants.SUM_SQRS));
		assertEquals("file:///android_asset/rms.html", proModel.getEquationUrl(ProConstants.RMS));
	}

	private ArrayList<String> makeStringList(String... args) {
		ArrayList<String> result = new ArrayList<String>();
		for (String item : args) {
			result.add(item);
		}
		return result;
	}
}
