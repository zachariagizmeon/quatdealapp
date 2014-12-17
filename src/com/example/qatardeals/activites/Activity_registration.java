package com.example.qatardeals.activites;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.qatardeals.R;
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

public class Activity_registration extends Activity {

	EditText txt_first_name, txt_last_name, txt_email, txt_telephone, txt_fax,
			txt_referral_name, txt_company, txt_company_id, txt_address1,
			txt_address2, txt_city, txt_post_code, txt_country, txt_region,
			txt_password, txt_con_password;
	Button btn_reg_register, btn_reg_login;
	public static final int MIN_PASSWORD_LENGTH = 5;
	public static final int MAX_PASSWORD_LENGTH = 15;

	boolean isMobileConn = false;
	InternetAccess iAccess;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_details);
		iAccess = new InternetAccess(Activity_registration.this);
		txt_first_name = (EditText) findViewById(R.id.reg_firstname);
		txt_last_name = (EditText) findViewById(R.id.reg_lastname);
		txt_email = (EditText) findViewById(R.id.reg_email);
		txt_telephone = (EditText) findViewById(R.id.reg_telephone);
		txt_fax = (EditText) findViewById(R.id.reg_fax);
		txt_referral_name = (EditText) findViewById(R.id.reg_referralname);
		txt_company = (EditText) findViewById(R.id.reg_company);
		txt_company_id = (EditText) findViewById(R.id.reg_companyid);
		txt_address1 = (EditText) findViewById(R.id.reg_address1);
		txt_address2 = (EditText) findViewById(R.id.reg_address2);
		txt_city = (EditText) findViewById(R.id.reg_city);
		txt_post_code = (EditText) findViewById(R.id.reg_postcode);
		txt_country = (EditText) findViewById(R.id.reg_country);
		txt_region = (EditText) findViewById(R.id.reg_regionstate);
		txt_password = (EditText) findViewById(R.id.reg_password);
		txt_con_password = (EditText) findViewById(R.id.reg_conpassword);
		btn_reg_register = (Button) findViewById(R.id.btn_Register);
		btn_reg_login = (Button) findViewById(R.id.btn_Login);

		btn_reg_register.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				isMobileConn = iAccess.isOnline();

				if (isMobileConn == false) {
					Toast.makeText(Activity_registration.this,
							"Mobile data  not connected.", Toast.LENGTH_LONG)
							.show();
				} else {
					final ProgressDialog pd = new ProgressDialog(
							Activity_registration.this);
					pd.show();
					String first_name = txt_first_name.getText().toString();
					String last_name = txt_last_name.getText().toString();
					String emal = txt_email.getText().toString();
					String telephne = txt_telephone.getText().toString();
					String fx = txt_fax.getText().toString();
					String referral_name = txt_referral_name.getText().toString();
					String compny = txt_company.getText().toString();
					String compny_id = txt_company_id.getText().toString();
					String address1 = txt_address1.getText().toString();
					String address2 = txt_address2.getText().toString();
					String cty = txt_city.getText().toString();
					String postcde = txt_post_code.getText().toString();
					String region = txt_region.getText().toString();
					String country = txt_country.getText().toString();
					String pwrd = txt_password.getText().toString();
					int len = pwrd.length();
					String con_password = txt_con_password.getText().toString();
					if (first_name.equals("")) {
						txt_first_name.setError("first name is required");
					}
					if (last_name.equals("")) {
						txt_last_name.setError("last name is required");
					}
					if (emal.equals("")) {
						txt_email.setError("email is required");
					}
					if (telephne.equals("")) {
						txt_telephone.setError(" telephone no is required");
					}
					if (address1.equals("")) {
						txt_address1.setError("address1 is required");

						if (cty.equals("")) {
							txt_city.setError("city is required");
						}
						if (postcde.equals("")) {
							txt_post_code.setError("post code is required");
						}
						if (country.equals("")) {
							txt_country.setError("country is required");
						}
						if (region.equals("")) {
							txt_region.setError(" region is reguired");
						}
						if (pwrd.equals("password is required")) {
							txt_password.setError("password is required");
						}
						if (con_password.equals("")) {
							txt_con_password
									.setError("confirm password is required");
						}
						if (first_name.equals("") || (last_name.equals(""))
								|| (emal.equals("")) || (telephne.equals(""))
								|| (address1.equals("")) || (cty.equals(""))
								|| (postcde.equals("")) || (country.equals(""))
								|| (region.equals("")) || (pwrd.equals(""))
								|| (con_password.equals(""))) {
							Toast.makeText(getApplicationContext(),
									"Field Vaccant", Toast.LENGTH_SHORT).show();
						} else if (len < MIN_PASSWORD_LENGTH
								|| len > MAX_PASSWORD_LENGTH) {
							txt_password
									.setError("Invalid password,should be between 5 and 15 characters long ");
							txt_password.setText("");
							txt_con_password.setText("");

						} else if (!pwrd.equals(con_password)) {
							Toast.makeText(getApplicationContext(),
									"Passwords do not match", Toast.LENGTH_SHORT)
									.show();
							txt_password.setText("");
							txt_con_password.setText("");
						} else {
							RequestParams params = new RequestParams();
							params.put("firstname", first_name);
							params.put("lastname", last_name);
							params.put("email", emal);
							params.put("telephone", telephne);
							params.put("fax", fx);
							params.put("company", compny);
							params.put("company_id", compny_id);
							params.put("address_1", address1);
							params.put("address_2", address2);
							params.put("city", cty);
							params.put("postcode", postcde);
							params.put("country_id", country);
							params.put("zone_id", region);
							params.put("password", pwrd);
							params.put("confirm", con_password);
							HttpConnectionClient.post("/register", params,
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
													Toast.makeText(
															getApplicationContext(),
															jobj.getString("message"),
															Toast.LENGTH_SHORT)
															.show();
													/*
													 * Intent reg_nxt=new
													 * Intent(
													 * Registration.this,Deals
													 * .class);
													 * startActivity(reg_nxt);
													 */
													Activity_registration.this.finish();
												} else {
													Toast.makeText(
															getApplicationContext(),
															jobj.getString("message"),
															Toast.LENGTH_SHORT)
															.show();
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch
												// block
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
			}
		});

		btn_reg_login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i_login = new Intent(Activity_registration.this, Activity_login.class);
				startActivity(i_login);
			}
		});

	}

}
