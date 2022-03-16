package com.revature.dao;

import java.util.ArrayList;

import com.revature.models.Car;
import com.revature.models.Country;
import com.revature.models.Manufacturer;
import com.revature.models.Model;
import com.revature.orm.ORM;

public class CarDao {
	
	private ORM orm = new ORM();
	
	public void initializeParentTables(ArrayList<Country> countries, ArrayList<Manufacturer> manufacturers) {
		
		orm.saveCountriesAndManufacturers(countries, manufacturers);
	}
	
	public int insert(Car car) {
		
		int primaryKey = orm.save(car);
		
		return primaryKey;
	}
	
	public boolean delete(int primaryKey) {
		
		boolean deleted = orm.remove("Car", primaryKey);
		
		return deleted;
		
	}
	
	public boolean update(Car car, int primaryKey) {
		
		boolean changed = orm.change("Car", car, primaryKey);
		
		return changed;
				
	}
	
	//Select Car by primary.
	
	//Select All method.

}
