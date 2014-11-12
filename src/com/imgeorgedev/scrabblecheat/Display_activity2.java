package com.imgeorgedev.scrabblecheat;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Display_activity2 extends Activity {

	TextView display_tv2;
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
	Context context= this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.display2_layout);
		ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
 		State mobile = conMan.getNetworkInfo(0).getState();
 		State wifi = conMan.getNetworkInfo(1).getState();
 		if (mobile == NetworkInfo.State.CONNECTED|| mobile==NetworkInfo.State.CONNECTING ||
		 		wifi==NetworkInfo.State.CONNECTED||
		 		 wifi==NetworkInfo.State.CONNECTING) {
		// word_tv = (TextView) findViewById(R.id.wordtv);
		display_tv2 = (TextView) findViewById(R.id.displaytv2);
		extras = getIntent().getExtras();
		// if(extras !=null) {
		value_letter = extras.getString("word");
		value_start = extras.getString("prefix");
		value_end = extras.getString("suffix");
//		 Log.v("he678", value_letter + "wrd");
//		 Log.v("heStart", value_start+ "start");
//		 Log.v("heEND", value_end+"end");
		
			new progression().execute();
		}else{
 			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("Lost Internet Connection").setNegativeButton(
					"Go Back", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					});

			AlertDialog alertDialog = builder.create();
			alertDialog.setCancelable(false);
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();
 		}

	}

	public class progression extends AsyncTask<Void, Void, Void> {
		ProgressDialog dialog = new ProgressDialog(Display_activity2.this);

		@Override
		protected void onPreExecute() {
			this.dialog.setCancelable(true);
			this.dialog
					.setMessage("Looking for Possible words, Please Wait.......");
			this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			this.dialog.setProgress(0);
			this.dialog.setMax(1000);
			this.dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) throws NullPointerException {

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
							.connect("http://www.scrabblefinder.com/solver/")
							.data("prefix", value_start).data("submit.x", "0")
							.data("submit.y", "0").data("suffix", value_end)
							.data("words", value_letter).userAgent("Mozilla")
							// .referrer("http://www.scrabblefinder.com/solver/")
							.cookie("auth", "token").timeout(69999).post();

					word = doc.select("p:contains(Found)").text();
					word12 = doc.select("p:contains(12 Letter)").text();
					word12a = doc.select("p:contains(12 Letter) + ul a").text();
					word11 = doc.select("p:contains(11 Letter) + ul a").text();
					word11a = doc.select("p:contains(11 Letter) + ul a").text();
					word10 = doc.select("p:contains(10 Letter)").text();
					word10a = doc.select("p:contains(10 Letter) + ul a").text();
					word09 = doc.select("p:contains(9 Letter)").text();
					word09a = doc.select("p:contains(9 Letter) + ul a").text();
					word08 = doc.select("p:contains(8 Letter)").text();
					word08a = doc.select("p:contains(8 Letter) + ul a").text();
					word07 = doc.select("p:contains(7 Letter)").text();
					word07a = doc.select("p:contains(7 Letter) + ul a").text();
					word06 = doc.select("p:contains(6 Letter)").text();
					word06a = doc.select("p:contains(6 Letter) + ul a").text();
					word05 = doc.select("p:contains(5 Letter)").text();
					word05a = doc.select("p:contains(5 Letter) + ul a").text();
					word04 = doc.select("p:contains(4 Letter)").text();
					word04a = doc.select("p:contains(4 Letter) + ul a").text();
					word03 = doc.select("p:contains(3 Letter)").text();
					word03a = doc.select("p:contains(3 Letter) + ul a").text();
					word02 = doc.select("p:contains(2 Letter Scrabble Words)")
							.text();
					word02a = doc.select(
							"p:contains(2 Letter) + ul a")
							.text();
//					word01 = doc.select("p:contains(1 Letter)").text();
//					word01a = doc.select("p:contains(1 Letter) + ul a").text();
					
					//

				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					display_tv2.setText("Sorry Server is Down, try again later");
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

			if (word.equals("0 words Found")) {
				display_tv2.setText(word + "\n"+"\n"+"Sorry Try Again with different letters");

			} else {

//				if (word01.contains("1 Letter")) {
//					StringBuilder sb1 = new StringBuilder(word01a);
//					int i1 = 0;
//
//					while ((i1 = sb1.indexOf(" ", i1 + 2)) != -1) {
//						sb1.replace(i1, i1 + 1, "\n\n");
//
//					}
//
//					if (sb1.toString() == null) {
//						final_word01a = "";
//					} else {
//						final_word01a = "\n" + word01 + "\n\n" + sb1.toString();
//					}
//
//				}
				if (word02.contains("2 Letter")) {
					StringBuilder sb2 = new StringBuilder(word02a);
					int i2 = 0;

					while ((i2 = sb2.indexOf(" ", i2 + 2)) != -1) {
						sb2.replace(i2, i2 + 1, "\n\n");

					}

					if (sb2.toString() == null) {
						final_word02a = "";
					} else {
						final_word02a = "\n" + word02 + "\n\n" + sb2.toString()+"\n";
					}

				}
				if (word03.contains("3 Letter")) {
					StringBuilder sb3 = new StringBuilder(word03a);
					int i3 = 0;

					while ((i3 = sb3.indexOf(" ", i3 + 3)) != -1) {
						sb3.replace(i3, i3 + 1, "\n\n");

					}
					// word_tv.setText(word);

					if (word03 == (null)) {
						//final_word03a = "";
						final_word03a.trim();
					} else {
						final_word03a = "\n" + word03 + "\n\n" + sb3.toString()+"\n";
					}

				}
				if (word04.contains("4 Letter")) {
					StringBuilder sb4 = new StringBuilder(word04a);
					int i4 = 0;

					while ((i4 = sb4.indexOf(" ", i4 + 4)) != -1) {
						sb4.replace(i4, i4 + 1, "\n\n");

					}
					// word_tv.setText(word);

					if (word04 == (null)) {
						//final_word04a = "";
						final_word04a.trim();
					} else {
						final_word04a = "\n" + word04 + "\n\n" + sb4.toString()+"\n";
					}

				}
				if (word05.contains("5 Letter")) {
					StringBuilder sb5 = new StringBuilder(word05a);
					int i5 = 0;

					while ((i5 = sb5.indexOf(" ", i5 + 5)) != -1) {
						sb5.replace(i5, i5 + 1, "\n\n");

					}
					// word_tv.setText(word);

					if (word05 == (null)) {
						//final_word05a = "";
						final_word05a.trim();
					} else {
						final_word05a = "\n" + word05 + "\n\n" + sb5.toString()+"\n";
					}

				}
				if (word06.contains("6 Letter")) {
					StringBuilder sb6 = new StringBuilder(word06a);
					int i6 = 0;

					while ((i6 = sb6.indexOf(" ", i6 + 6)) != -1) {
						sb6.replace(i6, i6 + 1, "\n\n");

					}
					// word_tv.setText(word);

					if (word06 == (null)) {
						//final_word06a = "";
						final_word06a.trim();
					} else {
						final_word06a = "\n" + word06 + "\n\n" + sb6.toString()+"\n";
					}

				}
				if (word07.contains("7 Letter")) {
					StringBuilder sb7 = new StringBuilder(word07a);
					int i7 = 0;

					while ((i7 = sb7.indexOf(" ", i7 + 7)) != -1) {
						sb7.replace(i7, i7 + 1, "\n\n");

					}
					// word_tv.setText(word);

					if (word07 == (null)) {
						//final_word07a = "";
						final_word07a.trim();
					} else {
						final_word07a = "\n" + word07 + "\n\n" + sb7.toString()+"\n";
					}

				}
				if (word08.contains("8 Letter")) {
					StringBuilder sb8 = new StringBuilder(word08a);
					int i8 = 0;

					while ((i8 = sb8.indexOf(" ", i8 + 8)) != -1) {
						sb8.replace(i8, i8 + 1, "\n\n");

					}
					// word_tv.setText(word);

					if (word08 == (null)) {
						//final_word08a = "";
						final_word08a.trim();
					} else {
						final_word08a = "\n" + word08 + "\n\n" + sb8.toString()+"\n";
					}

				}
				if (word09.contains("9 Letter")) {
					StringBuilder sb9 = new StringBuilder(word09a);
					int i9 = 0;

					while ((i9 = sb9.indexOf(" ", i9 + 9)) != -1) {
						sb9.replace(i9, i9 + 1, "\n\n");

					}
					// word_tv.setText(word);

					if (word09 == (null)) {
						//final_word09a = "";
						final_word09a.trim();
					} else {
						final_word09a = "\n" + word09 + "\n\n" + sb9.toString()+"\n";
					}

				}
				if (word10.contains("10 Letter")) {
					StringBuilder sb10 = new StringBuilder(word10a);
					int i10 =100;

					while ((i10 = sb10.indexOf(" ", i10 + 10)) != -1) {
						sb10.replace(i10, i10 + 1, "\n\n");

					}
					// word_tv.setText(word);

					if (word10 == (null)) {
						//final_word10a = "";
						final_word10a.trim();
					} else {
						final_word10a = "\n" + word10 + "\n\n" + sb10.toString()+"\n";
					}

				}
				if (word11.contains("11 Letter")) {
					StringBuilder sb11 = new StringBuilder(word11a);
					int i11 = 0;

					while ((i11 = sb11.indexOf(" ", i11 + 11)) != -1) {
						sb11.replace(i11, i11 + 1, "\n\n");

					}
					// word_tv.setText(word);

					if (word11 == (null)) {
						//final_word11a = "";
						final_word11a.trim();
					} else {
						final_word11a = "\n" + word11 + "\n\n" + sb11.toString()+"\n";
					}

				}if (word12.contains("12 Letter")) {
					StringBuilder sb12 = new StringBuilder(word12a);
					int i12 = 0;

					while ((i12 = sb12.indexOf(" ", i12 + 12)) != -1) {
						sb12.replace(i12, i12 + 1, "\n\n");

					}
					// word_tv.setText(word);

					if (word12 == (null)) {
						//final_word12a = "";
						final_word12a.trim();
					} else {
						final_word12a = "\n" + word12 + "\n\n" + sb12.toString()+"\n";
					}

				}else{
					//do nothing
				}

				display_tv2.setText(word + "\n" + "\n"
						+ final_word12a
						+ final_word11a
						+ final_word10a
						+ final_word09a
						+ final_word08a
						+ final_word07a
						+ final_word06a
						+ final_word05a
						+ final_word04a
						+ final_word03a
						+ final_word02a + "\n"+"\n");

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
