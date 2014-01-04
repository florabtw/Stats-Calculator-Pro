package me.nickpierson.StatsCalculatorPro.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.nickpierson.StatsCalculator.basic.BasicModel;
import me.nickpierson.StatsCalculator.utils.Constants;
import me.nickpierson.StatsCalculatorPro.IHelperModel;
import me.nickpierson.StatsCalculatorPro.utils.ProConstants;
import android.app.Activity;

public class ProBasicModel extends BasicModel implements IHelperModel {

	private HashMap<String, String> urlMap;

	public ProBasicModel(Activity activity) {
		super(activity);

		String path = "file:///android_asset/";
		urlMap = new HashMap<String, String>();
		urlMap.put(Constants.SIZE, path + "size.html");
		urlMap.put(Constants.SUM, path + "sum.html");
		urlMap.put(Constants.ARITH_MEAN, path + "arith_mean.html");
		urlMap.put(Constants.GEO_MEAN, path + "geo_mean.html");
		urlMap.put(Constants.MEDIAN, path + "median.html");
		urlMap.put(Constants.MODE, path + "mode.html");
		urlMap.put(Constants.RANGE, path + "range.html");
		urlMap.put(Constants.SAMPLE_VAR, path + "sample_var.html");
		urlMap.put(Constants.POP_VAR, path + "pop_var.html");
		urlMap.put(Constants.SAMPLE_DEV, path + "sample_dev.html");
		urlMap.put(Constants.POP_DEV, path + "pop_dev.html");
		urlMap.put(Constants.COEFF_VAR, path + "coeff_var.html");
		urlMap.put(Constants.SKEWNESS, path + "skewness.html");
		urlMap.put(Constants.KURTOSIS, path + "kurtosis.html");
		urlMap.put(ProConstants.STD_ERROR, path + "std_error.html");
		urlMap.put(ProConstants.SUM_SQRS, path + "sum_sqrs.html");
		urlMap.put(ProConstants.RMS, path + "rms.html");
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

		double size = results.get(Constants.SIZE);
		double sampleDev = results.get(Constants.SAMPLE_DEV);
		double mean = results.get(Constants.ARITH_MEAN);

		results.put(ProConstants.STD_ERROR, sampleDev / Math.sqrt(size));
		results.put(ProConstants.SUM_SQRS, calculateSumOfSquares(numberList, mean));
		results.put(ProConstants.RMS, calculateRootMeanSquare(numberList));

		return results;
	}

	private double calculateSumOfSquares(List<Double> numberList, double mean) {
		double result = 0;
		for (double number : numberList) {
			result += Math.pow(mean - number, 2);
		}

		return result;
	}

	private double calculateRootMeanSquare(List<Double> numberList) {
		double squaresSummed = 0;
		for (double number : numberList) {
			squaresSummed += Math.pow(number, 2);
		}

		return Math.sqrt(squaresSummed / numberList.size());
	}

	@Override
	public void moveItemUp(int position, ArrayList<String> currItems) {
		moveItem(currItems, position, position - 1);
	}

	@Override
	public void moveItemDown(int position, ArrayList<String> currItems) {
		moveItem(currItems, position, position + 1);
	}

	private void moveItem(ArrayList<String> items, int pos, int desiredPos) {
		String item = items.remove(pos);
		items.add(desiredPos, item);
	}

	@Override
	public String getEquationUrl(String key) {
		return urlMap.get(key);
	}
}
