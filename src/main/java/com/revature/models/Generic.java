package com.revature.models;

public class Generic<T> {
	
	T object;

	public Generic(T object) {
		super();
		this.object = object;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
	

}
