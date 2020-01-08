package com.northrow.freezer.catalog.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="freezer")
public class Freezer {
	
	private int id;
	
	private String foodName;
	
	private String foodType;
	
	private int  quantity;
	
	private String dateAdded;
	
	public Freezer() {
		
	}
	
	public Freezer(int id, String foodName, String foodType, int quantity, String dateAdded) {
		super();
		this.id = id;
		this.foodName = foodName;
		this.foodType = foodType;
		this.quantity = quantity;
		this.dateAdded = dateAdded;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	

}
