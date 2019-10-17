package model;

import bo.Person;

import javax.servlet.http.HttpServletRequest;

public class PersonBean {
	
	private Person person;
	
	public PersonBean() {}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson( Person person ) {
		this.person = person;
	}
	
	public void setPersonFromForm( HttpServletRequest request ) {
		
		int id;
		try {
			id = Integer.parseInt( request.getParameter( "form-id" ) );
		} catch ( Exception e ) {
			id = -1;
		}
		String name = request.getParameter( "form-name" );
		String email = request.getParameter( "form-email" );
		double salary;
		try {
			salary = Double.parseDouble( request.getParameter( "form-salary" ) );
		} catch ( Exception e ) {
			salary = 0;
		}
		
		person = new Person( id, name, email, salary );
	}
}
