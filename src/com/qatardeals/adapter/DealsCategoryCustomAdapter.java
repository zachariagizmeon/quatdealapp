package com.qatardeals.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qatardeals.R;
import com.example.qatardeals.models.ProductClass;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class DealsCategoryCustomAdapter extends ArrayAdapter<ProductClass> {
Context context;

	public DealsCategoryCustomAdapter(Context context, int resource,
			ArrayList<ProductClass> prductList) {
		super(context, resource, prductList);
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		ProductViewholder holder=null;
		if(convertView==null)
		{
		LayoutInflater inflater=LayoutInflater.from(context);
		convertView=inflater.inflate(R.layout.item_dealscategory_custom, null);
		holder=new ProductViewholder();
		holder.im_photo=(ImageView)convertView.findViewById(R.id.cat_imageView1);
		holder.t_name=(TextView)convertView.findViewById(R.id.cat_textname);
		holder.t_model=(TextView)convertView.findViewById(R.id.cat_textmodel);
		holder.t_price=(TextView)convertView.findViewById(R.id.cat_textprice);
		holder.t_categoryid=(TextView)convertView.findViewById(R.id.cat_textcategoryid);
		holder.t_productid=(TextView)convertView.findViewById(R.id.cat_textproductid);
		convertView.setTag(holder);
		
		}else 
		{
			holder=(ProductViewholder) convertView.getTag();
		}
		ProductClass prodobj=getItem(position);
		UrlImageViewHelper.setUrlDrawable(holder.im_photo, "http://talenweave.com/qatardeals2/image/cache/"+prodobj.getImage());
		holder.t_name.setText(prodobj.getName());
		holder.t_price.setText(prodobj.getPrice());
		holder.t_productid.setText(prodobj.getProd_id());
		holder.t_model.setText(prodobj.getModel());
		holder.t_categoryid.setText(prodobj.getCategory_id());
		
		
			return  convertView ;
		}
	private class ProductViewholder
	{
		ImageView im_photo;
		TextView t_name,t_productid,t_model,t_price,t_categoryid;
		
	}

	}
