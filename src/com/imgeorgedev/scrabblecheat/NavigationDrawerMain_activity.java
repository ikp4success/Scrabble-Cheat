package com.imgeorgedev.scrabblecheat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class NavigationDrawerMain_activity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mListTitles;
	Context ctx = this;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);

		ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		State mobile = conMan.getNetworkInfo(0).getState();
		State wifi = conMan.getNetworkInfo(1).getState();
		// || mobile==NetworkInfo.State.CONNECTING) ||
		// (wifi==NetworkInfo.State.CONNECTED||
		// wifi==NetworkInfo.State.CONNECTING)
		if (mobile == NetworkInfo.State.CONNECTED|| mobile==NetworkInfo.State.CONNECTING ||
		wifi==NetworkInfo.State.CONNECTED||
		 wifi==NetworkInfo.State.CONNECTING) {
			setContentView(R.layout.navigationdrawer_layout);
			mTitle = mDrawerTitle = getTitle();
			mListTitles = getResources().getStringArray(
					R.array.listDetails_array);
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			mDrawerList = (ListView) findViewById(R.id.left_drawer);

			// set a custom shadow that overlays the main content when the
			// drawer
			// opens
			mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
					GravityCompat.START);
			// set up the drawer's list view with items and click listener
			// mDrawerList.setAdapter(new ArrayAdapter<String>(this,
			// R.layout.drawer_list_item, mListTitles));

			String[] names = new String[] { "Search", "Word Check", "J-Words",
					"Q-Words", "X-Words", "Z-Words" };

			/* Array of Images */
			int[] imageid = new int[] { R.drawable.searchicon,
					R.drawable.valid, R.drawable.jicon, R.drawable.qicon,
					R.drawable.xicon, R.drawable.zicon };

			List<HashMap<String, String>> listinfo = new ArrayList<HashMap<String, String>>();
			listinfo.clear();
			for (int i = 0; i < 6; i++) {
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("names", names[i]);
				hm.put("imageid", Integer.toString(imageid[i]));
				listinfo.add(hm);
			}

			// Keys used in Hashmap
			String[] from = { "imageid", "names" };
			int[] to = { R.id.image, R.id.text1NV };
			SimpleAdapter adapter = new SimpleAdapter(getBaseContext(),
					listinfo, R.layout.drawer_list_item, from, to);
			mDrawerList.setAdapter(adapter);

			mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

			// enable ActionBar app icon to behave as action to toggle nav
			// drawer
			getActionBar().setDisplayHomeAsUpEnabled(true);

			getActionBar().setHomeButtonEnabled(true);

			// // ActionBarDrawerToggle ties together the the proper
			// interactions
			// between the sliding drawer and the action bar app icon
			mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
			mDrawerLayout, /* DrawerLayout object */
			R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
			R.string.drawer_open, /* "open drawer" description for accessibility */
			R.string.drawer_close /* "close drawer" description for accessibility */
			) {
				public void onDrawerClosed(View view) {
					getActionBar().setTitle(mTitle);
					invalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
				}

				public void onDrawerOpened(View drawerView) {
					getActionBar().setTitle(mDrawerTitle);
					invalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
				}
			};
			mDrawerLayout.setDrawerListener(mDrawerToggle);

			if (savedInstanceState == null) {
				try {
					getItem(0);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {

			AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
			builder.setMessage("Enable Wifi/3G/4G in settings to continue.").setPositiveButton(
					"Exit App", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
							Intent intent = new Intent(Intent.ACTION_MAIN);
							intent.addCategory(Intent.CATEGORY_HOME);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(intent);

						}
					});

			AlertDialog alertDialog = builder.create();
			alertDialog.setCancelable(false);
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activitymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// /* Called whenever we call invalidateOptionsMenu() */
	// @Override
	// public boolean onPrepareOptionsMenu(Menu menu) {
	// // If the nav drawer is open, hide action items related to the content
	// // view
	// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	// menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
	// return super.onPrepareOptionsMenu(menu);
	// }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (item != null)
			if (mDrawerToggle.onOptionsItemSelected(item)) {
				return true;
			}
		if (item != null)
			// Handle action buttons
			if (item.getItemId() == R.id.AD) {
				showPopup2();
			}

		if (item.getItemId() == R.id.Exit23) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);

			// finish();
			return true;

		}

		else {
			return super.onOptionsItemSelected(item);
		}

	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			try {
				getItem(position);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void getItem(int position) throws InstantiationException,
			IllegalAccessException {
		// /List<Fragment> fragments = new Vector<Fragment>();
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		setTitle(mListTitles[position]);

		switch (position) {
		case 0:

			fragmentTransaction.replace(R.id.content_frame,
					ViewPager_activity.class.newInstance());
			fragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fragmentTransaction.commit();

			// mDrawerList.setItemChecked(0, true);

			mDrawerLayout.closeDrawer(mDrawerList);

			// Intent a = new Intent(this, Search_activity.class);
			// startActivity(a);
			

			break;
		case 1:
			// fragments.add(Fragment.instantiate(this,
			// Zwords_activity.class.getName()));
			fragmentTransaction.replace(R.id.content_frame,
					WordChecker_activity.class.newInstance());
			fragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fragmentTransaction.commit();
			// mDrawerList.setItemChecked(3, true);

			mDrawerLayout.closeDrawer(mDrawerList);

			// Intent b = new Intent(this, Zwords_activity.class);
			// startActivity(b);
			break;
		case 2:
			// fragments.add(Fragment.instantiate(this,
			// Zwords_activity.class.getName()));
			fragmentTransaction.replace(R.id.content_frame,
					JWords_activity.class.newInstance());
			fragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fragmentTransaction.commit();
			// mDrawerList.setItemChecked(1, true);

			mDrawerLayout.closeDrawer(mDrawerList);

			// Intent b = new Intent(this, Zwords_activity.class);
			// startActivity(b);
			break;

		case 3:
			// fragments.add(Fragment.instantiate(this,
			// Zwords_activity.class.getName()));
			fragmentTransaction.replace(R.id.content_frame,
					Qwords_activity.class.newInstance());
			fragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fragmentTransaction.commit();
			// mDrawerList.setItemChecked(2, true);

			mDrawerLayout.closeDrawer(mDrawerList);

			// Intent b = new Intent(this, Zwords_activity.class);
			// startActivity(b);
			break;

		case 4:
			// fragments.add(Fragment.instantiate(this,
			// Zwords_activity.class.getName()));
			fragmentTransaction.replace(R.id.content_frame,
					Xwords_activity.class.newInstance());
			fragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fragmentTransaction.commit();
			// mDrawerList.setItemChecked(3, true);

			mDrawerLayout.closeDrawer(mDrawerList);

			// Intent b = new Intent(this, Zwords_activity.class);
			// startActivity(b);
			break;
		case 5:
			// fragments.add(Fragment.instantiate(this,
			// Zwords_activity.class.getName()));
			fragmentTransaction.replace(R.id.content_frame,
					Zwords_activity.class.newInstance());
			fragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fragmentTransaction.commit();
			// mDrawerList.setItemChecked(3, true);

			mDrawerLayout.closeDrawer(mDrawerList);

			// Intent b = new Intent(this, Zwords_activity.class);
			// startActivity(b);
			break;

		default:
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		if (mDrawerToggle != null)
			mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		if (mDrawerToggle != null)
			mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * Fragment that appears in the "content_frame", shows a planet
	 */
	// public static class PlanetFragment extends Fragment {
	// public static final String ARG_PLANET_NUMBER = "planet_number";
	//
	// public PlanetFragment() {
	// // Empty constructor required for fragment subclasses
	// }
	//
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup
	// container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.viewpager_layout, container,
	// false);
	//
	// return rootView;
	// }
	// }

	private void showPopup2() {
		final PopupWindow window = new PopupWindow(this);

		window.setWidth(430);
		window.setHeight(600);
		// window.setBackgroundDrawable(getResources().getDrawable(R.color.));

		window.setTouchable(true);
		window.setFocusable(true);

		TextView text = new TextView(this);
		text.setTextColor(getResources().getColor(R.color.Cream));

		text.setText("\n" + "\n" + "\n"
				+ "			                      Instructions Info" + "\n" + "\n"
				+ "\n" + "	            ** Use up to ?? as wild letters " + "\n"
				+ "	" + "\n" + "\n"
				+ "	            **Maximum of 12 letters allowed" + "\n" + "\n"
				+ "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n"
				+ "\n" + "\n"
				+ "	                             App Developed by" + "\n"
				+ "\n" + "	                IMGeorgeDEV:@www.imgeorge.co.nr");

		text.setTextSize(14);

		// text.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View v) {
		// startActivity(new Intent("com.android.shorttext.AddSlang"));
		// }
		//
		// });// button

		window.setContentView(text);
		// window.setContentView(bt);
		window.showAtLocation(text, Gravity.CENTER, 0, 0);

	}

	public static class iconFragment extends Fragment {
		public static final String ARG_PLANET_NUMBER = "planet_number";

		public iconFragment() {
			// Empty constructor required for fragment subclasses
		}

	}

}
