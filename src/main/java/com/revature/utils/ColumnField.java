package com.revature.utils;

import java.lang.reflect.Field;

import com.revature.annotations.Column;

public class ColumnField {
	
	//The field/property of a class.
	private Field field;

	public ColumnField(Field field) {
		
		//If the annotation above the field is not equal to @Column, then throw an Exception.
		if(field.getAnnotation(Column.class) == null) {
			throw new IllegalStateException("Cannot Create ColumnField object! Provided field with " + 
					 getName() + " is not Annotated with @Id");
		}
		
		this.field = field;
	}
	
	public String getName() {
		return field.getName();
	}
	
	public Class<?> getType(){
		return field.getType();
	}
	
	public String getColumnName() {
		return field.getAnnotation(Column.class).name();
	}
	
}
