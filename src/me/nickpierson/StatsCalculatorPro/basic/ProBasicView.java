package me.nickpierson.StatsCalculatorPro.basic;

import java.util.ArrayList;
import java.util.HashMap;

import me.nickpierson.StatsCalculator.basic.BasicView;
import me.nickpierson.StatsCalculator.utils.Constants;
import me.nickpierson.StatsCalculatorPro.IHelperView;
import me.nickpierson.StatsCalculatorPro.R;
import me.nickpierson.StatsCalculatorPro.utils.ProConstants;
import me.nickpierson.StatsCalculatorPro.utils.ProDefaultAdapter;
import me.nickpierson.StatsCalculatorPro.utils.ProKeypadHelper;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ProBasicView extends BasicView implements IHelperView {

	public enum ProTypes {
		ITEM_CLICK, LONG_ITEM_CLICK, MOVE_UP, MOVE_DOWN, REMOVE, MENU_RESET_LIST, INFO;
	}

	ProKeypadHelper proKeypadHelper;
	private RelativeLayout proResults;
	private LinearLayout controller;

	public ProBasicView(Activity activity, ArrayList<String> results) {
		super(activity, new ProDefaultAdapter(activity, R.layout.basic_result_item, R.id.basic_tvResultTitle, R.id.basic_tvResultAnswer));

		proResults = (RelativeLayout) LayoutInflater.from(activity).inflate(R.layout.pro_results_list, null);
		lvResults = (ListView) proResults.findViewById(R.id.pro_lv_results);
		controller = (LinearLayout) proResults.findViewById(R.id.pro_results_controller);
		ImageButton btnMoveUp = (ImageButton) controller.findViewById(R.id.pro_results_btnMoveUp);
		ImageButton btnMoveDown = (ImageButton) controller.findViewById(R.id.pro_results_btnMoveDown);
		ImageButton btnRemove = (ImageButton) controller.findViewById(R.id.pro_results_btnRemove);
		ImageButton btnInfo = (ImageButton) controller.findViewById(R.id.pro_results_btnInfo);

		lvResults.setAdapter(resultsAdapter);
		lvResults.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		resultsAdapter.addMultiple(results);
		flFrame.addView(proResults);

		proKeypadHelper = new ProKeypadHelper(activity);
		ImageButton btnBackspace = (ImageButton) tlKeypad.findViewById(R.id.keypad_backspace);

		proKeypadHelper.disableSoftInputFromAppearing(etInput);
		proKeypadHelper.watchEditText(etInput);

		btnBackspace.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				proKeypadHelper.longPressBackspace(etInput);
				return true;
			}
		});

		lvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
				HashMap<Enum<?>, Integer> map = new HashMap<Enum<?>, Integer>();
				map.put(ProTypes.ITEM_CLICK, pos);
				dataEvent(ProTypes.ITEM_CLICK, map);
			}
		});

		lvResults.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos, long id) {
				HashMap<Enum<?>, Integer> map = new HashMap<Enum<?>, Integer>();
				map.put(ProTypes.LONG_ITEM_CLICK, pos);
				dataEvent(ProTypes.LONG_ITEM_CLICK, map);
				return true;
			}
		});

		eventOnClick(btnMoveUp, ProTypes.MOVE_UP);
		eventOnClick(btnMoveDown, ProTypes.MOVE_DOWN);
		eventOnClick(btnRemove, ProTypes.REMOVE);
		eventOnClick(btnInfo, ProTypes.INFO);
	}

	private void eventOnClick(ImageButton button, final Enum<ProTypes> type) {
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				event(type);
			}
		});
	}

	@Override
	public void showResults() {
		flFrame.removeAllViews();
		flFrame.addView(proResults);
		resultsAdapter.notifyDataSetChanged();
	}

	@Override
	public void showController() {
		controller.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideController() {
		controller.setVisibility(View.GONE);
	}

	@Override
	public int getSelectedPosition() {
		return ((ProDefaultAdapter) resultsAdapter).getSelectedPosition();
	}

	@Override
	public void setSelectedPosition(int pos) {
		((ProDefaultAdapter) resultsAdapter).setSelectedPosition(pos);
	}

	public String getSelectedItem() {
		return resultsAdapter.getItem(getSelectedPosition());
	}

	@Override
	public void highlightAndSelect(int pos) {
		setSelectedPosition(pos);
		lvResults.setItemChecked(pos, true);
	}

	@Override
	public ArrayList<String> getAllItems() {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < resultsAdapter.getCount(); i++) {
			result.add(resultsAdapter.getItem(i));
		}

		return result;
	}

	@Override
	public void replaceItems(ArrayList<String> items) {
		resultsAdapter.clear();
		resultsAdapter.addMultiple(items);
	}

	@Override
	public void clearChoices() {
		lvResults.clearChoices();
	}

	@Override
	public void resetList() {
		resultsAdapter.clear();
		resultsAdapter.addMultiple(Constants.BASIC_TITLES);
		resultsAdapter.addMultiple(ProConstants.PRO_BASIC_TITLES);
	}

	@SuppressLint("SetJavaScriptEnabled")
	public void displayItemInfo(String url) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
		WebView view = (WebView) LayoutInflater.from(activity).inflate(R.layout.pro_basic_webview, null);
		view.loadUrl(url);
		view.getSettings().setJavaScriptEnabled(true);
		dialog.setView(view);
		dialog.show();
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public void copyItemToClipboard(int itemPos) {
		String selectedItem = resultsAdapter.getItem(itemPos);
		String selectedResult = resultsAdapter.getResults().get(selectedItem);

		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
			clipboard.setText(selectedResult);
		} else {
			android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText(selectedItem, selectedResult);
			clipboard.setPrimaryClip(clip);
		}
	}

	@Override
	public void wakeLock() {
		view.setKeepScreenOn(true);
	}

	public void menuListReset() {
		event(ProTypes.MENU_RESET_LIST);
	}

	public void keypadPress(Button button) {
		/* Skips MVP */
		proKeypadHelper.keypadPress(etInput, button.getText().charAt(0));
	}

	public void backspace() {
		/* Skips MVP */
		proKeypadHelper.backspace(etInput);
	}
}