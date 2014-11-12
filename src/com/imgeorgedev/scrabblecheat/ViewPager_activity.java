package com.imgeorgedev.scrabblecheat;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.SwipeyTabsView;
import com.viewpagerindicator.TabsAdapter;

public class ViewPager_activity extends Fragment {
	
	private PagerAdapter mPagerAdapter;
	private TabsAdapter mSwipeyTabsAdapter;
	private SwipeyTabsView mSwipeyTabs;

	ViewPager pager;
	View rootView;
	LinePageIndicator cIndicator;
	List<Fragment> fragments = new Vector<Fragment>();

	/** Called when the activity is first created. */
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.viewpager_layout, container, false);
        setRetainInstance(true);
        
       
        this.initialisePaging();
        return rootView;
	    // TODO Auto-generated method stub
	}
	
	private void initialisePaging() {

		initViewPager();
		
		mSwipeyTabs = (SwipeyTabsView) rootView.findViewById(R.id.swipey_tabs);
		mSwipeyTabsAdapter = new SwipeyTabsAdapter(this.getActivity());

		mSwipeyTabs.setAdapter(mSwipeyTabsAdapter);
		mSwipeyTabs.setViewPager(pager);

		
		// actionBar.setHomeLogo(R.drawable.ic_launcher);
		// actionBar.setTitle("              SMS Slang");
		//
		// final Action menuAction = new IntentAction(this, createMenuIntent(),
		// R.drawable.menulist34);
		// actionBar.addAction(menuAction);

	}
	
	private void initViewPager() {
		
		
		pager = (ViewPager) rootView.findViewById(R.id.viewpager);
		fragments.add(Fragment.instantiate(this.getActivity(), Search_activity.class.getName()));
//		fragments.add(Fragment.instantiate(this, Main2.class.getName()));
		fragments.add(Fragment.instantiate(this.getActivity(), Word4Friends_Activity.class.getName()));		
		this.mPagerAdapter = new ExamplePagerAdapter(
				super.getActivity().getSupportFragmentManager(), fragments);
		pager.setAdapter(mPagerAdapter);
		 pager.setCurrentItem(0);
		 //pager.setPageMargin(1);
//		 pager.setPageMarginDrawable(getResources().getColor(R.color.yellow));
	}

}
