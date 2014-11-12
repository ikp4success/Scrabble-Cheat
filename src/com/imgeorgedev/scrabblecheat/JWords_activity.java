package com.imgeorgedev.scrabblecheat;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JWords_activity extends Fragment {
	
	
	
	TextView display_J;
	// TextView word_tv;
	String value_letter;
	String value_start = "";
	String value_end= "";
	Bundle extras;
	private ProgressDialog progressDialog;
	String word;
	String word12;
	String word12a;
	String word11;
	String word11a;
	String word10;
	String word10a;
	String word09;
	String word09a;
	String word08;
	String word08a;
	String word07;
	String word07a;
	String word06;
	String word06a;
	String word05;
	String word05a;
	String word04;
	String word04a;
	String word03;
	String word03a;
	String word02;
	String word02a;
	String word01;
	String word01a;
	
	String final_word12a = "";
	String final_word11a = "";
	String final_word10a = "";
	String final_word09a = "";
	String final_word08a = "";
	String final_word07a = "";
	String final_word06a = "";
	String final_word05a = "";
	String final_word04a = "";
	String final_word03a = "";
	String final_word02a = "";
	String final_word01a = "";


	/** Called when the activity is first created. */
	 @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.jwords_layout, container, false);
         Context context = this.getActivity();

 		ConnectivityManager conMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
 		State mobile = conMan.getNetworkInfo(0).getState();
 		State wifi = conMan.getNetworkInfo(1).getState();
 		// || mobile==NetworkInfo.State.CONNECTING) ||
 		// (wifi==NetworkInfo.State.CONNECTED||
 		// wifi==NetworkInfo.State.CONNECTING)
 		if (mobile == NetworkInfo.State.CONNECTED|| mobile==NetworkInfo.State.CONNECTING ||
 		wifi==NetworkInfo.State.CONNECTED||
 		 wifi==NetworkInfo.State.CONNECTING) {
         display_J = (TextView) rootView.findViewById(R.id.displayJ);
         
        
         new progression().execute();
 		}else{
 			
 			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("Lost Internet Connection").setNegativeButton(
					"Go Back", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});

			AlertDialog alertDialog = builder.create();
			alertDialog.setCancelable(false);
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();
 		}
   
         return rootView;
	    // TODO Auto-generated method stub
	}
	 
	 public class progression extends AsyncTask<Void, Void, Void> {
			ProgressDialog dialog = new ProgressDialog(JWords_activity.this.getActivity());

			@Override
			protected void onPreExecute() {
				this.dialog.setCancelable(true);
				this.dialog
						.setMessage("Loading J words, Please Wait.......");
				this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				this.dialog.setProgress(0);
				this.dialog.setMax(1000);
				this.dialog.show();
			}

			@Override
			protected Void doInBackground(Void... params)  {

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
								.connect("http://www.scrabblefinder.com/scrabble-J-words/")
								// .referrer("http://www.scrabblefinder.com/solver/")
								.cookie("auth", "token").timeout(69999).get();

						
						
						word05 = doc.select("p:contains(5 Letter Scrabble J Words)").text();
						word05a = doc.select("p:contains(5 Letter Scrabble J Words) + ul").text();
						word04 = doc.select("p:contains(4 Letter Scrabble J Words)").text();
						word04a = doc.select("p:contains(4 Letter Scrabble J Words) + ul").text();
						word03 = doc.select("p:contains(3 Letter Scrabble J Words)").text();
						word03a = doc.select("p:contains(3 Letter Scrabble J Words) + ul").text();
						word02 = doc.select("p:contains(2 Letter Scrabble J Words)").text();
						word02a = doc.select("p:contains(2 Letter Scrabble J Words) + ul").text();


					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						display_J.setText("Sorry Server is Down, try again later");
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

			


				//	if (word02.contains("2 Letter")) {
						StringBuilder sb2 = new StringBuilder(word02a);
						int i2 = 0;

						while ((i2 = sb2.indexOf(" ", i2 + 2)) != -1) {
							sb2.replace(i2, i2 + 1, "\n\n");

						}

						if (sb2.toString() == null) {
							final_word02a = "";
						} else {
							final_word02a = "\n" + word02 + "\n\n" + sb2.toString();
						}

				//	}
					//if (word03.contains("3 Letter")) {
						StringBuilder sb3 = new StringBuilder(word03a);
						int i3 = 0;

						while ((i3 = sb3.indexOf(" ", i3 + 3)) != -1) {
							sb3.replace(i3, i3 + 1, "\n\n");

						}
						// word_tv.setText(word);

						if (word03 == (null)) {
							final_word03a = " ";
						} else {
							final_word03a = "\n" + word03 + "\n\n" + sb3.toString();
						}

				//	}
				//	if (word04.contains("4 Letter")) {
						StringBuilder sb4 = new StringBuilder(word04a);
						int i4 = 0;

						while ((i4 = sb4.indexOf(" ", i4 + 4)) != -1) {
							sb4.replace(i4, i4 + 1, "\n\n");

						}
						// word_tv.setText(word);

						if (word04 == (null)) {
							final_word04a = " ";
						} else {
							final_word04a = "\n" + word04 + "\n\n" + sb4.toString();
						}

				//	}
					//if (word05.contains("5 Letter")) {
						StringBuilder sb5 = new StringBuilder(word05a);
						int i5 = 0;

						while ((i5 = sb5.indexOf(" ", i5 + 5)) != -1) {
							sb5.replace(i5, i5 + 1, "\n\n");

						}
						// word_tv.setText(word);

						if (word05 == (null)) {
							final_word05a = " ";
						} else {
							final_word05a = "\n" + word05 + "\n\n" + sb5.toString();
						}

					

					display_J.setText("J Words" + "\n" 
							+ final_word05a+"\n"
							+ final_word04a +"\n"
							+ final_word03a +"\n"
							+ final_word02a + "\n");

				

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
