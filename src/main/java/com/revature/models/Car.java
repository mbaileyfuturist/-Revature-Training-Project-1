package com.revature.models;

import java.util.Objects;

import com.revature.annotations.Column;
import com.revature.annotations.PrimaryKey;
import com.revature.annotations.Table;

@Table(name = "car_details")
public class Car {
	
	//How can I make this auto increment using annotations?
	@Column(name = "id")
	private int id = 0;
	
	@Column(name = "make")
	private String make;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "body_color")
	private String color;
	
	@Column(name = "miles_per_gallon")
	private double mpg;
	
	@Column(name = "horse_power")
	private int horsePower;
	
	@Column(name = "top_speed")
	private int topSpeed;
	
	@Column(name = "acceleration")
	private double acceleration; //Speed from 0 - 60.
	
	@Column(name = "manufacture_year")
	private int year;
	
	public Car(int id, String make, String model, String color, double mpg, int horsePower, int topSpeed, double acceleration,
			int year) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.color = color;
		this.mpg = mpg;
		this.horsePower = horsePower;
		this.topSpeed = topSpeed;
		this.acceleration = acceleration;
		this.year = year;
	}

	public Car(String make, String model, String color, double mpg, int horsePower, int topSpeed, double acceleration,
			int year) {
		super();
		this.make = make;
		this.model = model;
		this.color = color;
		this.mpg = mpg;
		this.horsePower = horsePower;
		this.topSpeed = topSpeed;
		this.acceleration = acceleration;
		this.year = year;
	}

	public Car() {

	}
	
	public int getId() {
		return id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public double getMpg() {
		return mpg;
	}

	public void setMpg(double mpg) {
		this.mpg = mpg;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public int getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, make, model);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(color, other.color) && Objects.equals(make, other.make)
				&& Objects.equals(model, other.model);
	}

	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", color=" + color + "]";
	}
	
}