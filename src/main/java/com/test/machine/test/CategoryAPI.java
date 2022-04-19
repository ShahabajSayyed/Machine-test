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
public class CategoryAPI  {

	
	@Autowired
	 public SessionFactory factory;
	
		List<Category>  list;
		

		
		@PostConstruct
		void init()
		{
			
			getLatestData();
		}
		
		void getLatestData()
		{
			
			Session session=factory.openSession();
			
			Query query =session.createQuery("from Category");
			
			list = query.list();
			
			System.out.println("Init method is called . Data from database ");
			
			System.out.println(list);
		
		}
		
		@GetMapping("allCategoryies")
		List<Category>  Categoryies()
		{
		    getLatestData();
			return list;
		}
		
		
		@GetMapping("Category/{Categoryid}")
		Category getCategory(@PathVariable int categoryid)
		{
				
			Session session=factory.openSession();
			
			Category category=session.load(Category.class,categoryid);
			
			return category;
								
		}
		


		@PostMapping("category")
		List<Category> addCategory(@RequestBody Category category)
		{
			Session session=factory.openSession();
			
			
			Transaction tx = session.beginTransaction();
			
				session.save(category);
			
			tx.commit();
			
			getLatestData();
			return list;
			
		}
			
		
		@PutMapping("category")
		List<Category> updateProduct(@RequestBody Category clientcategory)
		{
			
			Session session=factory.openSession();
			
			
			Transaction tx = session.beginTransaction();
			
				session.saveOrUpdate(clientcategory);
				
				
			
			tx.commit();
			
			getLatestData();
			
			

			return list;
			
			
		}
		
		
		@DeleteMapping("category/{categoryid}")
		List<Category> deletecategory(@PathVariable int categoryid)
		{
				Session session=factory.openSession();
			
				Category	category=session.load(Category.class,categoryid);
				
				
				Transaction tx = session.beginTransaction();
			
					session.delete(category);
					
				tx.commit();
				
				getLatestData();
				
				return list;
		}
		
		
		
		@PostMapping("categoryies")
		List<Category> addcategoryies(@RequestBody Category[] categoryies)
		{
			Session session=factory.openSession();
					
			Transaction tx = session.beginTransaction();
			
			for( Category category:categoryies)
				session.save(categoryies);
			
			tx.commit();
			
			getLatestData();
			return list;
			
		}

	}




