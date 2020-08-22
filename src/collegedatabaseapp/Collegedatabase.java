package collegedatabaseapp;

import java.util.*;
public class Collegedatabase {
	
	static ArrayList<Student> student_operations(ArrayList<Student> students) {
		Scanner sc = new Scanner(System.in);		
		
		int outer_flag = 0;
		
		do {
			int opt;
			System.out.println("1.Student sign up ");
			System.out.println("2.Student login ");
			opt = sc.nextInt();
			
			if(opt == 1) {
				Student s1 = new Student();
				s1.input_data();
				students.add(s1);
			}
			
			if(opt == 2) {
				//String id = "ramram";
				System.out.println("Enter student id");
				String id = sc.nextLine();
				id = sc.nextLine();
				
				System.out.println("Enter password");
				String passw = sc.nextLine();
				
				int flag = 0;
				for(int i = 0; i < students.size(); i++) {
					System.out.println(students.get(i).getId());
					if(id.equals(students.get(i).getId()) && passw.equals(students.get(i).getPass())) {
						flag = 1;
						String fullname = students.get(i).getName();
						System.out.println("Hello " + fullname);
						
						int inner_flag = 0;
						
						System.out.println("1.View all the subjects ");
						System.out.println("2.View the marks distribution ");
						System.out.println("3. Check if any message from teacher");
						do {
							int inner_opt;
							inner_opt = sc.nextInt();
							
							if(inner_opt == 1) {
								students.get(i).assignSubjects();
								students.get(i).display_subjects();
								inner_flag = 1;
							}
							
							if(inner_opt == 2) {
								students.get(i).assignMarksDistribution();
								students.get(i).displayMarksDistribution();
								inner_flag = 1;
							}
							
							if(inner_opt == 3) {
								System.out.println("Message from class coordinator of the class : " + students.get(i).tmsg);
							}
							
						}while(inner_flag == 0);
						
					}
				}

				
				if(flag == 0) {
					System.out.println("Invalid ID or Password");
				}
			}
			
			
			char ans;
			System.out.println("Exit from student section ? ");
			
			ans = sc.next().charAt(0);
			if(ans == 'Y') {
				outer_flag = 1;
			}
			
		}while(outer_flag == 0);	
		
		return students;
		
	}
	
	
	static ArrayList<Teacher> teacher_operations(ArrayList<Teacher> teachers, ArrayList<Student> students) {
		Scanner sc = new Scanner(System.in);
		
		int outer_flag = 0;
		do {
			int opt;
			System.out.println("1.Teacher sign up");
			System.out.println("2.Teacher log in");
			opt = sc.nextInt();
			
			if(opt == 1) {
				Teacher t1 = new Teacher();
				t1.input_data();
				teachers.add(t1);
			}
			
			if(opt == 2) {
				System.out.println("Enter teacher id");
				String id = sc.nextLine();
				id = sc.nextLine();
				System.out.println("Enter teacher password");
				String passw = sc.nextLine();
				
				int flag = 0;
				for(int i = 0; i < teachers.size(); i++) {
					if(id.equals(teachers.get(i).getId()) && passw.equals(teachers.get(i).getPass())) {
						flag = 1;
						String fullName = teachers.get(i).getName();
						System.out.println("Hello " + fullName);
						
						int inner_flag = 0;
						System.out.println("1. View all the students of your class");
						System.out.println("2. View your teaching timetable as assigned by principal");
						System.out.println("3. Send a message to a student");
						System.out.println("4. Send a message to all students of the class");
						
						do {
							int inner_opt;
							inner_opt = sc.nextInt();
							
							if(inner_opt == 1) {
								String teacherclass = teachers.get(i).getclass();
								System.out.println("Students in your class are");
								for(int j = 0; j < students.size(); j++) {
									if(teacherclass.contentEquals(students.get(j).getDiv())) {
										System.out.println(students.get(j).getName());
									}
								}
							}
							
							if(inner_opt == 3) {
								String messg;
								System.out.println("Enter student id");
								String stid = sc.nextLine();
								stid = sc.nextLine();
								int stfound = 0;
								for(int j = 0; j < students.size(); j++) {
									if(students.get(j).getId().contentEquals(stid)) {
										stfound = 1;
										System.out.println("Enter the message you want to send this student");
										messg = sc.nextLine();
										students.get(j).tmsg = messg;
									}
								}
								if(stfound == 0) {
									System.out.println("Student not found");
								}
							}
							
							inner_flag = 1;
							
						}while(inner_flag == 0);
					}
				}
				
				if(flag == 0) {
					System.out.println("Invalid id or password");
				}
				
			}
			
			System.out.println("Exit from teachers section?");
			char ch;
			ch = sc.next().charAt(0);
			if(ch == 'Y') {
				outer_flag = 1;
			}
			
			}while(outer_flag == 0);
		
		return teachers;
	}
		
	
	public static void main(String[] args) {

		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		students = student_operations(students);
		teachers = teacher_operations(teachers,students);
	}
}
