package com.example.qatardeals.fragments;

import java.util.ArrayList;

import com.example.qatardeals.R;
import com.example.qatardeals.customviews.HorizontalListView;
import com.example.qatardeals.models.ProductClass;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class MywishlistFragment extends Fragment{
TextView noOfItems;
ListView wishList;
ArrayList<ProductClass> productList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View rootview = inflater.inflate(R.layout.fragment_wishlist,
				container, false);
		noOfItems=(TextView) rootview.findViewById(R.id.noofitems);
		wishList=(ListView) rootview.findViewById(R.id.wishlistitems);
		
		
		return rootview;
	}
	
}
