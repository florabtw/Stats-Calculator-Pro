package me.nickpierson.StatsCalculatorPro;

import java.util.ArrayList;

public interface IHelperModel {

	public void moveItemUp(int position, ArrayList<String> currItems);

	public void moveItemDown(int position, ArrayList<String> currItems);

	public String getEquationUrl(String key);
}
