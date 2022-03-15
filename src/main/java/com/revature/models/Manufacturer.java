package com.revature.models;

import java.util.Objects;

import com.revature.annotations.Column;
import com.revature.annotations.Table;

@Table(name="Manufacturers")
public class Manufacturer {
	
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="full_name")
	private String full_name;
	
	@Column(name="country_id")
	private int countryId;
	
	public Manufacturer(String name, String full_name, int countryId) {
		super();
		this.name = name;
		this.full_name = full_name;
		this.countryId = countryId;
	}
	
	public Manufacturer(int id, String name, String full_name) {
		super();
		this.id = id;
		this.name = name;
		this.full_name = full_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return full_name;
	}

	public void setFullName(String full_name) {
		this.full_name = full_name;
	}
	
	
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(full_name, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manufacturer other = (Manufacturer) obj;
		return Objects.equals(full_name, other.full_name) && id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Make [id=" + id + ", name=" + name + ", full_name=" + full_name + "]";
	}
	
	

}
