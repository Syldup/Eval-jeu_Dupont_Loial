package bo;

import java.io.Serializable;

public class Person implements Serializable {
	
	private int id = -1;
	private String name;
	private String email;
	private double salary;
	
	public Person() { }
	
	public Person( String name, String email, double salary ) {
		this.name = name;
		this.email = email;
		this.salary = salary;
	}
	
	public Person( int id, String name, String email, double salary ) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.salary = salary;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId( int id ) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail( String email ) {
		this.email = email;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary( double salary ) {
		this.salary = salary;
	}
}
