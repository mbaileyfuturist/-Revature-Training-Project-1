package com.revature.models;

import java.util.Objects;

// Fetches some data about the country where the car was produced 

public class Countries {
	
	
	private String country;
	
	
	private long totalAnnual;
	
	public Countries(String country, int numManufacturers, int totalExported) {
		super();
		this.country = country;
		this.totalAnnual = totalExported;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public long getTotalAnnual() {
		return totalAnnual;
	}

	public void setTotalAnnual(int totalAnnual) {
		this.totalAnnual = totalAnnual;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(country, totalAnnual);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Countries other = (Countries) obj;
		return Objects.equals(country, other.country) && totalAnnual == other.totalAnnual;
	}

	@Override
	public String toString() {
		return "Countries [country=" + country + ", totalAnnual=" + totalAnnual + "]";
	}

	
	
}














