package me.nickpierson.StatsCalculatorPro;

import android.app.Activity;

import com.thecellutioncenter.mvplib.DataActionHandler;

public interface IProHelper {

	public <T extends IHelperView> void handleWakeLock(Activity activity, T view);

	public <T extends DataActionHandler & IHelperView> void listenForItemClick(T view, Enum<?> type);

}
