package com.imgeorgedev.scrabblecheat;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

public class WordChecker_activity extends Fragment {

	Button bt;
	EditText et;

	TextView emptyError;

	Intent i;

	TextView display_WC;
	TextView slider_header;
	String value_word = "";

	Bundle extras;

	String wordValidity = "";
	String wordValidity2 = "";

	String Meaning = "";

	String points = "";

	String final_valid = "";
	String final_meaning = "";
	String final_points = "";
	Context context=this.getActivity();

	SlidingUpPanelLayout layout;
	Boolean execute = false;
	private AtomicBoolean mPanelCollapsed = new AtomicBoolean(true);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);

		final View rootView = inflater.inflate(R.layout.wordchecker_layout,
				container, false);
		setRetainInstance(true);
		

		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// setContentView(R.layout.search_layout);
		display_WC = (TextView) rootView.findViewById(R.id.displaytv5);
		bt = (Button) rootView.findViewById(R.id.button3);

		et = (EditText) rootView.findViewById(R.id.editText3);

		slider_header = (TextView) rootView.findViewById(R.id.tvP5);

		layout = (SlidingUpPanelLayout) rootView
				.findViewById(R.id.sliding_layout);

		layout.setShadowDrawable(getResources().getDrawable(
				R.drawable.above_shadow));
		
		
		emptyError = (TextView) rootView.findViewById(R.id.errorTV2);
		

		// tf.setText("Use up to two ? as wildcards");
		//

		bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Log.v("jkj678", letters);
				// if(et.equals(spc)){
				// et.getText().toString().trim();
			
				if (et.getText().toString().trim().equals("")
						|| et.getText().toString() == (null)) {
					emptyError.setText("Textbox Empty");

				} else {

					layout.expandPane();
					execute = true;
					onStart();

				}
				

				// }

				// tf.setText(letters);
			}
		});

		layout.setPanelSlideListener(new PanelSlideListener() {

			@Override
			public void onPanelSlide(View panel, float slideOffset) {
				if (slideOffset < 0.2) {
					if (getActivity().getActionBar().isShowing()) {
						getActivity().getActionBar().hide();
					}
				} else {
					if (!getActivity().getActionBar().isShowing()) {
						getActivity().getActionBar().show();
					}
				}
			}

			@Override
			public void onPanelExpanded(View panel) {

				if (execute == (true)) {
					slider_header.setText("Word Checker");
					slider_header.setMovementMethod(LinkMovementMethod
							.getInstance());
					value_word = et.getText().toString().trim();
					new progression().execute();

				} else {
					// execute = false;
				}

				execute = false;

			}

			@Override
			public void onPanelCollapsed(View panel) {
				
				
    				try{
    					setRetainInstance(getRetainInstance());
    					getActivity().getActionBar().show();
    					
    				//rootView.setMovementMethod=	LinkMovementMethod.getInstance();
    				execute = false;
    				}catch(Exception ex){
    					Log.e("Child Fragment Error-SlidePanel Layout", ex.toString());
    				}
                
				
			}
		});

		return rootView;

	}

	public class progression extends AsyncTask<Void, Void, Void> {
		ProgressDialog dialog = new ProgressDialog(
				WordChecker_activity.this.getActivity());

		@Override
		protected void onPreExecute() {
			this.dialog.setCancelable(true);
			this.dialog
					.setMessage("Checking If Word Is Valid, Please Wait.......");
			this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			this.dialog.setProgress(0);
			this.dialog.setMax(100);
			this.dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			try {
				/*
				 * This is run on a background thread, so we can sleep here or
				 * do whatever we want without blocking UI thread. A more
				 * advanced use would download chunks of fixed size and call
				 * publishProgress();
				 */

				// }
				try {

					Document doc = Jsoup
							.connect(
									"http://www.scrabblefinder.com/word/"
											+ value_word + "/")
							// .referrer("http://www.scrabblefinder.com/solver/")

							.cookie("auth", "token").timeout(69999).get();

					wordValidity = doc.select(
							"p:contains(a scrabble word? Yes!)").text();

					wordValidity2 = doc.select(
							"p:contains(still good as a Scrabble word)").text();
					// Log.v("WVDIty",wordValidity);
					Meaning = doc
							.select("div.zipcodes:contains(a scrabble word? Yes!) ul li:eq(0)")
							.text();
					// Log.v("Meani",Meaning);
					points = doc.select(
							"p.letters:contains(points in Scrabble)").text();
					// Log.v("pointe",points);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					display_WC.setText("Sorry Server is Down, try again later");
				}

			} catch (Exception e) {
				Log.e("tag", e.getMessage());

				/*
				 * The task failed
				 */

			}

			/*
			 * The task succeeded
			 */
			return null;
		}

		protected void onProgressUpdate(Void... params) {
			super.onProgressUpdate(params);

		}

		@Override
		protected void onPostExecute(final Void unused) {
			super.onPostExecute(unused);

			// word_tv.setText(word);

			if (wordValidity.contains("Yes!")
					|| wordValidity2.contains("still good as a Scrabble word")) {
				final_valid = "\n" + value_word + " is a Valid Word";
				final_meaning = "\n" + "Word Meaning: " + Meaning + "\n";
				final_points = "\n" + points + "\n";
				display_WC.setText("\n" + "\n" + final_valid + "\n"
						+ final_meaning + "\n" + final_points + "\n");
			} else {
				final_valid = value_word + " is NOT a Valid Word";
				display_WC.setText("\n" + "\n" + final_valid + "\n");
			}

			if (this.dialog.isShowing()) {

				this.dialog.dismiss();

			}
			/*
			 * Update here your view objects with content from download. It is
			 * save to dismiss dialogs, update views, etc., since we are working
			 * on UI thread.
			 */

		}
	}

}
