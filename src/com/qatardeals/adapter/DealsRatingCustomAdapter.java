package com.qatardeals.adapter;

import java.util.ArrayList;



import com.example.qatardeals.R;
import com.example.qatardeals.models.ProductClass;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DealsRatingCustomAdapter extends ArrayAdapter<ProductClass> {
	Context context;
	public DealsRatingCustomAdapter(Context context, int resource,
			ArrayList<ProductClass> objects) {
		super(context, resource, objects);
		this.context=context;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ProductViewHolder holder=null;		
		if(convertView==null){
			LayoutInflater inflater=LayoutInflater.from(context);
			convertView=inflater.inflate(R.layout.item_deals_custom, null);
			holder=new ProductViewHolder();
			holder.im_thumb=(ImageView) convertView.findViewById(R.id.imageView1);
			holder.t_name=(TextView) convertView.findViewById(R.id.textname);
			holder.t_pid=(TextView) convertView.findViewById(R.id.textproductid);
			holder.t_rating=(TextView) convertView.findViewById(R.id.textprice);
			holder.t_href=(TextView) convertView.findViewById(R.id.textmodel);
			convertView.setTag(holder);
		}else{
			holder=(ProductViewHolder) convertView.getTag();
		}
		ProductClass prodobj=getItem(position);
		UrlImageViewHelper.setUrlDrawable(holder.im_thumb, "http://talenweave.com/qatardeals2/image/cache/"+prodobj.getImage());
		holder.t_name.setText(prodobj.getName());
		int ratinG=prodobj.getRating();
		String rat=Integer.toString(ratinG);
		holder.t_rating.setText(rat);
		holder.t_pid.setText(prodobj.getProd_id());
		holder.t_href.setText(prodobj.getHref());
		
		
		return convertView;
		
	}
	
	private class ProductViewHolder{
		ImageView im_thumb;
		TextView t_name,t_rating,t_pid,t_href;
	}

}
