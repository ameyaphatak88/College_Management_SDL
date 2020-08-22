package collegedatabaseapp;
import java.util.*;

public class Teacher {
	private String firstName;
	private String lastName;
	private String teacherId;
	private String teacherPass;
	private String ccof;
	public String message_student;
	public String notify_students;
	Hashtable<String, String> class_subjects_dist = new Hashtable<String, String>();
		
	public void input_data() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter first name");
		this.firstName = in.nextLine();
		
		System.out.print("Enter last name");
		this.lastName = in.nextLine();
		
		System.out.print("Enter teacher id");
		this.teacherId = in.nextLine();
		
		System.out.print("Enter teacher password");
		this.teacherPass = in.nextLine();
		
		System.out.print("Enter class teacher of which class");
		this.ccof = in.nextLine();
	}
	
	public String getclass() {
		return this.ccof;
	}
	
	public String getId() {
		return this.teacherId;
	}
	
	public String getPass() {
		return this.teacherPass;
	}
	
	public String getName() {
		return (this.firstName + " " + this.lastName);
	}
	
	

}

