package com.revature.dao;

import com.revature.models.Car;
import com.revature.orm.CarORM;

public class CarDao {
	
	private CarORM orm = new CarORM();
	
	public int insert(Car car) {
		
		
		int primaryKey = orm.save(car);
		
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
