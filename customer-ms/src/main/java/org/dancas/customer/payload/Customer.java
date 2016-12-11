package org.dancas.customer.payload;

import org.springframework.data.annotation.Id;

public class Customer{

	@Id
	private String id;
	private String name;
	private String surname;
	private String age;
	
	public Customer(){
	}
		
	public String getId() {
		return id;
	}
	public Customer setId(String id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public Customer setName(String name) {
		this.name = name;
		return this;
	}
	public String getSurname() {
		return surname;
	}
	public Customer setSurname(String surname) {
		this.surname = surname;
		return this;
	}
	public String getAge() {
		return age;
	}
	public Customer setAge(String age) {
		this.age = age;
		return this;
	}
	
}
