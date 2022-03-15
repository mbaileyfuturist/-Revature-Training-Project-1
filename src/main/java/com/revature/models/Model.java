package com.revature.models;

import java.util.Objects;

import com.revature.annotations.Column;
import com.revature.annotations.Table;

@Table(name="Models")
public class Model {
	
	@Column(name="id")
	int id;

	@Column(name="model_name")
	String modelName;

	@Column(name="year_founded")
	int yearFounded;

	@Column(name="country")
	String country;
	
	public Model(String modelName, int yearFounded, String country) {
		super();
		this.modelName = modelName;
		this.yearFounded = yearFounded;
		this.country = country;
	}
	
	public String getmodelName() {
		return modelName;
	}

	public void setmodelName(String brandName) {
		this.modelName = brandName;
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
		return Objects.hash(modelName, country, yearFounded);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		return Objects.equals(modelName, other.modelName) && Objects.equals(country, other.country)
				&& yearFounded == other.yearFounded;
	}

	@Override
	public String toString() {
		return "Brand [brandName=" + modelName + ", yearFounded=" + yearFounded + ", country=" + country + "]";
	}

}















