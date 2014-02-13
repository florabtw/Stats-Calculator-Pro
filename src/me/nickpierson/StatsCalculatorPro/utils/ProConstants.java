package me.nickpierson.StatsCalculatorPro.utils;

import me.nickpierson.StatsCalculator.utils.Constants;

public class ProConstants {

	public static final String STD_ERROR = "Standard Error";
	public static final String SUM_SQRS = "Sum of Squares";
	public static final String RMS = "Root Mean Square";

	public static final String[] PRO_BASIC_TITLES = { STD_ERROR, SUM_SQRS, RMS };

	public static final String COPY_NOTIFICATION = "Copied to Clipboard";
	public static final String SETTINGS_NOTIFICATION = "Please exit the settings screen to see changes.";
	public static final String RESULTS_KEY = "results order";
	public static final String SELECTED_POS_KEY = "selected pos";

	public static enum BasicInfoPaths {
		SIZE(Constants.SIZE, "size.html"), SUM(Constants.SUM, "sum.html"), MIN(Constants.MIN, "min.html"), MAX(Constants.MAX, "max.html"), ARITH_MEAN(
				Constants.ARITH_MEAN, "arith_mean.html"), GEO_MEAN(Constants.GEO_MEAN, "geo_mean.html"), MODE(Constants.MODE, "mode.html"), RANGE(
				Constants.RANGE, "range.html"), FIRST_QUART(Constants.FIRST_QUART, "first_quart.html"), MEDIAN(Constants.MEDIAN, "median.html"), THIRD_QUART(
				Constants.THIRD_QUART, "third_quart.html"), IQR(Constants.IQR, "iqr.html"), SAMPLE_VAR(Constants.SAMPLE_VAR, "sample_var.html"), POP_VAR(
				Constants.POP_VAR, "pop_var.html"), SAMPLE_DEV(Constants.SAMPLE_DEV, "sample_dev.html"), POP_DEV(Constants.POP_DEV, "pop_dev.html"), COEFF_VAR(
				Constants.COEFF_VAR, "coeff_var.html"), SKEWNESS(Constants.SKEWNESS, "skewness.html"), KURTOSIS(Constants.KURTOSIS, "kurtosis.html"), STD_ERROR(
				ProConstants.STD_ERROR, "std_error.html"), SUM_SQRS(ProConstants.SUM_SQRS, "sum_sqrs.html"), RMS(ProConstants.RMS, "rms.html");

		private String name, path;

		private BasicInfoPaths(String name, String path) {
			this.name = name;
			this.path = path;
		}

		public static String getPath(String input) {
			for (BasicInfoPaths path : BasicInfoPaths.values()) {
				if (path.name.equals(input)) {
					return path.path;
				}
			}

			return null;
		}
	}

	public static enum PCInfoPaths {
		N_FACT(Constants.N_FACT, "n_fact.html"), R_FACT(Constants.R_FACT, "r_fact.html"), N_SUBFACT(Constants.N_SUBFACT, "n_subfact.html"), R_SUBFACT(
				Constants.R_SUBFACT, "r_subfact.html"), PERM(Constants.PERM, "perm.html"), REP_PERM(Constants.REP_PERM, "rep_perm.html"), COMB(Constants.COMB,
				"comb.html"), REP_COMB(Constants.REP_COMB, "rep_comb.html"), INDISTINCT_PERM(Constants.INDISTINCT_PERM, "indistinct_perm.html"), PIGEONHOLE(
				Constants.PIGEONHOLE, "pigeonhole.html");

		private String name, path;

		private PCInfoPaths(String name, String path) {
			this.name = name;
			this.path = path;
		}

		public static String getPath(String input) {
			for (PCInfoPaths path : PCInfoPaths.values()) {
				if (path.name.equals(input)) {
					return path.path;
				}
			}

			return null;
		}
	}
}
