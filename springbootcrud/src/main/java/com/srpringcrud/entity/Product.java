package com.srpringcrud.entity;

import javax.persistence.Id;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Component
@Entity
public class Product {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int Id;
		private String Name;
		private int Quantity;
		private double Price;
		
		
		public Product(int id, String name, int quantity, double price) {
			super();
			Id = id;
			Name = name;
			Quantity = quantity;
			Price = price;
		}
		public Product() {
			super();
			// TODO Auto-generated constructor stub
		}
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public int getQuantity() {
			return Quantity;
		}
		public void setQuantity(int quantity) {
			Quantity = quantity;
		}
		public double getPrice() {
			return Price;
		}
		public void setPrice(double price) {
			Price = price;
		}
		@Override
		public String toString() {
			return "Product [Id=" + Id + ", Name=" + Name + ", Quantity=" + Quantity + ", Price=" + Price + "]";
		}
		
		
}
