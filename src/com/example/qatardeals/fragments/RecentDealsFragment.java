package com.example.qatardeals.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qatardeals.R;
import com.example.qatardeals.customviews.HorizontalListView;
import com.example.qatardeals.data.HttpConnectionClient;
import com.example.qatardeals.data.InternetAccess;
import com.example.qatardeals.models.ProductClass;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.qatardeals.adapter.DealsCustomAdapter;

public class RecentDealsFragment  extends Fragment{
	View view1;

	ArrayList<ProductClass> productList;

	boolean isMobileConn = false;
	InternetAccess iAccess;
	HorizontalListView horizontalrecentlist,horizontallatestlist;
	public RecentDealsFragment() {

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{

		View view = inflater.inflate(R.layout.fragments_recent_details,
				container, false);
		iAccess=new InternetAccess(getActivity());
		productList=new ArrayList<ProductClass>();
		horizontalrecentlist=(HorizontalListView)view.findViewById(R.id.recent_deals_list);
		horizontallatestlist=(HorizontalListView)view.findViewById(R.id.latest_deals_list);
		
			RequestParams params=new RequestParams();
			params.put("lan", "1");


			HttpConnectionClient.post("/showrecentdeals", params, new AsyncHttpResponseHandler(){

				public void onSuccess(String content) {
					super.onSuccess(content);
					Log.i("jismi",content);
					try {
						JSONObject jobj=new JSONObject(content);
						String status=jobj.getString("status");
						if(status.equals("success"))
						{
							JSONArray jarray=jobj.getJSONArray("recentdeals");
							JSONObject jproductobj;
							for(int i=0;i<jarray.length();i++){
								jproductobj=jarray.getJSONObject(i);
								productList.add(new ProductClass(jproductobj.getString("product_id"), 
										jproductobj.getString("image"), jproductobj.getString("name"),
										jproductobj.getString("price"),jproductobj.getString("model")));
							} 
						}
						else {
							Toast.makeText(getActivity(),jobj.getString("message"),Toast.LENGTH_SHORT).show();
						}
						DealsCustomAdapter adapter=new DealsCustomAdapter(getActivity(),R.layout.item_deals_custom,productList);

						horizontalrecentlist.setAdapter(adapter);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
			});
	//	}


		return view1=view;

	}


}
