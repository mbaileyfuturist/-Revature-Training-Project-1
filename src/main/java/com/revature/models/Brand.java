package com.revature.models;

import java.util.Objects;

public class Brand {

	// maker_name
	String brandName;

	// year_founded
	int yearFounded;

	// country_id
	String country;
	
	// constructor

	public Brand(String brandName, int yearFounded, String country) {
		super();
		this.brandName = brandName;
		this.yearFounded = yearFounded;
		this.country = country;
	}

	
	// getters and setters
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}	
	
	@Override
	public int hashCode() {
		return Objects.hash(brandName, country, yearFounded);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		return Objects.equals(brandName, other.brandName) && Objects.equals(country, other.country)
				&& yearFounded == other.yearFounded;
	}


	@Override
	public String toString() {
		return "Brand [brandName=" + brandName + ", yearFounded=" + yearFounded + ", country=" + country + "]";
	}

}















