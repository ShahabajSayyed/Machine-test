package com.test.machine.test;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class ProductApi {
	
	
	@Autowired
	public SessionFactory factory;
	
	List<Product>  list;
	

	
	@PostConstruct
	void init()
	{
		
		getLatestData();
	}
	
	void getLatestData()
	{
		
		Session session=factory.openSession();
		
		Query query =session.createQuery("from Product");
		
		list = query.list();// 
		System.out.println("Init method is called . Data from database ");
		
		System.out.println(list);
	
	}
	
	
	@GetMapping("allProducts")
	List<Product>  allProducts()
	{
	    getLatestData();
		return list;
	}
	
	
	@GetMapping("Product/{productid}")
	Product getProduct(@PathVariable int productid)
	{
			
		Session session=factory.openSession();
		
		Product product=session.load(Product.class,productid);
		
		return product;
							
	}
	


	@PostMapping("product")
	List<Product> addProduct(@RequestBody Product product)
	{
		Session session=factory.openSession();
		
		
		Transaction tx = session.beginTransaction();
		
			session.save(product);
		
		tx.commit();
		
		getLatestData();
		return list;
		
	}
		
	
	@PutMapping("product")
	List<Product> updateProduct(@RequestBody Product clientproduct)
	{
		
		Session session=factory.openSession();
		
		
		Transaction tx = session.beginTransaction();
		
			session.saveOrUpdate(clientproduct);
			
			
		
		tx.commit();
		
		getLatestData();
		
		

		return list;
		
		
	}
	
	
	@DeleteMapping("product/{productid}")
	List<Product> deleteproduct(@PathVariable int productid)
	{
			Session session=factory.openSession();
		
		Product product=session.load(Product.class,productid);
			
			
			Transaction tx = session.beginTransaction();
		
				session.delete(product);
				
			tx.commit();
			
			getLatestData();
			
			return list;
	}
	
	
	
	@PostMapping("products")
	List< Product> addProducts(@RequestBody Product[] products)
	{
		Session session=factory.openSession();
				
		Transaction tx = session.beginTransaction();
		
		for( Product  product:products)
			session.save(product);
		
		tx.commit();
		
		getLatestData();
		return list;
		
	}

}


