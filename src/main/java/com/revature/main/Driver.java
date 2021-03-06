package com.revature.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.dao.CarDao;
import com.revature.dao.ModelDao;
import com.revature.models.Car;
import com.revature.models.Car.CarType;
import com.revature.models.Car.TransmissionType;
import com.revature.models.Country;
import com.revature.models.Manufacturer;
import com.revature.models.Model;

public class Driver {

	public static void main(String[] args) {
		
		CarDao carDao = new CarDao();
		ModelDao modelDao = new ModelDao();

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
		
		ArrayList<Manufacturer> manufacturers = new ArrayList<>();
		//USA
		manufacturers.add(new Manufacturer("Dodge", "Dodge", 1));
		manufacturers.add(new Manufacturer("Ford", "Ford", 1));
		manufacturers.add(new Manufacturer("Chevy", "Chevrolet", 1));
		manufacturers.add(new Manufacturer("GM", "General Moters", 1));
		manufacturers.add(new Manufacturer("BMW", "Bayerische Motoren Werke AG", 1));
		
		//Germany
		manufacturers.add(new Manufacturer("Mercedes", "Mercedes-Benz", 2));
		manufacturers.add(new Manufacturer("VW", "Volkswagen", 2));
		manufacturers.add(new Manufacturer("Audi", "Audi", 2));
		manufacturers.add(new Manufacturer("Porsche", "Dr.-Ing. h.c. F. Porsche AG", 2));
		
		//Italy
		manufacturers.add(new Manufacturer("FIAT", "Fiat Automobiles S.p.A.", 3));
		manufacturers.add(new Manufacturer("Lamborghini", "Automobili Lamborghini S.p.A.", 3));
		manufacturers.add(new Manufacturer("Alfa Romeo", "Alfa Romeo Automobiles S.p.A.", 3));
		manufacturers.add(new Manufacturer("Maserati", "Maserati S.p.A", 3));
		manufacturers.add(new Manufacturer("Ferrari", "Ferrari S.p.A.", 3));
		
		//Japan
		manufacturers.add(new Manufacturer("ACURA", "Acura", 4));
		manufacturers.add(new Manufacturer("Nissan", "Nissan Motor Corporation", 4));
		manufacturers.add(new Manufacturer("Subaru", "Subaru", 4));
		manufacturers.add(new Manufacturer("Honda", "Honda Motor Company, Ltd.", 4));
		manufacturers.add(new Manufacturer("Lexus", "Lexus", 4));
		manufacturers.add(new Manufacturer("Mazda", "Mazda Motor Corporation ", 4));
		manufacturers.add(new Manufacturer("Mitsubishi", "Mitsubishi Motors Corporation", 4));
		manufacturers.add(new Manufacturer("Infiniti", "Infiniti", 4));
		manufacturers.add(new Manufacturer("Isuzu", "Isuzu Motors Ltd.", 4));
		manufacturers.add(new Manufacturer("Toyota", "Toyota Motor Corporation", 4));
		
		//UK
		manufacturers.add(new Manufacturer("McLaren", "McLaren Automotive", 5));
		manufacturers.add(new Manufacturer("Bentley", "Bentley Motors Limited", 5));
		manufacturers.add(new Manufacturer("Jaguar", "Jaguar", 5));
		manufacturers.add(new Manufacturer("Aston", "Aston Martin Lagonda", 5));
		manufacturers.add(new Manufacturer("Rolls-Royce", "Rolls-Royce Motor Cars Limited", 5));
		manufacturers.add(new Manufacturer("Mini", "Mini", 5));
		manufacturers.add(new Manufacturer("Land Rover", "Land Rover", 5));
		manufacturers.add(new Manufacturer("Lotus", "Lotus Cars Limited", 5));
		
		carDao.initializeParentTables(countries, manufacturers);
		
		int manufacturerId = 0;
		for(Manufacturer manufacturer: manufacturers) {
			if(manufacturer.getName().equals("Toyota")){
				manufacturerId = manufacturers.indexOf(manufacturer) + 1;
			}
		}
		Model model = new Model("Supra", "2021", "Japan", manufacturerId);
		int modelPrimaryKey = modelDao.insert(model);

		//Create a car object.
		Car supra = new Car("Toyota", "Supra", "2021", "Silver", 382, 3.7, 155.0, 32, carType, transmission, modelPrimaryKey);
		Car viper = new Car("Dodge", "Viper", "1994", "blue", 645, 3.0, 206, 12, carType, transmission, modelPrimaryKey);

		//insert it using CarDao.
		int carPrimaryKey = carDao.insert(supra);
		
		Scanner scanner = new Scanner(System.in);
		
		int choice = scanner.nextInt();
		
		if(choice == 1) {	
//			modelDao.update(model, modelPrimaryKey);
//			carDao.update(viper, carPrimaryKey);
			
		  //carDao.delete(carPrimaryKey);
			
			//Car car = carDao.selectCar(2);
			
			for(Car car: carDao.SelectAll()) {
				System.out.println(car.toString());

			}
		}
				
	}

}
