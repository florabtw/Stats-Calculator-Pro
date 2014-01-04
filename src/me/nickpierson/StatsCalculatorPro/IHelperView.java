package me.nickpierson.StatsCalculatorPro;

import java.util.ArrayList;

public interface IHelperView {

	public void wakeLock();

	public int getSelectedPosition();

	public void setSelectedPosition(int pos);

	public void showController();

	public void hideController();

	public void clearChoices();

	public ArrayList<String> getAllItems();

	public void replaceItems(ArrayList<String> items);

	public void highlightAndSelect(int pos);

	public void resetList();

	public String getSelectedItem();

	public void displayItemInfo(String url);
}
