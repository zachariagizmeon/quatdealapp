package com.qatardeals.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.qatardeals.R;
import com.example.qatardeals.models.ProductClass;

public class HorizontalAdapter extends ArrayAdapter<ProductClass> {
	LayoutInflater tinflater;
	ArrayList<ProductClass> productList;
	
	public HorizontalAdapter(Context context, int resource, ArrayList<ProductClass> productList) {
		super(context, resource, productList);
		// TODO Auto-generated constructor stub
		tinflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ProductHolder productholder;
		if (convertView == null) {
			//convertView = tinflater.inflate(R.layout.item_photo_album_list, null);
			productholder = new ProductHolder();
			//productholder.mImage = (ImageView) convertView.findViewById(R.id.image_album);
			convertView.setTag(productholder);
		} else {
			productholder = (ProductHolder) convertView.getTag();
		}

		
		return convertView;
	}


private class ProductHolder {
	ImageView mImage;
}
}
