package com.example.qatardeals.activites;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.qatardeals.R;
import com.example.qatardeals.data.InternetAccess;
import com.example.qatardeals.fragments.RecentDealsFragment;
import com.example.qatardeals.models.NavDrawerItem;
import com.example.qatardeals.models.ProductClass;
import com.qatardeals.adapter.NavDrawerListAdapter;

public class Activity_recent_deals extends ActionBarActivity {
	private final String TAG = "Activity_recent_deals";
	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	List<String> datalist;
	private String[] navMenuTitles;
	ArrayList<ProductClass> productList;
	ArrayList<NavDrawerItem> navDrawerItems;
	FragmentManager fm;
	FragmentTransaction fragmentTransaction;
	NavDrawerListAdapter adapter;
	FrameLayout frameLayout_container;
	private Fragment fragment;
	RecentDealsFragment recentdeals_fragments;
	// boolean isMobileConn = false;
	InternetAccess iAccess;
	ListView lst;
	ActionBar aBar;

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		mDrawerList.setDivider(this.getResources().getDrawable(R.drawable.divider));
		mDrawerList.setDividerHeight(1);

		frameLayout_container = (FrameLayout) findViewById(R.id.content_frame);
		recentdeals_fragments = new RecentDealsFragment();
		// frameLayoutBalance.setBackgroundColor(R.color.green);
		iAccess = new InternetAccess(this);
		fm = getSupportFragmentManager();
		fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.content_frame, recentdeals_fragments);
		fragmentTransaction.commit();
		
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		navDrawerItems = new ArrayList<NavDrawerItem>();
		
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[0]));			
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[1]));
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[2]));				
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[3]));				
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[4]));				
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[5]));				
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[6]));				
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[7]));

		productList = new ArrayList<ProductClass>();
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
				getActionBar().setDisplayHomeAsUpEnabled(true);
				getActionBar().setHomeButtonEnabled(true);
				getActionBar().setDisplayShowTitleEnabled(false);
				getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_background));

				Log.d(TAG, "Display Metrics - " + getResources().getDisplayMetrics().density);
				mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer,R.string.app_name,R.string.app_name) {
					public void onDrawerClosed(View view) {
						getActionBar().setTitle(mTitle);
						// calling onPrepareOptionsMenu() to show action bar icons
						invalidateOptionsMenu(); // Log.d("Drawer", "drawer closed");
					}

					public void onDrawerOpened(View drawerView) {
						getActionBar().setTitle(mDrawerTitle);
						// calling onPrepareOptionsMenu() to hide action bar icons
						invalidateOptionsMenu(); // Log.d("Drawer", "drawer opened");
					}
				};

				mDrawerLayout.setDrawerListener(mDrawerToggle);

				if (savedInstanceState == null) {
					// on first time display view for first nav item
					displayView(0);
					Log.v(TAG, "SavedInstanceState is null");
				}
				
	}
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}
	/***
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	//	menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {

		// update the main content by replacing fragments
		// Fragment fragment;
		// fragment = null;
		switch (position) {
		case 0:

			break;
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;
		case 7:

			break;
		default:
			break;

		}
		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			// fragmentManager.beginTransaction().addToBackStack(null).commit();

			fragmentManager.beginTransaction()
			// Add this transaction to the back stack
					.addToBackStack(null).replace(R.id.frame_container, fragment).commit();
			Log.e(TAG, "comitted fragment class - " + fragment.getClass().getName());

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		 switch (item.getItemId()) {
	        case R.id.action_search:
	            // search action
	            return true;
	        case R.id.my_referafriend:
	            // location found
	            return true;
	        
	        case R.id.my_wishlist:
	            // help action
	            return true;
	        case R.id.my_cart:
	            // check for updates action
	            return true;
	        case R.id.my_account:
	            // check for updates action
	            return true;
	        case R.id.logout:
	            // check for updates action
	            return true;
	        case R.id.contactus:
	            // check for updates action
	            return true;
	        case R.id.action_refresh:
	            // refresh
	            return true;
	        case R.id.action_settings:
	            // refresh
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
 
        return super.onCreateOptionsMenu(menu);
    }

	/*
	 * public void fetchingTodaysDeal(){ isMobileConn=iAccess.isOnline(); if
	 * (isMobileConn=false) { Toast.makeText(Activity_recent_deals.this,
	 * "Mobile data  not connected", Toast.LENGTH_SHORT).show();
	 * 
	 * } else { productList=new ArrayList<ProductClass>();
	 * 
	 * RequestParams params=new RequestParams(); params.put("lan", "1");
	 * 
	 * 
	 * HttpConnectionClient.post("/showrecentdeals", params, new
	 * AsyncHttpResponseHandler(){
	 * 
	 * public void onSuccess(String content) { super.onSuccess(content);
	 * Log.i("jismi",content); try { JSONObject jobj=new JSONObject(content);
	 * String status=jobj.getString("status"); if(status.equals("success")) {
	 * JSONArray jarray=jobj.getJSONArray("recentdeals"); JSONObject
	 * jproductobj; for(int i=0;i<jarray.length();i++){
	 * jproductobj=jarray.getJSONObject(i); productList.add(new
	 * ProductClass(jproductobj.getString("product_id"),
	 * jproductobj.getString("image"), jproductobj.getString("name"),
	 * jproductobj.getString("price"),jproductobj.getString("model"))); } } else
	 * { Toast.makeText(getApplicationContext(),jobj.getString("message"),Toast.
	 * LENGTH_SHORT).show(); } DealsCustomAdapter adapter=new
	 * DealsCustomAdapter(
	 * Activity_recent_deals.this,R.layout.item_deals_custom,productList);
	 * 
	 * lst.setAdapter(adapter); } catch (JSONException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * } }); }
	 */
	// }

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		// getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}


}
