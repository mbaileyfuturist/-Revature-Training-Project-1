package com.revature.dao;

import java.util.ArrayList;

import com.revature.models.Car;
import com.revature.models.Country;
import com.revature.models.Manufacturer;
import com.revature.models.Model;
import com.revature.orm.ORM;

public class Dao {
	
	private ORM orm = new ORM();
	
	public void initializeParentTables(ArrayList<Country> countries, ArrayList<Manufacturer> manufacturers) {
		
		orm.saveCountriesAndManufacturers(countries, manufacturers);
	}
	
	public int insert(Car car, String countryName, Model model) {
		
		int primaryKey = orm.saveCar(car);
		
		return primaryKey;
	}
	
	public boolean delete(Car car) {
		
		boolean deleted = orm.remove(car);
		
		return deleted;
		
	}
	
	public boolean update(Car car, int primaryKey) {
		
		boolean changed = orm.change(car, primaryKey);
		
		return changed;
				
	}

}
