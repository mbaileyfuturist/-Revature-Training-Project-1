package com.revature.orm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.revature.models.Car;
import com.revature.utils.ColumnField;
import com.revature.utils.Configuration;
import com.revature.utils.DefineTable;

public class CarORM {
	
	private Configuration config = new Configuration("postgres", "Mikespasword123$");
	
	//Establish a connection to the database.
	private Connection connection = config.connectToDataBase();
	
	public HashMap<String, Class<?>>  createIfNotExist(Car car) {
		
		//Get the singleton class of our object.
		Class carClass = Car.class;		
		
		//(Figure out how to get the custom annotation name.)
		//Define our table based on the @Table annotation in the class.
		DefineTable table = new DefineTable(carClass);
		
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
			
			if(columnField.getType().toString().equals("class java.lang.String")) {
				typeAndRestrictions = "VARCHAR(50) NOT NULL";
			}else if(columnField.getType().toString().equals("int") && !columnField.getColumnName().equals("id")) {
				typeAndRestrictions = "INTEGER NOT NULL";
			}else if(columnField.getType().toString().equals("double")) {
				typeAndRestrictions = "NUMERIC(5,2) NOT NULL";
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
		
		
		//System.out.println(columnDecleration);
		
		return fieldToType;
		
	}
	
	
	public int save(Car car){
		
		//If the table doesn't exist then create a new one and return a map
		//That maps elements from their column name to their data type.
		HashMap<String, Class<?>> fieldToType = createIfNotExist(car);
		
		//Create a singleton class of the Car object.
		Class carClass = car.getClass();
		
		//(Figure out how to get the custom annotation name.)
		DefineTable table = new DefineTable(carClass);

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
				if(hmIterator.hasNext()) {
					sqlStatementBuilder.append(", ");
				}
			}
			
		}
		
		sqlStatementBuilder.append(") VALUES (");
		
		
		for(int index = 0; index < carClass.getDeclaredFields().length-1; index++) {
			sqlStatementBuilder.append("?");
			
			if(index < carClass.getDeclaredFields().length - 2) {
				sqlStatementBuilder.append(", ");
			}
		}
		
		sqlStatementBuilder.append(") RETURNING id");
		
		System.out.println(sqlStatementBuilder.toString());
						
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatementBuilder.toString());
			
			preparedStatement.setDouble(1, car.getZeroToSixty());
			preparedStatement.setDouble(2, car.getHorsePower());
			preparedStatement.setString(3, car.getColor());
			preparedStatement.setDouble(4, car.getMpg());
			preparedStatement.setInt(5, car.getYear());
			preparedStatement.setString(6, car.getModel());
			preparedStatement.setDouble(7, car.getTopspeed());
			preparedStatement.setString(8, car.getMake());
			
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

	public boolean remove(Car car) {
		
		boolean deleted = false;
		
		Class carClass = car.getClass();
		
		try {
			Statement statement = connection.createStatement();
						
			String deleteStatement = "DELETE FROM " + carClass.getSimpleName() + " WHERE model='" + car.getModel() + "'";
			
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
			preparedStatement.setString(3, car.getColor());


			System.out.println(updateStatement.toString());
			
			updated = true;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updated;
	}
}

