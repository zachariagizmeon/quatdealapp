package com.example.qatardeals.activites;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.qatardeals.R;
import com.example.qatardeals.R.id;
import com.example.qatardeals.data.HttpConnectionClient;
import com.example.qatardeals.data.InternetAccess;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_change_password  extends Activity{
	EditText txt_email_id,txt_new_password;
	Button btn_changpwrd;
	boolean isMobileConn = false;
	InternetAccess iAccess;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		iAccess = new InternetAccess(Activity_change_password.this);
		txt_email_id=(EditText)findViewById(R.id.pwChangeEmailEntry);
		txt_new_password=(EditText)findViewById(R.id.pwChangeNewEntry);
		btn_changpwrd=(Button) findViewById(R.id.pwChangeSubmit);
		btn_changpwrd.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				isMobileConn=iAccess.isOnline();

				if(isMobileConn==false)
				{
					Toast.makeText(Activity_change_password.this, "Mobile data  not connected.", Toast.LENGTH_LONG).show();
				}
				else 
				{
					
					String pwCh_email=txt_email_id.getText().toString();
					String newpass=txt_new_password.getText().toString();
					RequestParams params = new RequestParams();
					params.put("email", pwCh_email);
					params.put("password", newpass);
					Log.i("jismi", ""+pwCh_email + " "+newpass);
					final ProgressDialog pd=new ProgressDialog(Activity_change_password.this);
					pd.show();
					HttpConnectionClient.post("/resetPassword", params,
							new AsyncHttpResponseHandler() {

						@Override
						public void onSuccess(String content) {
							// TODO Auto-generated method stub
							super.onSuccess(content);
							Log.i("jismi", "" + content);

							try {
								JSONObject jobj = new JSONObject(
										content);
								String status=jobj.getString("status");
								if(status.equals("Success"))
								{
									Toast.makeText(getApplicationContext(),jobj.getString("message"),Toast.LENGTH_SHORT).show();
//									Intent chpas_nxt=new Intent(Changepassword.this,Deals.class);
//									startActivity(chpas_nxt);


								}else
								{
									Toast.makeText(getApplicationContext(),jobj.getString("message"),Toast.LENGTH_SHORT).show();
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
							if (pd.isShowing()) {
								pd.dismiss();
							}

						}

						@Override
						public void onFailure(Throwable error,
								String content) {
							// TODO Auto-generated method stub
							super.onFailure(error, content);
							if (pd.isShowing()) {
								pd.dismiss();
							}
						}
					});





				}
			}


		});
	}

}
