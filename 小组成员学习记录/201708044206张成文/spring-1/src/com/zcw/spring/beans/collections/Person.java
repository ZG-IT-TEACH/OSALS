package com.zcw.spring.beans.collections;

import java.util.List;

import com.zcw.spring.beans.Car;

public class Person {

	private String name;
	private int age;
	private List<Car> cars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", cars=" + cars + "]";
	}

}
