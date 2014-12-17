package com.example.qatardeals.activites;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qatardeals.R;

import com.example.qatardeals.data.HttpConnectionClient;
import com.example.qatardeals.data.InternetAccess;
import com.example.qatardeals.models.ProductClass;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Activity_deals_byPrice extends Activity{ 

	ArrayList<ProductClass> productList;

	boolean isMobileConn = false;
	InternetAccess iAccess;
	ListView lst;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		iAccess=new InternetAccess(this);
		
		productList=new ArrayList<ProductClass>();
		fetchingDealsByPrice();		
		
		lst=(ListView)findViewById(R.id.left_drawer);
		DealsPriceCustomAdapter adapter=new DealsPriceCustomAdapter(Activity_deals_byPrice.this,R.layout.item_deals_custom,productList);
		
		lst.setAdapter(adapter);

		lst.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) 
			{
				//Intent home_nxt=new Intent(HomeActivity.this,)
			}
		});

	}

	public void fetchingDealsByPrice(){
		isMobileConn=iAccess.isOnline();
		if (isMobileConn=false) {
			Toast.makeText(Activity_deals_byPrice.this, "Mobile data  not connected", Toast.LENGTH_SHORT).show();

		} 
		else {
			productList=new ArrayList<ProductClass>();

			RequestParams params=new RequestParams();
			params.put("pricerange ", "$100-$200 ");
			

			HttpConnectionClient.post("/searchByPriceRange", params, new AsyncHttpResponseHandler(){

				public void onSuccess(String content) {
					super.onSuccess(content);
					Log.i("jismi",content);
					try {
						JSONObject jobj=new JSONObject(content);
						String status=jobj.getString("status");
						if(status.equals("success"))
						{
							JSONArray jarray=jobj.getJSONArray("dealsbyprice");
							JSONObject jproductobj;
							for(int i=0;i<jarray.length();i++){
								jproductobj=jarray.getJSONObject(i);
								productList.add(new ProductClass(jproductobj.getString("product_id"), 
										jproductobj.getString("thumb"), jproductobj.getString("name"),
										jproductobj.getString("price"),jproductobj.getString("href")));
							} 
						}
						else {
							Toast.makeText(getApplicationContext(),jobj.getString("message"),Toast.LENGTH_SHORT).show();
						}
						DealsCustomAdapter adapter=new DealsCustomAdapter(Activity_deals_byPrice.this,R.layout.item_deals_custom,productList);
						
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
