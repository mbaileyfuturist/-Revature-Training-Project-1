package com.revature.utils;

import java.lang.reflect.Field;

import com.revature.annotations.Column;
import com.revature.annotations.Table;

public class DefineTable {
	
	//The field/property of a class.
	private Class clazz;

	public DefineTable(Class clazz) {
		
		//If the annotation above the field is not equal to @Column, then throw an Exception.
		if(clazz.getAnnotation(Table.class) == null) {
			throw new IllegalStateException("Cannot Create class object! Provided class with " + 
					 getSimpleName() + " is not Annotated with @Table");
		}
		
		this.clazz = clazz;
	}
	
	public String getSimpleName() {
		return clazz.getSimpleName();
	}
	
	//Need to get the custom className from the annotation.
//	public String getTableName() {
//		return clazz.getAnnotation(Table.class);
//	}
	
	public Field[] getFields() {
		return clazz.getDeclaredFields();
	}

}
