package com.imgeorgedev.scrabblecheat;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


public class ExamplePagerAdapter extends FragmentStatePagerAdapter  {
	private List<Fragment> fragments;
	
	public ExamplePagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments2) {
		super(fragmentManager);
		this.fragments = fragments2;

		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.fragments.size();
	}

//	@Override
//	public void destroyItem(View arg0, int arg1, Object arg2) {
//		((ViewPager) arg0).removeView((View) arg2);
//		((ViewPager) arg0).addView((View) arg2);
//
//
//	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	

	
}
