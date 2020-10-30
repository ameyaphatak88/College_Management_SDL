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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;



public class StudentSection
{
	StudentFrame obj = new StudentFrame();
}

class STudentC
{
	String fname;
	String lname;
	String sid;
	String spassw;
}


class StudentFrame extends JFrame implements ActionListener
{
	JButton b1,b2,b3,b4;	
	
	  public StudentFrame()
	   {

	     setLayout(new FlowLayout());

	     b1=new JButton("SignUp");
	     b2=new JButton("Login");
	     b3=new JButton("Chat");
	     b4=new JButton("Exit");

	     add(b1);
	     add(b2);
	     add(b3);
	     add(b4);
	     
	     b1.addActionListener(this);
	     b2.addActionListener(this);
	     b3.addActionListener(this);
	     b4.addActionListener(this);

	     setVisible(true);
	     setSize(350,700);
	     setTitle("Student Section");

	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  }
	  
	  public void actionPerformed(ActionEvent ae)
	  {
		  if(ae.getSource()==b1)
		  {
			  StudentSignUpFrame obj2 = new StudentSignUpFrame();
		  }
		  if(ae.getSource()==b2)
		  {
			  StudentLoginFrame obj3 = new StudentLoginFrame();
		  }
		  if(ae.getSource()==b3)
		  {
			  
		  }
		  if(ae.getSource()==b4)
		  {
			  
		  }
	  }	  
}

class StudentSignUpFrame extends JFrame implements ActionListener
{
	JButton b1;
	JLabel l1,l2,l3,l4,l5,l6;
	JTextField t1,t2,t3,t4,t5,t6;
	
	public StudentSignUpFrame()
	{	
		setLayout(new FlowLayout());
		
		l1=new JLabel("First Name:");
		t1=new JTextField(); 
		t1.setColumns(20);
		
		l2=new JLabel("Last Name:");
		t2=new JTextField(); 
		t2.setColumns(20);
		
		l3=new JLabel("Student ID:");
		t3=new JTextField(); 
		t3.setColumns(20);
		
		l4=new JLabel("Password:");
		t4=new JTextField(); 
		t4.setColumns(20);
		
		l5=new JLabel("Year:");
		t5=new JTextField(); 
		t5.setColumns(20);
		
		l6=new JLabel("Division:");
		t6=new JTextField(); 
		t6.setColumns(20);
		
		b1 = new JButton("Sign Up");
		
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(l4);
		add(t4);
		add(l5);
		add(t5);
		add(l6);
		add(t6);
		add(b1);
		
		
	    t1.addActionListener(this);
	    t2.addActionListener(this);
	    t3.addActionListener(this);
	    t4.addActionListener(this);
	    t5.addActionListener(this);
	    t6.addActionListener(this);
	    b1.addActionListener(this);
		
		
		setVisible(true);
		setSize(250,700);
		setTitle("Student SignUp Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdldatabase","ostechnix","Password123#@!");
			
			String p1 = t1.getText();
			String p2 = t2.getText();
			String p3 = t3.getText();
			String p4 = t4.getText();
			String p5 = t5.getText();
			String p6 = t6.getText();
			
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
		catch(Exception e){
			
		}
		dispose();
	}
}


class StudentLoginFrame extends JFrame implements ActionListener
{
	JButton b1,b4;
	JLabel l1,l2,l3,l4;
	JTextField t1,t2,t3;
	
	public StudentLoginFrame()
	{
		setLayout(new FlowLayout());
		
		l1=new JLabel("Enter id:");
		t1=new JTextField(); 
		t1.setColumns(20);
		
		l2=new JLabel("Enter password");
		t2=new JTextField(); 
		t2.setColumns(20);
		
		b1 = new JButton("Sign Up");
		b4 = new JButton("Receive Assignment");
		
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(b1);
		
		t1.addActionListener(this);
		t2.addActionListener(this);
		b1.addActionListener(this);
		
		setVisible(true);
		setSize(250,700);
		setTitle("Student Login Page");
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		System.out.println("Hey!");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdldatabase","ostechnix","Password123#@!");
			String id,passw;
			id = t1.getText();
			passw = t2.getText();
			
			String queryCheck = "SELECT * from students WHERE studentId= '" + id + "'";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(queryCheck);
			int flag;
			
			char inner_flag = 'q';
			
			if(rs.absolute(1)) {
				System.out.println("Exists");
				flag = 1;
				String delsql = "delete from curr_id_pass";
				st.executeUpdate(delsql);
				String insql = "insert into curr_id_pass values('" + id + "','" + passw + "')";
				st.executeUpdate(insql);
				
				SepStudentFrame ssf = new SepStudentFrame();
			}
			else {
				System.out.println("Not exists");
				flag = 0;
			}
		}
		catch(Exception e)
		{
			System.out.println("In catch");
		}
		
	}
}

class SepStudentFrame extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b1,b2,b3,b4;
	String sid,spass,sfname,slname,sdiv,syear;
	
	public SepStudentFrame()
	{
		setLayout(new FlowLayout());
		
		l1=new JLabel("Welcome!",SwingConstants.CENTER);
		b1 = new JButton("Subjects");
		b2 = new JButton("Marks Distributuion");
		b3 = new JButton("Chat");
		b4 = new JButton("Receive Assignment");
		
		add(l1);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		
		setVisible(true);
		setSize(200,700);
		setTitle("Student page");

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdldatabase","ostechnix","Password123#@!");
			
			String selsql = "select * from curr_student";
			PreparedStatement pst = con.prepareStatement(selsql);
			ResultSet rsr = pst.executeQuery(selsql);
			ResultSetMetaData rsmd = rsr.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rsr.next())
			{
				/*for(int i = 1; i <= columnsNumber; i++)
				{
					String columnValue = rsr.getString(i);
			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
					
			        
				}*/
				sfname = rsr.getString(1);
				slname = rsr.getString(2);
				sid = rsr.getString(3);
				syear = rsr.getString(4);
				sdiv = rsr.getString(5);
				spass = rsr.getString(6);
			}
			System.out.println(sfname + " " + slname + " " + sid + " " + syear + " " + sdiv + " " + spass);
			
		}
		catch(Exception e)
		{
			
		}
		
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
	}
	
}

class StudentNotFound extends JFrame
{
	JLabel l1,l2;
	
	public StudentNotFound()
	{
		setLayout(new FlowLayout());
		
		l1=new JLabel("Student not found!");
		l2=new JLabel("Invalid id or password!");
		
		add(l1);
		add(l2);
		
		setVisible(true);
		setSize(300,100);
		setTitle("Student Not found");
	}
}



























