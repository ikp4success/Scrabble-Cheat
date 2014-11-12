package com.imgeorgedev.scrabblecheat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Word4Friends_Activity extends Fragment {

	Button bt;
	EditText et;
	EditText startwith_et;
	EditText endwith_et;
	TextView emptyError;
	String letters;
	String start_let = "";
	String end_let = "";
	Intent i;
	Context context = this.getActivity();

	 @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
		 setRetainInstance(true);
         View rootView = inflater.inflate(R.layout.wordfriends_layout, container, false);
         
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//setContentView(R.layout.search_layout);
     	ConnectivityManager conMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
 		final State mobile = conMan.getNetworkInfo(0).getState();
 		final State wifi = conMan.getNetworkInfo(1).getState();
 		// || mobile==NetworkInfo.State.CONNECTING) ||
 		// (wifi==NetworkInfo.State.CONNECTED||
 		// wifi==NetworkInfo.State.CONNECTING)
		

		bt = (Button) rootView.findViewById(R.id.button2);
		et = (EditText) rootView.findViewById(R.id.editText2);
		startwith_et = (EditText) rootView.findViewById(R.id.startET2);
		endwith_et = (EditText) rootView.findViewById(R.id.endET2);
		emptyError = (TextView) rootView.findViewById(R.id.errorTV2);
		// tf.setText("Use up to two ? as wildcards");
		//

		bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Log.v("jkj678", letters);
				// if(et.equals(spc)){
				// et.getText().toString().trim();
				//if(isConnected ==true){
			
		 		
				if (et.getText().toString().trim().equals("")
						|| et.getText().toString() == (null)) {
					emptyError.setText("Textbox Empty");

				} else {
					
					new progression().execute();
				}
		 		
//				}else{
//					AlertDialog.Builder builder = new AlertDialog.Builder(context);
//					builder.setMessage("Lost Internet Connection").setNegativeButton(
//							"Go Back", new DialogInterface.OnClickListener() {
//
//								@Override
//								public void onClick(DialogInterface dialog, int which) {
//									
//
//								}
//							});
//
//					AlertDialog alertDialog = builder.create();
//					alertDialog.show();
//				}

				// }

				// tf.setText(letters);
			}
		});
		
		return rootView;

	}

	public class progression extends AsyncTask<Void, Void, Void> {
		ProgressDialog dialog = new ProgressDialog(Word4Friends_Activity.this.getActivity());

		@Override
		protected void onPreExecute() {
			this.dialog.setCancelable(true);
			this.dialog.setMessage("Contacting Web, Please Wait.......");
			this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			this.dialog.setProgress(0);
			this.dialog.setMax(5);
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
				letters = et.getText().toString().trim();
				start_let = startwith_et.getText().toString().trim();
				end_let = endwith_et.getText().toString().trim();
				i = new Intent(Word4Friends_Activity.this.getActivity(), Display_activity.class);
				i.putExtra("word", letters);
				i.putExtra("prefix", start_let);
				i.putExtra("suffix", end_let);
				// }

				
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

			startActivity(i);

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

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getActivity().getMenuInflater().inflate(R.menu.search_activity, menu);
//		return true;
//	}

}
