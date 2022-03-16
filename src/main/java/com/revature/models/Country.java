package com.revature.models;

import java.util.Objects;

import com.revature.annotations.Column;
import com.revature.annotations.Table;
import com.revature.models.Car.CarType;

// Fetches some data about the country where the car was produced 
@Table(name = "Countries")
public class Country {
	
	@Column(name="id")
	private int id;
	
	@Column(name="country_name")
	private String country;

	@Column(name = "number_of_manufacturers")
	private int numManufacturers;
	
	@Column(name="total_exports_anual")
	private int totalExportsAnnual;

	public enum Countries {

		USA, JAPAN, GERMANY, INDIA, MEXICO, UK;

	}

	public Country(String country, int numManufacturers, int totalExportsAnnual) {
		super();
		this.country = country;
		this.numManufacturers = numManufacturers;
		this.totalExportsAnnual = totalExportsAnnual;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNumManufacturers() {
		return numManufacturers;
	}

	public void setNumManufacturers(int numManufacturers) {
		this.numManufacturers = numManufacturers;
	}

	public int getTotalAnnual() {
		return totalExportsAnnual;
	}

	public void setTotalAnnual(int totalAnnual) {
		this.totalExportsAnnual = totalAnnual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, numManufacturers, totalExportsAnnual);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(country, other.country) && numManufacturers == other.numManufacturers
				&& totalExportsAnnual == other.totalExportsAnnual;
	}

}
