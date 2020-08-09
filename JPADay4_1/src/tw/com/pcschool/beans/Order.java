/*
*
*Copyright (c) 2020, pcschool 
*/

package tw.com.pcschool.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Lee 
 * 課程大綱: 
 * 單向多對一
 */
@Table(name="JPA4_1_Order")
@Entity
public class Order {
	@GeneratedValue
	@Id
	private Integer Id;
	@JoinColumn (name="ORDER_NAME")
	private String orderName;
	@JoinColumn
	@ManyToOne
	private Customer customer;
	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
		return Id;
	}

	public Order() {}

	public void setId(Integer id) {
		Id = id;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}


}
