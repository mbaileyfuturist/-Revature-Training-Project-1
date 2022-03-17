package com.revature.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.models.Car;
import com.revature.models.Country;
import com.revature.models.Manufacturer;
import com.revature.orm.ORM;

public class CarDao {
	
	private ORM orm = new ORM();
	private static Logger logger = Logger.getLogger(CarDao.class);

	
	public void initializeParentTables(ArrayList<Country> countries, ArrayList<Manufacturer> manufacturers) {
		
		orm.saveCountriesAndManufacturers(countries, manufacturers);
	}
	
	public int insert(Car car) {
		
		int primaryKey = orm.save(car);
		
		if(primaryKey > 0) {
			logger.info("A car object has successfully been inserted into the Car table with primary key " + primaryKey);
		}
		
		return primaryKey;
	}
	
	public boolean delete(int primaryKey) {
		
		boolean deleted = orm.remove("Car", primaryKey);
		
		if(deleted) {
			logger.info("A record in the Car table with an id=" + primaryKey + " has successfully been deleted.");
		}
		
		return deleted;
		
	}
	
	public boolean update(Car car, int primaryKey) {
		
		boolean changed = orm.change("Car", car, primaryKey);
		
		if(changed) {
			logger.info("A record in the Car table with id=" + primaryKey + " has successfully been updated.");
		}
		
		return changed;
				
	}
	
	//Select Car by primary.
	public Car selectCar(int primaryKey) {
		
		Car car = orm.select(primaryKey);
		
		if(!car.equals(null)) {
			logger.info("A new record with id=" + primaryKey + " has successfully been selected.");
		}
		
		return car;
	}
	
//	//Select All method.
	public ArrayList<Car> SelectAll() {
		
		ArrayList<Car> cars = orm.selectAll();
		
		if(cars.size() > 0) {
			logger.info("All cars have successfully been selected from the Car table.");
		}
		
		return cars;
	}
}
