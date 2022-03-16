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
				typeAndRestrictions = "INTEGER REFERENCES manufacturer(id)";
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
				preparedStatement.setInt(6, ((Car) genericObject.getObject()).getYear());
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
	
	public boolean remove(Object object) {
		
		boolean deleted = false;
		
		Generic<Object> genericObject = new Generic<Object>(object);
		
		Class objectClass = object.getClass();
		
		try {
			Statement statement = connection.createStatement();
			
			String deleteStatement = "";
			if(objectClass.getSimpleName().equals("Car")) {
				deleteStatement = "DELETE FROM " + objectClass.getSimpleName() + " WHERE model='" + ((Car) genericObject.getObject()).getModel() + "'";
			}else if(objectClass.getSimpleName().equals("Model")){
				deleteStatement = "DELETE FROM " + objectClass.getSimpleName() + " WHERE model_name='" + ((Model) genericObject.getObject()).getModelName() + "'";
			}
			
			statement.execute(deleteStatement);
			
			deleted = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	public boolean change(Car car, int primaryKey) {
		
		boolean updated = false;
		
		Class carClass = car.getClass();
		
		try {
			
			StringBuilder updateStatement = new StringBuilder("UPDATE " + carClass.getSimpleName() + " SET ");
			
			Field[] fields = carClass.getDeclaredFields();
			
			for(int index = 0; index < fields.length; index++) {
				//We use the colimnField object to extract the custom column
				//name from @Column annotation.
				ColumnField columnField = new ColumnField(fields[index]);
				
				if(!columnField.getColumnName().equals("id")) {
					updateStatement.append(columnField.getColumnName() + " = ?");
					
					if(index < fields.length -1) {
						updateStatement.append(", ");
					}
				}
				
			}
			
			updateStatement.append(" WHERE id=" + primaryKey);
			
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement.toString());
			
			preparedStatement.setString(1, car.getMake());
			preparedStatement.setString(2, car.getModel());
			preparedStatement.setInt(3, car.getYear());
			preparedStatement.setString(4, car.getColor());
			preparedStatement.setInt(5, car.getHorsePower());
			preparedStatement.setDouble(6, car.getZeroToSixty());
			preparedStatement.setDouble(7, car.getTopspeed());
			preparedStatement.setDouble(8, car.getMpg());
			preparedStatement.setString(9, car.getCarType().toString());
			preparedStatement.setString(10, car.getTransmission().toString());

			preparedStatement.executeUpdate();

			System.out.println(updateStatement.toString());
			
			updated = true;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updated;
	}
}

