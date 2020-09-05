package collegedatabaseapp;

import java.util.Scanner;

public class Principal {
	private String a;
	private String firstName;
	private String lastName;
	private String princId;
	private String princpass;
	
	
	public void input_data() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter first name");
		this.firstName = in.nextLine();
		
		System.out.print("Enter last name");
		this.lastName = in.nextLine();
		
		System.out.print("Enter id");
		this.princId = in.nextLine();
		
		System.out.print("Enter password");
		this.princpass = in.nextLine();
	}
	
	public String getId() {
		return this.princId;
	}
	
	public String getpass() {
		return this.princpass;
	}
	
	public void displayname() {
		System.out.println("Hello " + this.firstName + " " + this.lastName);
	}
}

