package com.revature.main;

import java.util.ArrayList;

import com.revature.dao.Dao;
import com.revature.models.Car;
import com.revature.models.CarType;
import com.revature.models.Country;
import com.revature.models.Manufacturer;
import com.revature.models.Model;
import com.revature.models.TransmissionType;

public class Driver {

	public static void main(String[] args) {
		
		Dao dao = new Dao();
		CarType carType = CarType.CONVERTIBLE;
		TransmissionType transmission = TransmissionType.AUTOMATIC;
		
		//Initially create a list of countries and manufacturers to insert
		//These tables should not have any redundancy. To show that the ORM works.
		
		//Initialize Parent Tables (countries & makers)
		ArrayList<Country> countries = new ArrayList<>();
		countries.add(new Country("USA", 4, 50000));
		countries.add(new Country("Germany", 5, 30000));
		countries.add(new Country("Italy", 5, 20000));
		countries.add(new Country("Japan", 10, 15000));
		countries.add(new Country("Britain", 8, 50000));
		
		ArrayList<Manufacturer> Manufacturers = new ArrayList<>();
		//USA
		Manufacturers.add(new Manufacturer("Dodge", "Dodge"));
		Manufacturers.add(new Manufacturer("Ford", "Ford"));
		Manufacturers.add(new Manufacturer("Chevy", "Chevrolet"));
		Manufacturers.add(new Manufacturer("GM", "General Moters"));
		Manufacturers.add(new Manufacturer("BMW", "Bayerische Motoren Werke AG"));
		
		//Germany
		Manufacturers.add(new Manufacturer("Mercedes", "Mercedes-Benz"));
		Manufacturers.add(new Manufacturer("VW", "Volkswagen"));
		Manufacturers.add(new Manufacturer("Audi", "Audi"));
		Manufacturers.add(new Manufacturer("Porsche", "Dr.-Ing. h.c. F. Porsche AG"));
		
		//Italy
		Manufacturers.add(new Manufacturer("FIAT", "Fiat Automobiles S.p.A."));
		Manufacturers.add(new Manufacturer("Lamborghini", "Automobili Lamborghini S.p.A."));
		Manufacturers.add(new Manufacturer("Alfa Romeo", "Alfa Romeo Automobiles S.p.A."));
		Manufacturers.add(new Manufacturer("Maserati", "Maserati S.p.A"));
		Manufacturers.add(new Manufacturer("Ferrari", "Ferrari S.p.A."));
		
		//Japan
		Manufacturers.add(new Manufacturer("ACURA", "Acura"));
		Manufacturers.add(new Manufacturer("Nissan", "Nissan Motor Corporation"));
		Manufacturers.add(new Manufacturer("Subaru", "Subaru"));
		Manufacturers.add(new Manufacturer("Honda", "Honda Motor Company, Ltd."));
		Manufacturers.add(new Manufacturer("Lexus", "Lexus"));
		Manufacturers.add(new Manufacturer("Mazda", "Mazda Motor Corporation "));
		Manufacturers.add(new Manufacturer("Mitsubishi", "Mitsubishi Motors Corporation"));
		Manufacturers.add(new Manufacturer("Infiniti", "Infiniti"));
		Manufacturers.add(new Manufacturer("Isuzu", "Isuzu Motors Ltd."));
		Manufacturers.add(new Manufacturer("Toyota", "Toyota Motor Corporation"));
		
		//UK
		Manufacturers.add(new Manufacturer("McLaren", "McLaren Automotive"));
		Manufacturers.add(new Manufacturer("Bentley", "Bentley Motors Limited"));
		Manufacturers.add(new Manufacturer("Jaguar", "Jaguar"));
		Manufacturers.add(new Manufacturer("Aston", "Aston Martin Lagonda"));
		Manufacturers.add(new Manufacturer("Rolls-Royce", "Rolls-Royce Motor Cars Limited"));
		Manufacturers.add(new Manufacturer("Mini", "Mini"));
		Manufacturers.add(new Manufacturer("Land Rover", "Land Rover"));
		Manufacturers.add(new Manufacturer("Lotus", "Lotus Cars Limited"));
		
		dao.initializeParentTables(countries, Manufacturers);
		
		//Create a car object.
		Car supra = new Car("Toyota", "Supra", 2021, "Silver", 382, 3.7, 155.0, 32, carType, transmission);
		Car viper = new Car("Dodge", "Viper", 2021, "Blue", 645, 3.0,  206.0, 19, carType, transmission);
				
		//insert it using CarDao.
		//int pk = dao.insert(viper);
		
		//boolean deleted = dao.delete(supra);
		
		//boolean updated = dao.update(supra, 1);
		
		//System.out.println(pk);
	}

}
