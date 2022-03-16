package com.revature.models;

import java.util.Objects;

import com.revature.annotations.Column;
import com.revature.annotations.Table;

@Table(name="Models")
public class Model {
	
	@Column(name="id")
	private int id;

	@Column(name="model_name")
	private String modelName;

	@Column(name="year_founded")
	private String yearFounded;

	@Column(name="country")
	private String country;
	
	@Column(name="manufacturer_id")
	private int makerId;
	
	public Model(String modelName, String yearFounded, String country, int makerId) {
		super();
		this.modelName = modelName;
		this.yearFounded = yearFounded;
		this.country = country;
		this.makerId = makerId;
	}
	
	public String getmodelName() {
		return modelName;
	}

	public void setmodelName(String brandName) {
		this.modelName = brandName;
	}

	public String getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(String yearFounded) {
		this.yearFounded = yearFounded;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}	
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getMakerId() {
		return makerId;
	}

	public void setMakerId(int makerId) {
		this.makerId = makerId;
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















