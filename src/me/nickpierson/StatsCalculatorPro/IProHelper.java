package me.nickpierson.StatsCalculatorPro;

import android.app.Activity;

public interface IProHelper {

	public <T extends IHelperView> void handleWakeLock(Activity activity, T view);

}
