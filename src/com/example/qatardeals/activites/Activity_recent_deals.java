package com.example.qatardeals.activites;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qatardeals.R;
import com.example.qatardeals.data.HttpConnectionClient;
import com.example.qatardeals.data.InternetAccess;
import com.example.qatardeals.fragments.RecentDealsFragment;
import com.example.qatardeals.models.ProductClass;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Activity_recent_deals extends ActionBarActivity {
	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	List<String> datalist;

	ArrayList<ProductClass> productList;

	FragmentManager fm;
	FragmentTransaction fragmentTransaction ;

	FrameLayout frameLayout_container;
	
	RecentDealsFragment recentdeals_fragments;
	//	boolean isMobileConn = false;
	InternetAccess iAccess;
	ListView lst;
	ActionBar aBar;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,	GravityCompat.START);
		
		frameLayout_container = (FrameLayout)findViewById(R.id.content_frame);
		recentdeals_fragments=new RecentDealsFragment();
		// frameLayoutBalance.setBackgroundColor(R.color.green);
			iAccess=new InternetAccess(this);
		fm = getSupportFragmentManager();
		 fragmentTransaction = fm.beginTransaction();
	fragmentTransaction.replace(R.id.content_frame, recentdeals_fragments);
		fragmentTransaction.commit();

		datalist=new ArrayList<String>();

		productList=new ArrayList<ProductClass>();

	
		datalist.add("HOME");
		datalist.add("Categories");
		datalist.add("Desktpos");
		datalist.add("Laptops");
		datalist.add("Notebooks");
		datalist.add("Components");
		datalist.add("Tablets");
		datalist.add("Softwares");
		mDrawerList	=(ListView)findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,	GravityCompat.START);
		mDrawerList.setAdapter(new ArrayAdapter<String>(Activity_recent_deals.this, android.R.layout.simple_list_item_1,datalist));

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		aBar=getSupportActionBar();

		aBar.setDisplayHomeAsUpEnabled(true);
		aBar.setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(Activity_recent_deals.this, mDrawerLayout,
				R.drawable.logo, R.string.app_name,R.string.app_name) {
			public void onDrawerClosed(View view) {
				//	getActionBar().setTitle(mTitle);
				//invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}		

			public void onDrawerOpened(View drawerView) {
				//				getActionBar().setTitle(mDrawerTitle);
				//				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}
		};




		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {

		}

		/*	DealsCustomAdapter adapter=new DealsCustomAdapter(Activity_recent_deals.this,R.layout.item_deals_custom,productList);

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
		 */
	}

	public void setTitle(CharSequence title) {
		mTitle = title;
		aBar.setTitle(mTitle);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return true;
	}

	/*	public void fetchingTodaysDeal(){
		isMobileConn=iAccess.isOnline();
		if (isMobileConn=false) {
			Toast.makeText(Activity_recent_deals.this, "Mobile data  not connected", Toast.LENGTH_SHORT).show();

		} 
		else {
			productList=new ArrayList<ProductClass>();

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
							Toast.makeText(getApplicationContext(),jobj.getString("message"),Toast.LENGTH_SHORT).show();
						}
					DealsCustomAdapter adapter=new DealsCustomAdapter(Activity_recent_deals.this,R.layout.item_deals_custom,productList);

					lst.setAdapter(adapter);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
			});
		}*/
	//}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private class DrawerItemClickListener implements
	ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			//SelectItem(position);

		}
	}

}
