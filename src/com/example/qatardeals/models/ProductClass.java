package com.example.qatardeals.models;

public class ProductClass {

	String prod_id,image,name,price,model,category_id,href;
	int rating;

	public ProductClass(String prod_id, String image, String name,
			String price, String model) {
		super();
		this.prod_id = prod_id;
		this.image = image;
		this.name = name;
		this.price = price;
		this.model = model;
	}
	
public ProductClass(String prod_id, String image, String name,
			String price, String model, String category_id) {
		super();
		this.prod_id = prod_id;
		this.image = image;
		this.name = name;
		this.price = price;
		this.model = model;
		this.category_id = category_id;
	}
	
	public ProductClass(String prod_id, String image, String name,
			int rating, String href) {
		super();
		this.prod_id = prod_id;
		this.image = image;
		this.name = name;
		this.rating = rating;
		this.href = href;
	}

	public String getProd_id() {
		return prod_id;
	}

	public String getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public String getModel() {
		return model;
	}
	public String getCategory_id()
	{
		return category_id;
	}
	public int getRating()
	{
		return rating;
	}
	public String getHref()
	{
		return href;
	}


}
