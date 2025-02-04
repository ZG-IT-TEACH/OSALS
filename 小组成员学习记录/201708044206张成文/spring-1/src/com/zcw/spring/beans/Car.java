package com.zcw.spring.beans;

public class Car {

	private String brand;
	private double price;
	private int maxspeed;

	public Car(String brand, double price) {
		super();
		this.brand = brand;
		this.price = price;
	}

	public Car(String brand, int maxspeed) {
		super();
		this.brand = brand;
		this.maxspeed = maxspeed;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + ", maxspeed=" + maxspeed + "]";
	}

	public void setMaxspeed(int maxspeed) {
		this.maxspeed = maxspeed;
	}
}
