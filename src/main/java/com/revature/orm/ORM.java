package com.revature.orm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.revature.models.Car;
import com.revature.models.Car.CarType;
import com.revature.models.Car.TransmissionType;
import com.revature.models.Country;
import com.revature.models.Generic;
import com.revature.models.Manufacturer;
import com.revature.models.Model;
import com.revature.utils.ColumnField;
import com.revature.utils.Configuration;
import com.revature.utils.DefineTable;

public class ORM {
	
	private Configuration config = new Configuration("postgres", "Mikespasword123$");
	
	//Establish a connection to the database.
	private Connection connection = config.connectToDataBase();
	
	//Make this generic so that it can create the following tables Countries, Manufacturers, Cars.
	public HashMap<String, Class<?>>  createIfNotExist(Object object) {
		
		Generic<Object> genericObject = new Generic<Object>(object);
		
		//Get the singleton class of our object.
		Class objectClass = genericObject.getObject().getClass();		
		
		//(Figure out how to get the custom annotation name.)
		//Define our table based on the @Table annotation in the class.
		DefineTable table = new DefineTable(objectClass);
		
		//Get the fields of the class.
		Field[] fields = table.getFields();
		
		//Create a hashmap to map custom column names to their datatypes.
		HashMap<String, Class<?>> fieldToType = new HashMap<String, Class<?>>();
		
		//Create a StringBuilder to create an SQL statement that will create our table if it does'nt exist.
		StringBuilder createTable = new StringBuilder("CREATE TABLE IF NOT EXISTS " + table.getSimpleName() + "\n");
		String typeAndRestrictions = "";
		for(int index = 0; index < fields.length; index++) {
			
			//In case the class designer enters a different name for the column.
			ColumnField columnField = new ColumnField(fields[index]);
			
			fieldToType.put(columnField.getColumnName(), columnField.getType());
			
			if(columnField.getColumnName().equals("id")) {
				createTable.append( "(id SERIAL NOT NULL PRIMARY KEY");
			}
						
			System.out.println(columnField.getType());
			if(columnField.getType().toString().equals("class java.lang.String") || columnField.getType().toString().equals("class com.revature.models.Car$CarType") ||
					columnField.getType().toString().equals("class com.revature.models.Car$TransmissionType")) {
				
				typeAndRestrictions = "VARCHAR(50) NOT NULL";
				
			}else if(columnField.getType().toString().equals("int") && !columnField.getColumnName().equals("id") && !columnField.getColumnName().equals("country_id")
					&& !columnField.getColumnName().equals("manufacturer_id") && !columnField.getColumnName().equals("model_id")) {
				
				typeAndRestrictions = "INTEGER NOT NULL";
				
			}else if(columnField.getType().toString().equals("double")) {
				
				typeAndRestrictions = "NUMERIC(5,2) NOT NULL";
				
			}
			
			if(columnField.getColumnName().equals("country_id")) {
				typeAndRestrictions = "INTEGER REFERENCES Country(id)";
			}
			
			if(columnField.getColumnName().equals("manufacturer_id")) {
				typeAndRestrictions = "INTEGER REFERENCES manufacturer(id) ON DELETE CASCADE";
			}
			
			if(columnField.getColumnName().equals("model_id")) {
				typeAndRestrictions = "INTEGER REFERENCES model(id)";
			}
			
			if(!columnField.getColumnName().equals("id")) {
				createTable.append(columnField.getColumnName() + " " + typeAndRestrictions);
			}
						
			//If we have more columns to iterate then append a comma, otherwise don't.
			if(index < fields.length - 1) {
				createTable.append(", \n");
			}
			
		}
		
		createTable.append(");");
		
		try {
			Statement statement = connection.createStatement();
			
			statement.execute(createTable.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(createTable + "\n");
		
		return fieldToType;
		
	}
	
	public void saveCountriesAndManufacturers(ArrayList<Country> countries, ArrayList<Manufacturer> manufacturers) {
		
		for(Country country: countries) {
			save(country);
		}
		for(Manufacturer manufacturer: manufacturers) {
			save(manufacturer);
		}
	}
	
	public int save(Object object){
				
		//If the table doesn't exist then create a new one and return a map
		//That maps elements from their column name to their data type.
		HashMap<String, Class<?>> fieldToType = createIfNotExist(object);

		Generic<Object> genericObject = new Generic<Object>(object);
		
		//Create a singleton class of the Car object.
		Class objectClass = genericObject.getObject().getClass();
		
		//(Figure out how to get the custom annotation name.)
		DefineTable table = new DefineTable(objectClass);

		//Create a prepared statement using StringBuilder.
		StringBuilder sqlStatementBuilder = new StringBuilder("INSERT INTO " + table.getSimpleName() + " ( ");
		
		//Create an iterator to iterate through the map.
		Iterator hmIterator = fieldToType.entrySet().iterator(); 
		
		//Insert into (custom column names)
		while(hmIterator.hasNext()) {
			
			//The mapElement contains the key and value (columnName -> dataType)
			Map.Entry mapElement = (Map.Entry)hmIterator.next();
			
			if(!mapElement.getKey().equals("id")){
				sqlStatementBuilder.append(mapElement.getKey());
				//If there is a next element in the iterator then add a comma.
				if(hmIterator.hasNext() && !mapElement.getKey().equals("year_founded")) {
					sqlStatementBuilder.append(", ");
				}
			}
			
		}
		
		sqlStatementBuilder.append(") VALUES (");
		
		
		for(int index = 0; index < objectClass.getDeclaredFields().length-1; index++) {
			sqlStatementBuilder.append("?");
			
			if(index < objectClass.getDeclaredFields().length - 2) {
				sqlStatementBuilder.append(", ");
			}
		}
		
		sqlStatementBuilder.append(") RETURNING id");

		System.out.println(sqlStatementBuilder.toString());
							
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatementBuilder.toString());

			if(table.getSimpleName().equals("Country")) {
				preparedStatement.setString(1, ((Country) genericObject.getObject()).getCountry());
				preparedStatement.setInt(2, ((Country) genericObject.getObject()).getNumManufacturers());
				preparedStatement.setInt(3, ((Country) genericObject.getObject()).getTotalAnnual());
			}
			else if(table.getSimpleName().equals("Manufacturer")) {
				preparedStatement.setString(1, ((Manufacturer) genericObject.getObject()).getName());
				preparedStatement.setString(2, ((Manufacturer) genericObject.getObject()).getFullName());
				preparedStatement.setInt(3, ((Manufacturer) genericObject.getObject()).getCountryId());
			}
			else if(table.getSimpleName().equals("Model")) {
				preparedStatement.setString(1, ((Model) genericObject.getObject()).getCountry());
				preparedStatement.setInt(2, ((Model) genericObject.getObject()).getMakerId());
				preparedStatement.setString(3, ((Model) genericObject.getObject()).getmodelName());
				preparedStatement.setString(4, ((Model) genericObject.getObject()).getYearFounded());
			}
			else if(table.getSimpleName().equals("Car")){
				preparedStatement.setDouble(1, ((Car) genericObject.getObject()).getZeroToSixty());
				preparedStatement.setDouble(2, ((Car) genericObject.getObject()).getHorsePower());
				preparedStatement.setString(3, ((Car) genericObject.getObject()).getTransmission().toString());
				preparedStatement.setString(4, ((Car) genericObject.getObject()).getColor());
				preparedStatement.setDouble(5, ((Car) genericObject.getObject()).getMpg());
				preparedStatement.setString(6, ((Car) genericObject.getObject()).getYear());
				preparedStatement.setDouble(7, ((Car) genericObject.getObject()).getTopspeed());
				preparedStatement.setString(8, ((Car) genericObject.getObject()).getModel());
				preparedStatement.setInt(9, ((Car) genericObject.getObject()).getModelId());
				preparedStatement.setString(10, ((Car) genericObject.getObject()).getMake());
				preparedStatement.setString(11, ((Car) genericObject.getObject()).getCarType().toString());
			}
			
			ResultSet resultSet;
			
			if ((resultSet = preparedStatement.executeQuery()) != null) {

				resultSet.next();			
				int id = resultSet.getInt("id");
				return id; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	public boolean remove(String keyWord, int primaryKey) {
		
		boolean deleted = false;
		
		Class objectClass = null;
		if(keyWord.equals("Car")) {
			objectClass = Car.class;

		}else if(keyWord.equals("Model")) {
			objectClass = Model.class;

		}
		
		try {
			Statement statement = connection.createStatement();
			
			String deleteStatement = "";
			if(objectClass.getSimpleName().equals("Car")) {
				deleteStatement = "DELETE FROM " + objectClass.getSimpleName() + " WHERE id='" + primaryKey + "'";
				deleted = true;
			}else if(objectClass.getSimpleName().equals("Model")){
				deleteStatement = "DELETE FROM " + objectClass.getSimpleName() + " WHERE id='" + primaryKey + "'";
				deleted = true;
			}
			
			statement.execute(deleteStatement);
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	public boolean change(String keyWord, Object object, int primaryKey) {
		
		boolean updated = false;
		
		Generic<Object> genericObject = new Generic<Object>(object);
		
		Class<? extends Object> objectClass = genericObject.getObject().getClass();
		
		try {
			
			StringBuilder updateStatement = new StringBuilder("UPDATE " + objectClass.getSimpleName() + " SET ");
			
			Field[] fields = objectClass.getDeclaredFields();
			
			for(int index = 0; index < fields.length; index++) {
				ColumnField columnField = new ColumnField(fields[index]);
				
				if(!columnField.getColumnName().equals("id")) {
					updateStatement.append(columnField.getColumnName() + " = ?");
					
					if(index < fields.length -1) {
						updateStatement.append(", ");
					}
				}
				
			}
			
			updateStatement.append(" WHERE id=" + primaryKey);
			
			System.out.println(updateStatement.toString());
			
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement.toString());
			
			if(objectClass.getSimpleName().equals("Car")) {
				preparedStatement.setString(1, ((Car) genericObject.getObject()).getMake());
				preparedStatement.setString(2, ((Car) genericObject.getObject()).getModel());
				preparedStatement.setString(3, ((Car) genericObject.getObject()).getYear());
				preparedStatement.setString(4, ((Car) genericObject.getObject()).getColor());
				preparedStatement.setInt(5, ((Car) genericObject.getObject()).getHorsePower());
				preparedStatement.setDouble(6, ((Car) genericObject.getObject()).getZeroToSixty());
				preparedStatement.setDouble(7, ((Car) genericObject.getObject()).getTopspeed());
				preparedStatement.setDouble(8, ((Car) genericObject.getObject()).getMpg());
				preparedStatement.setString(9, ((Car) genericObject.getObject()).getCarType().toString());
				preparedStatement.setString(10, ((Car) genericObject.getObject()).getTransmission().toString());
				preparedStatement.setInt(11, ((Car) genericObject.getObject()).getModelId());
			}else if(objectClass.getSimpleName().equals("Model")) {
				preparedStatement.setString(1, ((Model) genericObject.getObject()).getmodelName());
				preparedStatement.setString(2, ((Model) genericObject.getObject()).getYearFounded());
				preparedStatement.setString(3, ((Model) genericObject.getObject()).getCountry());
				preparedStatement.setInt(4, ((Model) genericObject.getObject()).getMakerId());
			}

			preparedStatement.executeUpdate();
			
			updated = true;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updated;
	}
	
	public Car select(int primaryKey) {
		
		Car car = new Car();
		String selectStatement = "SELECT * FROM Car WHERE id=" + primaryKey + "";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectStatement);
			
			if(rs.next()) {
				
				CarType carType = CarType.CONVERTIBLE;
				for(CarType ct: CarType.values()){
					if(carType.name().equals(rs.getString("car_type"))) {
						carType = ct;
					}
				}
				
				TransmissionType transmissionType = TransmissionType.AUTOMATIC;
				for(TransmissionType tt: TransmissionType.values()){
					if(carType.name().equals(rs.getString("transmission"))) {
						transmissionType = tt;
					}
				}
				
				
				car.setMake(rs.getString("make"));
				car.setModel(rs.getString("model"));
				car.setYear(rs.getString("manufacture_year"));
				car.setColor(rs.getString("color"));
				car.setHorsePower(rs.getInt("horse_power"));
				car.setZeroToSixty(rs.getDouble("acceleration"));
				car.setTopspeed( rs.getDouble("top_speed"));
				car.setMpg( rs.getDouble("mpg"));
				car.setCarType(carType);
				car.setTransmission(transmissionType);
				car.setModelId(rs.getInt("model_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return car;
	}
	
	public ArrayList<Car> selectAll(){
		
		ArrayList<Car> cars = new ArrayList<Car>();
		
		String selectAll = "SELECT * FROM Car";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectAll);
			
			while(rs.next()) {
				
				CarType carType = CarType.CONVERTIBLE;
				for(CarType ct: CarType.values()){
					if(carType.name().equals(rs.getString("car_type"))) {
						carType = ct;
					}
				}
				
				TransmissionType transmissionType = TransmissionType.AUTOMATIC;
				for(TransmissionType tt: TransmissionType.values()){
					if(carType.name().equals(rs.getString("transmission"))) {
						transmissionType = tt;
					}
				}
				
				cars.add(new Car(rs.getString("make"), rs.getString("model"), rs.getString("manufacture_year"), rs.getString("color"), 
						rs.getInt("horse_power"), rs.getDouble("acceleration"), rs.getDouble("top_speed"), rs.getDouble("mpg"), carType, transmissionType, rs.getInt("model_id")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cars;
	}
	
}

