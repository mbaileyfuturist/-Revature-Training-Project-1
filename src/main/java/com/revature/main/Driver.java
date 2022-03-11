package com.revature.main;

import com.revature.dao.CarDao;
import com.revature.models.Car;

public class Driver {

	public static void main(String[] args) {
		
		CarDao dao = new CarDao();
		
		//Create a car object.
		Car supra = new Car("Toyota", "Supra", 2021, "Silver", 382, 3.7, 155.0, 32);
		Car viper = new Car("Dodge", "Viper", 2021, "Blue", 645, 3.0,  206.0, 19);

		
		//insert it using CarDao.
		//int pk = dao.insert(supra);
		
		//boolean deleted = dao.delete(supra);
		
		boolean updated = dao.update(supra, 1);
		
		//System.out.println(pk);
	}

}
