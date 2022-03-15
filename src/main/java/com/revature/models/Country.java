package com.revature.models;

import java.util.Objects;

import com.revature.annotations.Column;
import com.revature.annotations.Table;
import com.revature.models.Car.CarType;

// Fetches some data about the country where the car was produced 
@Table(name = "Countries")
public class Country {

	@Column(name = "country")
	private String country;

	@Column(name = "number_of_manufacturers")
	private int numManufacturers;

	@Column(name = "total_anual")
	private long totalAnnual;

	@Column(name = "countries")
	private Countries countries;

	public enum Countries {

		USA, JAPAN, GERMANY, INDIA, MEXICO, UK;

	}

	public Country(String country, int numManufacturers, int totalAnnual) {
		super();
		this.country = country;
		this.numManufacturers = numManufacturers;
		this.totalAnnual = totalAnnual;
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

	public long getTotalAnnual() {
		return totalAnnual;
	}

	public void setTotalAnnual(int totalAnnual) {
		this.totalAnnual = totalAnnual;
	}

	public Countries getCountries() {
		return countries;
	}

	public void setCountries(Countries countries) {
		this.countries = countries;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, numManufacturers, totalAnnual);
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
				&& totalAnnual == other.totalAnnual;
	}

}
