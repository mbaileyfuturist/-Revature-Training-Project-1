package com.revature.main;

import com.revature.dao.CarDao;
import com.revature.models.Car;
import com.revature.models.CarType;
import com.revature.models.TransmissionType;

public class Driver {

	public static void main(String[] args) {
		
		CarDao dao = new CarDao();
		CarType carType = CarType.CONVERTIBLE;
		TransmissionType transmission = TransmissionType.AUTOMATIC;
		
		//Create a car object.
		Car supra = new Car("Toyota", "Supra", 2021, "Silver", 382, 3.7, 155.0, 32, carType, transmission);
		Car viper = new Car("Dodge", "Viper", 2021, "Blue", 645, 3.0,  206.0, 19, carType, transmission);

		
		//insert it using CarDao.
		int pk = dao.insert(supra);
		
		//boolean deleted = dao.delete(supra);
		
		//boolean updated = dao.update(supra, 1);
		
		//System.out.println(pk);
	}

}
