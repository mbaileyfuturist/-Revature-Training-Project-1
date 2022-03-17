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

	static CarDao carDao = new CarDao();
	static ModelDao modelDao = new ModelDao();
	
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
		
// 		int choice = scanner.nextInt();
		
// 		if(choice == 1) {	
// //			modelDao.update(model, modelPrimaryKey);
// //			carDao.update(viper, carPrimaryKey);
			
// 		  //carDao.delete(carPrimaryKey);
			
// 			//Car car = carDao.selectCar(2);
			
// 			for(Car car: carDao.SelectAll()) {
// 				System.out.println(car.toString());

				System.out.println("Hello, please enter your username: ");
				String username = scanner.next();
				System.out.println("Welcome, " + username + ", please input a command from the options below: ");
				
				while (true) {
					System.out.println("Press 1: Add New Entry");
					System.out.println("Press 2: Delete Entry");
					System.out.println("Press 3: Search for Entry");
					System.out.println("Press 4: Update an Entry");
					System.out.println("Press 5: View All Entries");
					System.out.println("Press 0: Exit App");
					
					int input = scanner.nextInt(); 
					
					switch (input) {
					case 0: 
						System.out.println(username + " is now logging out. Goodbye.");
						username = null;
						return;
					case 1:
						add();    //call save method from CarORM
						break;
					case 2:
						delete();  //call remove method from CarORM
						break;
					case 3:
						search();   //currently no functionality 
						break;
					case 4:
						update();   //call change method from CarORM
						break;
					case 5:
						findAll();  //call CarORM class? create instance of ORM
						break;
					}
							
				}
			}
		
				//insert it using CarDao.
				//int pk = dao.insert(viper);
				
				//boolean deleted = dao.delete(supra);
				
				//boolean updated = dao.update(supra, 1);
				
				//System.out.println(pk);
			



		public static void add() {
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Please input the following details for a new entry:");
			System.out.println("Manufacturer: ");
			String make = scan.next();
			int manufacturerId = 0;
			for(Manufacturer manufacturer: carDao.manufacturers) {
				if(manufacturer.getName().equals(make)){
					manufacturerId = manufacturers.indexOf(manufacturer) + 1;   //retrieve manufacturer ID from table via input String name
				}
			}
			System.out.println("Model: ");
			String model = scan.next();
			System.out.println("Country: ");
			String country = scan.next();
			System.out.println("Color: ");
			String color = scan.next();
			System.out.println("Manufacture year: ");
			String year = scan.next();
			System.out.println("Acceleration: ");
			double accel = scan.nextDouble();
			System.out.println("Transmission (ALL CAPS): "); // .toUpperCase
			String transmission = scan.next();
			TransmissionType transEnum = TransmissionType.valueOf(transmission);
			System.out.println("MPG: ");
			double mpg = scan.nextDouble();
			System.out.println("Car type (ALL CAPS): ");
			String carType = scan.next();
			CarType carTypeEnum = CarType.valueOf(carType);
			System.out.println("HorsePower: ");
			int horsePower = scan.nextInt();
			System.out.println("Top Speed: ");
			double topSpeed = scan.nextDouble();
			
			Model modelToInsert = new Model(model, year, country, manufacturerId);
			int modelPrimaryKey = modelDao.insert(modelToInsert);
			Car carToInsert = new Car(make, model, year, color, horsePower, accel,
					topSpeed, mpg, carTypeEnum, transEnum, modelPrimaryKey);
			carDao.insert(carToInsert); //add gathered parameters to insert statement (Conflict with dao and orm currently?)
		}

		public static void delete() {
			Scanner scan = new Scanner(System.in);
			Car c = new Car();
			System.out.println("Please enter the PrimaryKey of the car you'd like to remove from the database: ");
			int carToDelete = scan.nextInt();
			carDao.delete(carToDelete);       
		}

		public static void search() {
			// same as above, need find method findByCarName() findByCountry() findByColor() etc...
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter the PrimaryKey for the car you are searching for: ");
			int input = scan.nextInt();
			System.out.println("Printing info for selected car:");
			System.out.println(carDao.selectCar(input));
		}

		public static void update() {
			Scanner scan = new Scanner(System.in);
			Car c = new Car();
			System.out.println("Please enter the PrimaryKey of the car you'd like to remove from the database: ");
			int carToDelete = scan.nextInt();
			       //Need find method to isolate car object from table   (Use select all method to load table and get primary key)
			carDao.delete(carToDelete);
			System.out.println("Please input the following details for updated entry:");
			System.out.println("Manufacturer: ");
			String make = scan.next();
			int manufacturerId = 0;
			for(Manufacturer manufacturer: carDao.manufacturers) {
				if(manufacturer.getName().equals(make)){
					manufacturerId = manufacturers.indexOf(manufacturer) + 1;   //retrieve manufacturer ID from table via input String name
				}
			}
			System.out.println("Model: ");
			String model = scan.next();
			System.out.println("Country: ");
			String country = scan.next();
			System.out.println("Color: ");
			String color = scan.next();
			System.out.println("Manufacture year: ");
			String year = scan.next();
			System.out.println("Acceleration: ");
			double accel = scan.nextDouble();
			System.out.println("Transmission (ALL CAPS): "); // .toUpperCase
			String transmission = scan.next();
			TransmissionType transEnum = TransmissionType.valueOf(transmission);
			System.out.println("MPG: ");
			double mpg = scan.nextDouble();
			System.out.println("Car type (ALL CAPS): ");
			String carType = scan.next();
			CarType carTypeEnum = CarType.valueOf(carType);
			System.out.println("HorsePower: ");
			int horsePower = scan.nextInt();
			System.out.println("Top Speed: ");
			double topSpeed = scan.nextDouble();
			
			Model modelToInsert = new Model(model, year, country, manufacturerId);
			int modelPrimaryKey = modelDao.insert(modelToInsert);
			Car carToInsert = new Car(make, model, year, color, horsePower, accel,
					topSpeed, mpg, carTypeEnum, transEnum, modelPrimaryKey);
			carDao.insert(carToInsert); 
		}

		public static void findAll() {
			ArrayList<Car> allCars = carDao.SelectAll(); //using carDao
			 //Correct with fetchAll() 
			allCars.forEach(e -> System.out.println(e));
		}

			
		
				
	}


