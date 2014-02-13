package me.nickpierson.StatsCalculatorPro.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

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

		proModel = spy(new ProBasicModel(activity));
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
		doReturn("light/").when(proModel).getThemePath();

		assertEquals("file:///android_asset/light/size.html", proModel.getEquationUrl(Constants.SIZE));
		assertEquals("file:///android_asset/light/sum.html", proModel.getEquationUrl(Constants.SUM));
		assertEquals("file:///android_asset/light/min.html", proModel.getEquationUrl(Constants.MIN));
		assertEquals("file:///android_asset/light/max.html", proModel.getEquationUrl(Constants.MAX));
		assertEquals("file:///android_asset/light/arith_mean.html", proModel.getEquationUrl(Constants.ARITH_MEAN));
		assertEquals("file:///android_asset/light/geo_mean.html", proModel.getEquationUrl(Constants.GEO_MEAN));
		assertEquals("file:///android_asset/light/mode.html", proModel.getEquationUrl(Constants.MODE));
		assertEquals("file:///android_asset/light/range.html", proModel.getEquationUrl(Constants.RANGE));
		assertEquals("file:///android_asset/light/first_quart.html", proModel.getEquationUrl(Constants.FIRST_QUART));
		assertEquals("file:///android_asset/light/median.html", proModel.getEquationUrl(Constants.MEDIAN));
		assertEquals("file:///android_asset/light/third_quart.html", proModel.getEquationUrl(Constants.THIRD_QUART));
		assertEquals("file:///android_asset/light/iqr.html", proModel.getEquationUrl(Constants.IQR));
		assertEquals("file:///android_asset/light/sample_var.html", proModel.getEquationUrl(Constants.SAMPLE_VAR));
		assertEquals("file:///android_asset/light/pop_var.html", proModel.getEquationUrl(Constants.POP_VAR));
		assertEquals("file:///android_asset/light/sample_dev.html", proModel.getEquationUrl(Constants.SAMPLE_DEV));
		assertEquals("file:///android_asset/light/pop_dev.html", proModel.getEquationUrl(Constants.POP_DEV));
		assertEquals("file:///android_asset/light/coeff_var.html", proModel.getEquationUrl(Constants.COEFF_VAR));
		assertEquals("file:///android_asset/light/skewness.html", proModel.getEquationUrl(Constants.SKEWNESS));
		assertEquals("file:///android_asset/light/kurtosis.html", proModel.getEquationUrl(Constants.KURTOSIS));
		assertEquals("file:///android_asset/light/std_error.html", proModel.getEquationUrl(ProConstants.STD_ERROR));
		assertEquals("file:///android_asset/light/sum_sqrs.html", proModel.getEquationUrl(ProConstants.SUM_SQRS));
		assertEquals("file:///android_asset/light/rms.html", proModel.getEquationUrl(ProConstants.RMS));
	}

	@Test
	public void getEquationUrlWhenDarkTheme_ReturnsCorrectUrl() {
		doReturn("dark/").when(proModel).getThemePath();

		assertEquals("file:///android_asset/dark/size.html", proModel.getEquationUrl(Constants.SIZE));
		assertEquals("file:///android_asset/dark/sum.html", proModel.getEquationUrl(Constants.SUM));
		assertEquals("file:///android_asset/dark/min.html", proModel.getEquationUrl(Constants.MIN));
		assertEquals("file:///android_asset/dark/max.html", proModel.getEquationUrl(Constants.MAX));
		assertEquals("file:///android_asset/dark/arith_mean.html", proModel.getEquationUrl(Constants.ARITH_MEAN));
		assertEquals("file:///android_asset/dark/geo_mean.html", proModel.getEquationUrl(Constants.GEO_MEAN));
		assertEquals("file:///android_asset/dark/mode.html", proModel.getEquationUrl(Constants.MODE));
		assertEquals("file:///android_asset/dark/range.html", proModel.getEquationUrl(Constants.RANGE));
		assertEquals("file:///android_asset/dark/first_quart.html", proModel.getEquationUrl(Constants.FIRST_QUART));
		assertEquals("file:///android_asset/dark/median.html", proModel.getEquationUrl(Constants.MEDIAN));
		assertEquals("file:///android_asset/dark/third_quart.html", proModel.getEquationUrl(Constants.THIRD_QUART));
		assertEquals("file:///android_asset/dark/iqr.html", proModel.getEquationUrl(Constants.IQR));
		assertEquals("file:///android_asset/dark/sample_var.html", proModel.getEquationUrl(Constants.SAMPLE_VAR));
		assertEquals("file:///android_asset/dark/pop_var.html", proModel.getEquationUrl(Constants.POP_VAR));
		assertEquals("file:///android_asset/dark/sample_dev.html", proModel.getEquationUrl(Constants.SAMPLE_DEV));
		assertEquals("file:///android_asset/dark/pop_dev.html", proModel.getEquationUrl(Constants.POP_DEV));
		assertEquals("file:///android_asset/dark/coeff_var.html", proModel.getEquationUrl(Constants.COEFF_VAR));
		assertEquals("file:///android_asset/dark/skewness.html", proModel.getEquationUrl(Constants.SKEWNESS));
		assertEquals("file:///android_asset/dark/kurtosis.html", proModel.getEquationUrl(Constants.KURTOSIS));
		assertEquals("file:///android_asset/dark/std_error.html", proModel.getEquationUrl(ProConstants.STD_ERROR));
		assertEquals("file:///android_asset/dark/sum_sqrs.html", proModel.getEquationUrl(ProConstants.SUM_SQRS));
		assertEquals("file:///android_asset/dark/rms.html", proModel.getEquationUrl(ProConstants.RMS));
	}

	private ArrayList<String> makeStringList(String... args) {
		ArrayList<String> result = new ArrayList<String>();
		for (String item : args) {
			result.add(item);
		}
		return result;
	}
}
