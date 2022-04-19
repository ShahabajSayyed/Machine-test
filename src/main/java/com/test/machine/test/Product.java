package com.test.machine.test;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Product implements Serializable{
     
	@Id      
    int productid;
    String productname;
    int price;
 
 public Product() {
		super();
	}
 public Product(int productid, String productname, int price) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.price = price;
	}

public int getProductid() {
	return productid;
}

public void setProductid(int productid) {
	this.productid = productid;
}

public String getProductname() {
	return productname;
}

public void setProductname(String productname) {
	this.productname = productname;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

@Override
public String toString() {
	return "Product [productid=" + productid + ", productname=" + productname + ", price=" + price + "]";
}
 
 
}