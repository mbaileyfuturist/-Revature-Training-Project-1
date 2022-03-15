package com.revature.models;

import java.util.Objects;

import com.revature.annotations.Column;
import com.revature.annotations.Table;


@Table(name = "car_details")
public class Car {

	@Column(name="id")
	private int carId; 
	
	@Column(name = "make")
	private String make; 
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "manufacture_year")
	private int year;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "horse_power")
	private int horsePower;
	
	@Column(name = "acceleration")
	private double zeroToSixty;
	
	@Column(name = "top_speed")
	private double topSpeed; 

	@Column(name = "mpg")
	private double mpg; 
	
	@Column(name = "car_type")
	private CarType carType;
	
	@Column(name = "transmission")
	private TransmissionType transmission;
	
	@Column(name="model_id")
	private int modelId;
			
	public Car() {
		
	}

	public Car(String make, String model, int year, String color, int horsePower, double zeroToSixty,
			double topSpeed, double mpg, CarType carType, TransmissionType transmission, int modelId) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.horsePower = horsePower;
		this.zeroToSixty = zeroToSixty;
		this.topSpeed = topSpeed;
		this.mpg = mpg;
		this.carType = carType;
		this.transmission = transmission;
		this.modelId = modelId;
	}

	public Car(int carId, String make, String model, int year, String color, int horsePower, double zeroToSixty,
			double topSpeed, double mpg, CarType carType, TransmissionType transmission) {
		super();
		this.carId = carId;
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.horsePower = horsePower;
		this.zeroToSixty = zeroToSixty;
		this.topSpeed = topSpeed;
		this.mpg = mpg;
		this.carType = carType;
		this.transmission = transmission;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public double getZeroToSixty() {
		return zeroToSixty;
	}

	public void setZeroToSixty(double zeroToSixty) {
		this.zeroToSixty = zeroToSixty;
	}

	public double getTopspeed() {
		return topSpeed;
	}

	public void setTopspeed(double topspeed) {
		this.topSpeed = topspeed;
	}

	public double getMpg() {
		return mpg;
	}

	public void setMpg(double mpg) {
		this.mpg = mpg;
	}
	
	public double getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(double topSpeed) {
		this.topSpeed = topSpeed;
	}
	
	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public TransmissionType getTransmission() {
		return transmission;
	}

	public void setTransmission(TransmissionType transmission) {
		this.transmission = transmission;
	}
	
	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carId, color, horsePower, make, model, mpg, topSpeed, year, zeroToSixty);
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
		return carId == other.carId && Objects.equals(color, other.color) && horsePower == other.horsePower
				&& Objects.equals(make, other.make) && Objects.equals(model, other.model)
				&& Double.doubleToLongBits(mpg) == Double.doubleToLongBits(other.mpg)
				&& Double.doubleToLongBits(topSpeed) == Double.doubleToLongBits(other.topSpeed)
				&& Double.doubleToLongBits(year) == Double.doubleToLongBits(other.year)
				&& Double.doubleToLongBits(zeroToSixty) == Double.doubleToLongBits(other.zeroToSixty);
	}
	
}