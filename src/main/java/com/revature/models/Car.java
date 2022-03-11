package com.revature.models;

import java.util.Objects;

/*
 * Models
Id
Mpg
Horse power
Topspeed
Acceleration: speed from 0 - 60
Year
Type: enum Sports car, sedan, coup
Color:
Transmission Type: Enum Automatic, manual

 */

public class Car {

	private int carId; 
	private String make; 	
	private double year;
	private String color; 
	private boolean zeroToSixty; 
	private double topspeed; 

	private double mpg; 
	
	// no args constructor
	
	public Car() {
		
	}

	// no-id constructor
	
	public Car(String make, String color, boolean zeroToSixty, double topspeed, double year, double mpg) {
		super();
		this.make = make;
		this.color = color;
		this.zeroToSixty = zeroToSixty;
		this.topspeed = topspeed;
		this.year = year;
		this.mpg = mpg;
	}

	// full args constructor
	
	public Car(int carId, String make, String color, boolean zeroToSixty, double topspeed, double year, double mpg) {
		super();
		this.carId = carId;
		this.make = make;
		this.color = color;
		this.zeroToSixty = zeroToSixty;
		this.topspeed = topspeed;
		this.year = year;
		this.mpg = mpg;
	}


	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public double getYear() {
		return year;
	}

	public void setYear(double year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isZeroToSixty() {
		return zeroToSixty;
	}

	public void setZeroToSixty(boolean zeroToSixty) {
		this.zeroToSixty = zeroToSixty;
	}

	public double getTopspeed() {
		return topspeed;
	}

	public void setTopspeed(double topspeed) {
		this.topspeed = topspeed;
	}

	public double getMpg() {
		return mpg;
	}

	public void setMpg(double mpg) {
		this.mpg = mpg;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carId, color, make, mpg, topspeed, year, zeroToSixty);
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
		return carId == other.carId && Objects.equals(color, other.color) && Objects.equals(make, other.make)
				&& Double.doubleToLongBits(mpg) == Double.doubleToLongBits(other.mpg)
				&& Double.doubleToLongBits(topspeed) == Double.doubleToLongBits(other.topspeed)
				&& Double.doubleToLongBits(year) == Double.doubleToLongBits(other.year)
				&& zeroToSixty == other.zeroToSixty;
	}


	@Override
	public String toString() {
		return "Car [carId=" + carId + ", make=" + make + ", color=" + color + ", zeroToSixty=" + zeroToSixty
				+ ", topspeed=" + topspeed + ", year=" + year + ", mpg=" + mpg + "]";
	}
	
	
	
}