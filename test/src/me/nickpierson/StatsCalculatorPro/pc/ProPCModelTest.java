package me.nickpierson.StatsCalculatorPro.pc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import me.nickpierson.StatsCalculator.pc.PCModelTest;

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

	private ArrayList<String> makeStringList(String... args) {
		ArrayList<String> result = new ArrayList<String>();
		for (String item : args) {
			result.add(item);
		}
		return result;
	}
}
