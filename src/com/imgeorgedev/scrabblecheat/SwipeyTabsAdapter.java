package com.imgeorgedev.scrabblecheat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.viewpagerindicator.SwipeyTabButton;
import com.viewpagerindicator.TabsAdapter;

public class SwipeyTabsAdapter implements TabsAdapter {

	private Activity mContext;

	private String[] mTitles = { "GENERAL SCRABBLE WORD CHEAT", "WORDS WITH FRIENDS CHEAT" };

	public SwipeyTabsAdapter(Activity ctx) {

		this.mContext = ctx;
	}

	@Override
	public View getView(int position) {
		SwipeyTabButton tab;

		LayoutInflater inflater = mContext.getLayoutInflater();
		tab = (SwipeyTabButton) inflater.inflate(R.layout.tab_swipey, null);

		if (position < mTitles.length)
			tab.setText(mTitles[position]);

		return tab;
	}

}
