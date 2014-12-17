package com.example.qatardeals.activites;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.qatardeals.R;
import com.example.qatardeals.data.HttpConnectionClient;
import com.example.qatardeals.data.InternetAccess;
import com.example.qatardeals.models.ProductClass;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.R.array;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_deals_byCategory extends Activity {
	ArrayList<ProductClass> prductList;
	InternetAccess iAccess;
	boolean isMobileConn = false;
	ListView lst;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		iAccess=new InternetAccess(this);
		fetchDealsByCategory();
		lst=(ListView)findViewById(R.id.left_drawer);
		DealsCategoryCustomAdapter adapter=new DealsCategoryCustomAdapter(Activity_deals_byCategory.this,R.layout.item_dealscategory_custom, prductList);
		lst.setAdapter(adapter);
		lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

			}
		} );
	}
	public void fetchDealsByCategory() {
		isMobileConn=iAccess.isOnline();
		if(isMobileConn==false)
		{
			Toast.makeText(Activity_deals_byCategory.this, "Mobile data  not connected", Toast.LENGTH_SHORT).show();
		}
		else
		{
			prductList=new ArrayList<ProductClass>();
			RequestParams params=new RequestParams();
			params.add("lan","1" );
			HttpConnectionClient.post("/showdealsbycategory", params, new AsyncHttpResponseHandler(){
				@Override
				public void onSuccess( String content) {
					// TODO Auto-generated method stub
					super.onSuccess( content);
					Log.i("jismi", content);
					try {
						JSONObject jobj=new JSONObject(content);
						String status=jobj.getString("status");
						if(status.equals("success"))
						{
							JSONArray jarray=new  JSONArray("DealsByCategory");
							JSONObject dataObject;
							for(int i=0;i<=jarray.length();i++)
							{
								dataObject=jarray.getJSONObject(i);
								prductList.add(new ProductClass(dataObject.getString("product_id"),dataObject.getString("image"),
										dataObject.getString("model"),dataObject.getString("price"),
										dataObject.getString("name"),dataObject.getString("category_id")));

							}

						}

						else {
							Toast.makeText(getApplicationContext(),jobj.getString("message"),Toast.LENGTH_SHORT).show();
						}
						DealsCategoryCustomAdapter adapter =new DealsCategoryCustomAdapter(Activity_deals_byCategory.this, R.layout.item_dealscategory_custom, prductList);
						lst.setAdapter(adapter);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});
		}


	}

}
