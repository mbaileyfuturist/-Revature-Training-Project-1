package com.revature.main;

import com.revature.dao.CarDao;
import com.revature.models.Car;

public class Driver {

	public static void main(String[] args) {
		
		CarDao dao = new CarDao();
		
		//Create a car object.
		Car supra = new Car("Toyota", "Supra", "Silver", 25, 382, 155, 3.9, 2021);
		Car viper = new Car("Dodge", "Viper", "Blue", 12, 645, 206, 3.8, 2021);

		
		//insert it using CarDao.
		int pk = dao.insert(supra);
		
		//boolean deleted = dao.delete(supra);
		
		//boolean updated = dao.update(viper, 6);
		
		System.out.println(pk);
	}

}
