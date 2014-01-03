package me.nickpierson.StatsCalculatorPro;

public interface IHelperView {

	public void wakeLock();
	public int getSelectedPosition();
	public void setSelectedPosition(int pos);
	public void showController();
	public void hideController();
	public void clearChoices();

}
