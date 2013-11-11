package me.nickpierson.StatsCalculatorPro.basic;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import me.nickpierson.StatsCalculator.basic.BasicModelTest;
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
}
