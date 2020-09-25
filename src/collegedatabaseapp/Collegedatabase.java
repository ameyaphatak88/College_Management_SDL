package collegedatabaseapp;

import java.util.*;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


class Assignment implements Serializable{
	String prob_statement;
	String instructions;
	String lastDate;
	String marks;
}

//------------------------------------------------------------------------------------------------------------------------------------------------------------



class MultiThreadChatClient implements Runnable {

	  // The client socket
	  private static Socket clientSocket = null;
	  // The output stream
	  private static PrintStream os = null;
	  // The input stream
	  private static DataInputStream is = null;

	  private static BufferedReader inputLine = null;
	  private static boolean closed = false;
	  
	  public static void main(String[] args) {

	    // The default port.
	    int portNumber = 2222;
	    // The default host.
	    String host = "localhost";

	    if (args.length < 2) {
	      System.out
	          .println("Usage: java MultiThreadChatClient <host> <portNumber>\n"
	              + "Now using host=" + host + ", portNumber=" + portNumber);
	    } else {
	      host = args[0];
	      portNumber = Integer.valueOf(args[1]).intValue();
	    }

	    /*
	     * Open a socket on a given host and port. Open input and output streams.
	     */
	    try {
	      clientSocket = new Socket(host, portNumber);
	      inputLine = new BufferedReader(new InputStreamReader(System.in));
	      os = new PrintStream(clientSocket.getOutputStream());
	      is = new DataInputStream(clientSocket.getInputStream());
	    } catch (UnknownHostException e) {
	      System.err.println("Don't know about host " + host);
	    } catch (IOException e) {
	      System.err.println("Couldn't get I/O for the connection to the host "
	          + host);
	    }

	    /*
	     * If everything has been initialized then we want to write some data to the
	     * socket we have opened a connection to on the port portNumber.
	     */
	    if (clientSocket != null && os != null && is != null) {
	      try {

	        /* Create a thread to read from the server. */
	        new Thread(new MultiThreadChatClient()).start();
	        while (!closed) {
	          os.println(inputLine.readLine().trim());
	        }
	        /*
	         * Close the output stream, close the input stream, close the socket.
	         */
	        os.close();
	        is.close();
	        clientSocket.close();
	      } catch (IOException e) {
	        System.err.println("IOException:  " + e);
	      }
	    }
	  }

	  /*
	   * Create a thread to read from the server. (non-Javadoc)
	   * 
	   * @see java.lang.Runnable#run()
	   */
	  public void run() {
	    /*
	     * Keep on reading from the socket till we receive "Bye" from the
	     * server. Once we received that then we want to break.
	     */
	    String responseLine;
	    try {
	      while ((responseLine = is.readLine()) != null) {
	        System.out.println(responseLine);
	        if (responseLine.indexOf("*** Bye") != -1)
	          break;
	      }
	      closed = true;
	    } catch (IOException e) {
	      System.err.println("IOException:  " + e);
	    }
	  }
	}



//------------------------------------------------------------------------------------------------------------------------------------------------------------












//-------------------------------------------------------------------------------------------------------------------------------------------------------------
public class Collegedatabase {
	
	static ArrayList<Student> student_operations(ArrayList<Student> students) throws Exception {
		Scanner sc = new Scanner(System.in);		
		
		int outer_flag = 0;
		do {
			int opt;
			System.out.println("1.Student sign up ");
			System.out.println("2.Student login ");
			opt = sc.nextInt();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdldatabase","ostechnix","Password123#@!");
			
			if(opt == 1) {
				Student s1 = new Student();
				s1.input_data();
				
				String p1 = s1.getFirstName();
				String p2 = s1.getLastName();
				String p3 = s1.getId();
				String p4 = s1.getYear();
				String p5 = s1.getDiv();
				String p6 = s1.getPass();
				
				//Statement st=con.createStatement();
				PreparedStatement st=con.prepareStatement("insert into students values(?,?,?,?,?,?)");
				st.setString(1,p1);
				st.setString(2,p2);
				st.setString(3,p3);
				st.setString(4,p4);
				st.setString(5,p5);
				st.setString(6,p6);
				
				int i=st.executeUpdate();
				st.close();


			}
			
			if(opt == 2) {
					char exit_acc;
					//String id = "ramram";
					System.out.println("Enter student id");
					String id = sc.nextLine();
					id = sc.nextLine();
					
					System.out.println("Enter password");
					String passw = sc.nextLine();
					
					int flag = 0;
					
			
					//int flag = 0;
					/*Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdldatabase","ostechnix","Password123#@!");*/
					String queryCheck = "SELECT * from students WHERE studentId= '" + id + "'";
					Statement st = con.createStatement();
					
					ResultSet rs = st.executeQuery(queryCheck);
					
					char inner_flag = 'q';
					
					if(rs.absolute(1)) {
						System.out.println("Exists");
						flag = 1;
					}
					else {
						System.out.println("Not exists");
						flag = 0;
					}
					
					System.out.println("FLAG : " + flag);
					
					if(flag == 1) {
						System.out.println("Hey!");
						
						
							System.out.println(rs);
							
							do {
								System.out.println("1.View all the subjects ");
								System.out.println("2.View the marks distribution ");
								System.out.println("3. Check if any message from teacher");
								System.out.println("4. Check if any notice from teacher");
								System.out.println("5. Chat with teacher");
								System.out.println("6. Receive the assignment from teachre who is connected");
								System.out.println("7. Chat with admin(principal)");
								System.out.println("8. Multi client chat");
								
								int inner_opt;
								inner_opt = sc.nextInt();
								
								
								if(inner_opt == 1) {
									if(rs.getString(4).equals("FE")) {
										System.out.println("BXE");
										System.out.println("PHYSICS");
										System.out.println("CHEMISTRY");
										System.out.println("MATHS");
									}
									
									if(rs.getString(4).equals("SE")) {
										System.out.println("DSA");
										System.out.println("OOP");
										System.out.println("DELD");
										System.out.println("DM");
									}
									
									if(rs.getString(4).equals("TE")) {
										System.out.println("SDL");
										System.out.println("DBMS");
										System.out.println("CN");
										System.out.println("TOC");
									}
									
									if(rs.getString(4).equals("BE")) {
										System.out.println("WEB DEVELOPMENT");
										System.out.println("AI");
										System.out.println("DATA ANALYSIS");
										System.out.println("DEEP LEARNING");
									}
								}
								
								if(inner_opt == 2) {
									if(rs.getString(4).equals("FE")) {
										System.out.println("50 : ONLINE");
										System.out.println("50 : WRITTEN");
										System.out.println("25 : TERMWORK");
									}
									
									if(rs.getString(4).equals("SE")) {
										System.out.println("50 : PRACTICALS");
										System.out.println("50 : ONLINE");
										System.out.println("50 : WRITTEN");
										System.out.println("25 : TERM WORK");
									}
									
									if(rs.getString(4).equals("TE")) {
										System.out.println("30 : MIDSEM");
										System.out.println("70 : ENDSEM");
										System.out.println("50 : PRACTICAL");
										System.out.println("25 : TERMWORK");
									}
									
									if(rs.getString(4).equals("BE")) {
										System.out.println("50 : PROJECTT");
										System.out.println("50 : WRITTEN");
									}
								}
								
								if(inner_opt == 3) {
									System.out.println("In 3");
								}
								
								if(inner_opt == 4) {
									System.out.println("In 4");
								}
								
								if(inner_opt == 5) {
									try{
										Socket s = new Socket("localhost",1201);
										DataInputStream din = new DataInputStream(s.getInputStream());
										DataOutputStream dout = new DataOutputStream(s.getOutputStream());

										BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

										String msgin = "", msgout = "";

										while(!msgin.equals("end")){
											msgout = br.readLine();
											dout.writeUTF(msgout);
											msgin = din.readUTF();
											System.out.println("Teacher : " + msgin);
										}
										s.close();			

									}catch(Exception e){
										System.out.println("Exception");
									}
								}
								
								
								if(inner_opt == 6) {
									try {
										ServerSocket ss = new ServerSocket(7000);
								        //System.out.println("ServerSocket awaiting connections...");
								        Socket socket = ss.accept();
								        //System.out.println("Connection from " + socket);

								        //Deserialization
								        InputStream is = socket.getInputStream();
								        ObjectInputStream ois = new ObjectInputStream(is);
								        Assignment obj1=(Assignment)ois.readObject();
								        
								        System.out.println("Values received from Client are:-");
								        System.out.println("Problem Statement : "+obj1.prob_statement);
								        System.out.println("Instructions : "+obj1.instructions);
								        System.out.println("Last date : "+obj1.lastDate);
								        System.out.println("Marks : "+obj1.marks);

								        System.out.println("Closing sockets.");
								        ss.close();
								        socket.close();
									}catch(Exception e) {
										
									}
									
								}
								
								
								if(inner_opt == 8) {
									MultiThreadChatClient p = new MultiThreadChatClient();
									Thread t1 = new Thread(p);
									t1.start();
								}
								
								System.out.println("Exit from account ?");
								inner_flag = sc.next().charAt(0);
								
								
							}while(inner_flag != 'Y');
							
					}
					
					if(flag == 0) {
						System.out.println("Invalid ID or Password");
						exit_acc = 'Y';
					}
					
					con.close();
					st.close();
					rs.close();
					
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
	
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	
	
	
	static ArrayList<Teacher> teacher_operations(ArrayList<Teacher> teachers, ArrayList<Student> students) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int outer_flag = 0;
		do {
			int opt;
			System.out.println("1.Teacher sign up");
			System.out.println("2.Teacher log in");
			opt = sc.nextInt();
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdldatabase","ostechnix","Password123#@!");
			
			if(opt == 1) {
				Teacher t1 = new Teacher();
				t1.input_data();
				
				String p1 = t1.getFirstName();
				String p2 = t1.getLastName();
				String p3 = t1.getId();
				String p4 = t1.getPass();
				String p5 = t1.getCC();
				
				PreparedStatement st=conn.prepareStatement("insert into teachers values(?,?,?,?,?)");
				st.setString(1,p1);
				st.setString(2,p2);
				st.setString(3,p3);
				st.setString(4,p4);
				st.setString(5,p5);
				
				int i = st.executeUpdate();
				st.close();
			}
			
			if(opt == 2) {
				System.out.println("Enter teacher id");
				String id = sc.nextLine();
				id = sc.nextLine();
				System.out.println("Enter teacher password");
				String passw = sc.nextLine();
				
				int flag = 0;
				
				String queryCheck = "SELECT * from teachers WHERE teacherId= '" + id + "'";
				Statement st = conn.createStatement();
				
				ResultSet rs = st.executeQuery(queryCheck);
				
				
				
				if(rs.absolute(1)) {
					System.out.println("Exists");
					flag = 1;
				}
				else {
					System.out.println("Not exists");
					flag = 0;
				}
				
				if(flag == 1) {
					char inner_flag = 'q';
					
					do {
						System.out.println("1. View all the students of your class");
						System.out.println("2. View your teaching timetable as assigned by principal");
						System.out.println("3. Send a message to a student");
						System.out.println("4. Send a message to all students of the class");
						System.out.println("5. Chat with student");
						System.out.println("6. Assign a student(who is connected to socket) an assignment");
						System.out.println("7. Multi client chat");
						
						int inner_opt;
						inner_opt = sc.nextInt();
						
						if(inner_opt == 5) {
							try{
								ServerSocket ss = new ServerSocket(1201);
								Socket s = ss.accept();

								System.out.println("Connencted");

								DataInputStream din = new DataInputStream(s.getInputStream());
								DataOutputStream dout = new DataOutputStream(s.getOutputStream());

								BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

								String msgin = "", msgout = "";

								while(!msgin.equals("end")){
									msgin = din.readUTF();
									System.out.println("Student : " + msgin);
									msgout = br.readLine();
									dout.writeUTF(msgout);
									dout.flush();
								}
								s.close();
							}catch(Exception e){
								System.out.println("Exception");
							}
							
						}
						
						if(inner_opt == 6) {
							try {
								Assignment obj=new Assignment();
						        obj.prob_statement = "Implement a SDL Project using Java";
						        obj.instructions = "Implement using atleast 4 Java advance data structures. Use serialization, multithreading, sockets, JDBC.";
						        obj.lastDate = "1 October 2020";
						        obj.marks = "50";
						      
						        Socket socket = new Socket("localhost", 7000);
						        System.out.println("Connected");

						        //Serialization
						        OutputStream os = socket.getOutputStream();
						        ObjectOutputStream oos = new ObjectOutputStream(os);
						        System.out.println("Sending values to the ServerSocket");
						        oos.writeObject(obj);

						        System.out.println("Closing socket and terminating program.");
						        socket.close();
							}catch(Exception e) {
								
							}
							
						}
						
						if(inner_opt == 8) {
							//MultiThreadChatServer m = new MultiThreadChatServer();
						}
						
						
						
						System.out.println("Exit from account ?");
						inner_flag = sc.next().charAt(0);
						
						
					}while(inner_flag != 'Y');
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
	
	static Principal principal_operations(Principal p,ArrayList<Teacher> teachers, ArrayList<Student> students){
		Scanner sc = new Scanner(System.in);
		
		int outer_flag = 0;
		do {
			int opt;
			System.out.println("1. Sign up");
			System.out.println("2. Log in");
			opt = sc.nextInt();
			
			if(opt == 1) {
				p.input_data();
				//outer_flag = 1;
			}
			
			if(opt == 2) {
				String id;
				String pass;
				System.out.println("Enter id");
				id = sc.nextLine();
				id = sc.nextLine();
				System.out.println("Enter password");
				pass = sc.nextLine();		
				
				
				if(id.contentEquals(p.getId()) && pass.contentEquals(p.getpass())){
					p.displayname();
					int inner_opt;
					
					System.out.println("1. Display all students");
					System.out.println("2. Display all teachers");
					System.out.println("3. See details of a particular student");
					System.out.println("4. See details of a particular teacher");
					inner_opt = sc.nextInt();
					
					if(inner_opt == 1) {
						for(int i = 0; i < students.size(); i++) {
							System.out.println(students.get(i).getName() + " " + students.get(i).getDiv());
						}
					}
					if(inner_opt == 2) {
						for(int i = 0; i < teachers.size(); i++) {
							System.out.println(teachers.get(i).getName());
						}
					}
					if(inner_opt == 3) {
						String sid;
						System.out.println("Enter student id");
						sid = sc.nextLine();
						for(int i = 0; i < students.size(); i++) {
							if(sid.contentEquals(students.get(i).getId())) {
								students.get(i);
							}
						}
					}
				}
				else {
					System.out.println("Invalid id or password!");
				}
				//outer_flag = 1;
			}
			
			System.out.println("Exit from principal section ?");
			char ch;
			ch = sc.next().charAt(0);
			if(ch == 'Y') {
				outer_flag = 1;
			}
			
			
		}while(outer_flag == 0);
		return p;
		
	}
		
	
	public static void main(String[] args) throws Exception {
		

		Scanner sc = new Scanner(System.in);

		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		Principal p = new Principal();
		char ch;
		
		do {
			System.out.println("Which user ?");
			System.out.println("1. Student");
			System.out.println("2. Teacher");
			System.out.println("3. Principal");
			
			int user_opt;
			user_opt = sc.nextInt();
			System.out.println(user_opt);
			
			if(user_opt == 1) {
				students = student_operations(students);
			}
			if(user_opt == 2) {
				teachers = teacher_operations(teachers,students);
			}
			if(user_opt == 3) {
				p = principal_operations(p, teachers, students);
			}
			
			System.out.println("Exit from system ?");
			ch = sc.next().charAt(0);
			System.out.println();
		}while(ch !='Y');
		
		//Principal p = new Principal();
		//p = principal_operations(p, teachers, students);
		
		System.out.println("Thank you for using the college management app !!");
		
		
		
	}
}

