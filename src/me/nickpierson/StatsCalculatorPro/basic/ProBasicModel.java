package me.nickpierson.StatsCalculatorPro.basic;

import java.util.HashMap;
import java.util.List;

import me.nickpierson.StatsCalculator.basic.BasicModel;
import me.nickpierson.StatsCalculator.utils.MyConstants;
import me.nickpierson.StatsCalculatorPro.utils.ProConstants;
import android.app.Activity;

public class ProBasicModel extends BasicModel {

	public ProBasicModel(Activity activity) {
		super(activity);
	}

	@Override
	public HashMap<String, Double> getEmptyResults() {
		HashMap<String, Double> emptyResults = super.getEmptyResults();
		for (String title : ProConstants.PRO_BASIC_TITLES) {
			emptyResults.put(title, Double.NaN);
		}

		return emptyResults;
	}

	@Override
	public HashMap<String, Double> calculateResults(List<Double> numberList) {
		HashMap<String, Double> results = super.calculateResults(numberList);

		double size = results.get(MyConstants.SIZE);
		double sampleDev = results.get(MyConstants.SAMPLE_DEV);
		double mean = results.get(MyConstants.ARITH_MEAN);

		results.put(ProConstants.STD_ERROR, sampleDev / Math.sqrt(size));
		results.put(ProConstants.SUM_SQRS, calculateSumOfSquares(numberList, mean));

		return results;
	}

	private double calculateSumOfSquares(List<Double> numberList, double mean) {
		double result = 0;
		for (double number : numberList) {
			result += Math.pow(mean - number, 2);
		}

		return result;
	}
}