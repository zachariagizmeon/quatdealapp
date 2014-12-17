package com.example.qatardeals.activites;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.qatardeals.R;
import com.example.qatardeals.data.HttpConnectionClient;
import com.example.qatardeals.data.InternetAccess;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_login extends Activity {
	EditText txt_uname, txt_pwd;
	TextView txt_frgtpwd;
	Button btn_login_login, btn_login_register;
	
	boolean isMobileConn = false;
	InternetAccess iAccess;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_details);
		iAccess = new InternetAccess(Activity_login.this);
		txt_frgtpwd = (TextView) findViewById(R.id.textForgotPassword);
		
		txt_uname = (EditText) findViewById(R.id.editEmailId);
		txt_pwd = (EditText) findViewById(R.id.editpassword);
		btn_login_login = (Button) findViewById(R.id.btnLogin);
		btn_login_register = (Button) findViewById(R.id.btnRegister);
		btn_login_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				isMobileConn = iAccess.isOnline();

				if (isMobileConn == false) {
					Toast.makeText(Activity_login.this, "Mobile data  not connected.",
							Toast.LENGTH_SHORT).show();
				} else {					
					String uname = txt_uname.getText().toString();
					String pwd = txt_pwd.getText().toString();
					if(uname.equals(""))
					{
						txt_uname.setError("email is required");
					}
					if(pwd.equals(""))
					{
						txt_pwd.setError("password is required");
					}
					if(uname.equals("")||(pwd.equals("")))
					{
						Toast.makeText(getApplicationContext(), "fields cannot be blank", Toast.LENGTH_LONG).show();
					}
					else {
						final ProgressDialog pd = new ProgressDialog(Activity_login.this);
						pd.show();
					RequestParams params = new RequestParams();
					params.put("email", uname);
					params.put("password", pwd);
					HttpConnectionClient.post("/login", params,
							new AsyncHttpResponseHandler() {

						@Override
						public void onSuccess(String content) {
							// TODO Auto-generated method stub
							super.onSuccess(content);
							Log.i("jismi", "" + content);

							try {
								JSONObject jobj = new JSONObject(
										content);
								String status = jobj
										.getString("status");
								if (status.equals("Success")) {
									JSONObject dataobj = jobj
													.getJSONObject("data");

									 String custmer_id=dataobj.getString("customer_id");
									 String stre_id=dataobj.getString("store_id");
									Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_LONG).show();
									Intent login_nxt=new Intent(Activity_login.this, Activity_recent_deals.class);
									startActivity(login_nxt);
									Activity_login.this.finish();
								} else {
									Toast.makeText(
											getApplicationContext(),
											jobj.getString("message"),
											Toast.LENGTH_SHORT).show();
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
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
			}
		});

		btn_login_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ii = new Intent(Activity_login.this, Activity_registration.class);
				startActivity(ii);
			}
		});
		
		txt_frgtpwd.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				
				Intent ii = new Intent(Activity_login.this, Activity_change_password.class);
				startActivity(ii);
			}
		});

	}
}

