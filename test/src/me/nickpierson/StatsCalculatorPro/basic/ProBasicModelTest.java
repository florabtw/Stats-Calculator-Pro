package me.nickpierson.StatsCalculatorPro.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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

	private ArrayList<String> makeStringList(String... args) {
		ArrayList<String> result = new ArrayList<String>();
		for (String item : args) {
			result.add(item);
		}
		return result;
	}
}
