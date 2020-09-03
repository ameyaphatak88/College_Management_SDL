package collegedatabaseapp;
import java.util.*;
import java.util.Scanner;
import java.util.*;

public class Student {
	private String firstName;
	private String lastName;
	private String studentId;
	private String year;
	private String division;
	private String studentPass;
	public String tmsg = " ";
	public String noticeT;
	Vector subjects = new Vector();
	Hashtable<String, Integer> marks_distribution = new Hashtable<String, Integer>();
	
	public String getId() {
		return this.studentId;
	}
	
	public String getName() {
		return (this.firstName + " " + this.lastName);
	}
	
	public String getNameId() {
		return (this.studentId + " " + this.firstName + " " + this.lastName);
	}
	
	public String getPass() {
		return this.studentPass;
	}
	
	public String getDiv() {
		return this.division;
	}
	
	public void dispTeachMess() {
		System.out.println("Treacher's message : " + this.teachers_message);
	}
	
	public void showNoticeT() {
		System.out.println("Notice from teacher : " + this.noticeT);
	}
	
	public void input_data() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter first name");
		this.firstName = in.nextLine();
		
		System.out.print("Enter last name");
		this.lastName = in.nextLine();
		
		System.out.print("Enter student id");
		this.studentId = in.nextLine();
		
		System.out.print("Enter password you want to set");
		this.studentPass = in.nextLine();
		
		System.out.print("Enter year");
		this.year = in.nextLine();
		
		System.out.print("Enter division");
		this.division = in.nextLine();
	}
	
	public void assignSubjects() {
		
		if(this.year.equals("FE")) {
			subjects.addElement("BXE");
			subjects.addElement("PHYSICS");
			subjects.addElement("CHEMISTRY");
			subjects.addElement("MATHS 1");
		}
		
		if(this.year.equals("SE")) {
			subjects.addElement("DSA");
			subjects.addElement("OOP");
			subjects.addElement("DELD");
			subjects.addElement("DM");
		}
		
		if(this.year.equals("TE")) {
			subjects.addElement("SDL");
			subjects.addElement("DBMS");
			subjects.addElement("CN");
			subjects.addElement("TOC");
		}
		
		if(this.year.equals("BE")) {
			subjects.addElement("WEB DEVELOPMENT");
			subjects.addElement("AI");
			subjects.addElement("DATA ANALYSIS");
			subjects.addElement("DEEP LEARNING");
		}
	}
	
	public void assignMarksDistribution() {
		if(this.year.equals("FE")) {
			marks_distribution.put("Online", 50);
			marks_distribution.put("Written", 50);
			marks_distribution.put("TermWork", 25);
		}
		
		if(this.year.equals("SE")) {
			marks_distribution.put("Online", 50);
			marks_distribution.put("Written", 50);
			marks_distribution.put("Practicals", 50);
			marks_distribution.put("TermWork", 25);
		}
		
		if(this.year.equals("TE")) {
			marks_distribution.put("Insem", 30);
			marks_distribution.put("Endsem", 70);
			marks_distribution.put("Practicals", 50);
			marks_distribution.put("TermWork", 25);
		}
		
		if(this.year.equals("BE")) {
			marks_distribution.put("Projects", 50);
			marks_distribution.put("Written", 50);
		}
	}
	
	public void display_student() {
		System.out.println("Name : " + this.firstName + " " + this.lastName);
		System.out.println("ID : " + this.studentId);
		System.out.println("Division : " + this.division);
		System.out.println("Year : " + this.year);
	}

	
	public void display_subjects() {
		Enumeration e;
		e = subjects.elements();
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}
	}
	
	public void displayMarksDistribution() {
		System.out.println(marks_distribution);
	}

}

