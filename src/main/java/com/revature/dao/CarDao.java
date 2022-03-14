package com.revature.dao;

import com.revature.models.Car;
import com.revature.orm.ORM;

public class CarDao {
	
	private ORM orm = new ORM();
	
	public int insert(Car car) {
		
		
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
