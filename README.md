# The Best ORM

## Project Description
The best ORM is a project used to map a Java class to a Postgress SQL table in a database as well as perfomr operations directly on the database using custom anotations, the Java Reflection class and the Java StringBuilder Class. 

## Technologies Used

* Java - version 8.0  
* PostgreSQL - version 42.2.12  
* Apache commons - version 2.1  
* JUnit
  
## Usage  
  ### Annotating classes  
  All classes which represent objects in database must be annotated.
   - #### @Table(name = "table_name)  
      - Indicates that this class is associated with table 'table_name'  
   - #### @Column(name = "column_name)  
      - Indicates that the Annotated field is a column in the table with the name 'column_name'  
   - #### @Setter(name = "column_name")  
      - Indicates that the anotated method is a setter for 'column_name'.  
   - #### @Getter(name = "column_name")  
      - Indicates that the anotated method is a getter for 'column_name'.  
   - #### @PrimaryKey(name = "column_name") 
      - Indicates that the annotated field is the primary key for the table.
   - #### @SerialKey(name = "column_name") 
      - Indicates that the annotated field is a serial key.

  ## ORM Useage
  The ORM is used to map a class to a table in a database as well as perform operaitions directly on the database using the anotations listed above, the   reflection class and the StringBuilder class.
  - #### `public HashMap<String, Class<?>>  createIfNotExist(Object object)`  
     - This method is responsible for creating a table of the type Object if it does not exist in the database.
     - Returns a Hashman that maps the fields of the class to their coresponding datatypes.
     - The map returned is useful for inserting data into the appropriate columns in the save() method described below.  
  - #### `public void saveCountriesAndManufacturers(ArrayList<Country> countries, ArrayList<Manufacturer> manufacturers)`  
     - This method is responible for creating and populating both the Manufacturer and Country tables.
     - Population gets done by iterating through each ArrayList and calling the save(Object object) method for each object.
  - #### `public int save(Object object)`  
     - This method creates a table by calling the createIfNotExist(Object object).
     - This method also inserts data into the appropriate table using the HahMap that is returned from the createIfNotExist(Object object) method.
     - Returns the primaryKey where the new record was inserted.
  - #### `public boolean remove(String keyWord, int primaryKey)`  
     - This method removes a record from a table based on the primary key passed into the parameter.
     - Retuns a boolean that returns true or false if the operation was successful or not.
  - #### `public boolean change(String keyWord, Object object, int primaryKey) `  
     - Updates a record in a table with the object passed in parameter two at the given primary key passed in parameter three.
  - #### `public Car select(int primaryKey)`  
     -  Selects a record from the table at the primary key passed in the parameter.
  - #### `public ArrayList<Car> selectAll()`
     - This method is responsible for selecting all records in the table and returning it in the form of an ArrayList.  
  
  ## Dao Usage  
  The CarDao and ModelDao classes are direct mirrors of themselves with the exception that the ModelDao doesnt have select(int primaryKey) and selectAll() methods.
  - #### `public void initializeParentTables(ArrayList<Country> countries, ArrayList<Manufacturer> manufacturers)`
     - This method is responible for creating and populating both the Manufacturer and Country tables by calling the saveCountriesAndManufacturers(ArrayList<Country> countries, ArrayList<Manufacturer> manufacturers) method listed above.
  - #### `public int insert(Car car)`
     - This method is responsible for inserting a new record into the Car table and returning the primary key of that new record to the main driver.
     - This operation is performed by calling the ORM insert(Car car) method listed above.
  - #### `public boolean delete(int primaryKey)`
     - Removes a record from a table based on the primary key that is passed into the parameter.
     - This operation is performed by calling the ORM remove(String keyWord, int primaryKey) method.
  - #### `public boolean update(Car car, int primaryKey)`
     - Updates a record with the object that is passed in parameter one at the primary key passed in parameter two.
     - This operation is performed by calling the ORM change(String keyWord, Object object, int primaryKey) method listed above.
  - #### `public Car selectCar(int primaryKey)`
     - Selects a record at the primary key passed in the parameter.
  - #### `public ArrayList<Car> SelectAll()`
     - Selects all records in the database by calling the ORM selectAll() method listed above.

## License
This project uses the following license: [GNU Public License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
