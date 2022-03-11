package com.revature.utils;

import java.lang.reflect.Method;

import com.revature.annotations.Getter;
import com.revature.annotations.PrimaryKey;
import com.revature.annotations.Setter;

public class ColumnMethod {
	
	private Method method;
	
	public ColumnMethod(Method method) {
		if(method.getAnnotation(Getter.class) == null || method.getAnnotation(Setter.class) == null) {
			throw new IllegalStateException("Cannot Create method object! Provided method with " + 
		getName() + " is not Annotated with @Getter or @Setter.");
		}
		
		this.method = method;
	}
	
	public String getName() {
		return method.getName();
	}

}
