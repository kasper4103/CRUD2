/*
*
*Copyright (c) 2020, pcschool 
*/

package tw.com.pcschool.utils;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tw.com.pcschool.beans.Customer;
import tw.com.pcschool.beans.Order;

/**
*
*@author Lee
* 課程大綱:
*join
*單向多對一
*單向一對多
*雙向多對一
*雙向一對一
*雙向多對多

*/
public class JUnitMain {
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	EntityTransaction entityTransaction;
	@Before
	public void before() {
		// 1.創建一個管理工廠類
		String persistenceUnitName = "JPADay04_1";
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		// 2.創建一個管理類
		entityManager = entityManagerFactory.createEntityManager();
		// 3.開啟事務
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
	}
	
	@Test
	public void getCustomer() {
		// 4.建議beans結構
		Customer customer = new Customer();
		customer.setAge(13);
		customer.setEmail("kasper@gmail.com");
		customer.setLastName("Lee");
		customer.setCreateDate(new Date());
		customer.setBirth(new Date());
		

		entityManager.persist(customer);	
	}
	
	
	
	/*
	 *新增
	 */
	@Test
	public void testManyToOne() {
		Customer customer = new Customer();
		customer.setAge(13);
		customer.setEmail("kasper@gmail.com");
		customer.setLastName("Lee");
		customer.setCreateDate(new Date());
		customer.setBirth(new Date());
		
		Order o1=new Order();
		o1.setOrderName("sss");
		Order o2=new Order();
		o2.setOrderName("aaa");
		
		//建立關聯
		customer.getOrder().add(o1);
		customer.getOrder().add(o2);
		o1.setCustomer(customer);
		o2.setCustomer(customer);
		
		//存取
		entityManager.persist(customer);
		entityManager.persist(o1);
		entityManager.persist(o2);
	}
	
	/*
	 *更新
	 */
	@Test
	public void testManyToOneUpdate() {
		Customer customer = entityManager.find(Customer.class, 5);
		customer.getOrder().iterator().next().setOrderName("adsada");
		customer.getOrder().iterator().next().setOrderName("aqw");
	}
	
	

	/*
	 *移除
	 */
	@Test
	public void testManyToOneRemove() {
		Customer customer = entityManager.find(Customer.class, 1);
		entityManager.remove(customer);
	}
	
	
	
	/*
	 *搜尋 
	 */
	@Test
	public void testManyToOneFind() {
		Customer customer = entityManager.find(Customer.class, 3);
		System.out.println(customer.getBirth());
		System.out.println(customer.getOrder().size());
	}
	
	/*
	 * sql
	 * hql
	 * jpal
	 */
	
	@Test
	public void testHelloJPQL() {
		String jpql="FROM Customer c WHERE c.age>?";
		Query query=entityManager.createQuery(jpql);
		
		query.setParameter(1, 1);
		List<Customer> customer =query.getResultList();
		System.out.println(customer.size());
	}
	
	@Test
	public void testNameQuery() {
		Query query =entityManager.createNamedQuery("testNameQuery").setParameter(1, 1);
		Customer cus = (Customer) query.getSingleResult();
		System.out.println(cus);
	}
	
	@After
	public void after() {
		// 5.提交事務
		entityTransaction.commit();
		// 6.關閉管理類
		entityManager.close();
		// 7.關閉管理工廠類
		entityManagerFactory.close();
		
	}
}
