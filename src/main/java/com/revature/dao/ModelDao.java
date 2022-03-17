package com.revature.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.models.Car;
import com.revature.models.Country;
import com.revature.models.Manufacturer;
import com.revature.models.Model;
import com.revature.orm.ORM;

public class ModelDao {
	
private ORM orm = new ORM();
private static Logger logger = Logger.getLogger(ModelDao.class);

	
	public void initializeParentTables(ArrayList<Country> countries, ArrayList<Manufacturer> manufacturers) {
		
		orm.saveCountriesAndManufacturers(countries, manufacturers);
	}
	
	public int insert(Model model) {
		
		int primaryKey = orm.save(model);
		
		if(primaryKey > 0) {
			logger.info("A record in the Car table with an id=" + primaryKey + " has successfully been inserted.");
		}
		
		return primaryKey;
	}
	
	public boolean delete(int primaryKey) {
		
		boolean deleted = orm.remove("Model", primaryKey);
		
		if(deleted) {
			logger.info("A record in the Car table with an id=" + primaryKey + " has successfully been deleted.");
		}
		
		return deleted;
		
	}
	
	public boolean update(Model model, int primaryKey) {
		
		boolean changed = orm.change("Model", model, primaryKey);
		
		if(changed) {
			logger.info("A record in the Car table with an id=" + primaryKey + " has successfully been updated.");
		}
		
		return changed;	
	
	}

}
