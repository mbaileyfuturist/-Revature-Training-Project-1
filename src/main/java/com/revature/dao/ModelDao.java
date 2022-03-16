package com.revature.dao;

import java.util.ArrayList;

import com.revature.models.Car;
import com.revature.models.Country;
import com.revature.models.Manufacturer;
import com.revature.models.Model;
import com.revature.orm.ORM;

public class ModelDao {
	
private ORM orm = new ORM();
	
	public void initializeParentTables(ArrayList<Country> countries, ArrayList<Manufacturer> manufacturers) {
		
		orm.saveCountriesAndManufacturers(countries, manufacturers);
	}
	
	public int insert(Model model) {
		
		int primaryKey = orm.save(model);
		
		return primaryKey;
	}
	
	public boolean delete(Model model) {
		
		boolean deleted = orm.remove(model);
		
		return deleted;
		
	}

}
