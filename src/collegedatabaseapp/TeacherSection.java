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

import javax.swing.tree.DefaultMutableTreeNode;  

public class TeacherSection
{
	TeacherFrame tf = new TeacherFrame();
}

class TeacherFrame extends JFrame implements ActionListener
{
	JButton b1,b2,b3,b4,b5,b6;	
	
	  public TeacherFrame()
	   {

	     setLayout(new FlowLayout());

	     b1=new JButton("SignUp");
	     b2=new JButton("Login");
	     b3=new JButton("Chat");
	     b4=new JButton("View all the students");
	     b5=new JButton("Send assignments");
	     b6=new JButton("Exit");

	     add(b1);
	     add(b2);
	     add(b3);
	     add(b4);
	     add(b5);
	     add(b6);
	     
	     b1.addActionListener(this);
	     b2.addActionListener(this);
	     b3.addActionListener(this);
	     b4.addActionListener(this);
	     b5.addActionListener(this);
	     b6.addActionListener(this);

	     setVisible(true);
	     setSize(350,700);
	     setTitle("Teacher Section");

	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  }
	  
	  public void actionPerformed(ActionEvent ae)
	  {
		  if(ae.getSource()==b1)
		  {
			  TeacherSignUpFrame obj2 = new TeacherSignUpFrame();
		  }
		  if(ae.getSource()==b2)
		  {
			  //StudentLoginFrame obj3 = new StudentLoginFrame();
		  }
		  if(ae.getSource()==b3)
		  {
			  
		  }
		  if(ae.getSource()==b4)
		  {
			  
		  }
	  }	  
}



class TeacherSignUpFrame extends JFrame implements ActionListener
{
	JButton b1;
	JLabel l1,l2,l3,l4,l5;
	JTextField t1,t2,t3,t4,t5;
	
	public TeacherSignUpFrame()
	{	
		setLayout(new FlowLayout());
		
		l1=new JLabel("First Name:");
		t1=new JTextField(); 
		t1.setColumns(20);
		
		l2=new JLabel("Last Name:");
		t2=new JTextField(); 
		t2.setColumns(20);
		
		l3=new JLabel("Teacher ID:");
		t3=new JTextField(); 
		t3.setColumns(20);
		
		l4=new JLabel("Password:");
		t4=new JTextField(); 
		t4.setColumns(20);
		
		l5=new JLabel("CC of");
		t5=new JTextField(); 
		t5.setColumns(20);
		
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
		add(b1);
		
		
	    t1.addActionListener(this);
	    t2.addActionListener(this);
	    t3.addActionListener(this);
	    t4.addActionListener(this);
	    t5.addActionListener(this);
	    b1.addActionListener(this);
		
		
		setVisible(true);
		setSize(250,700);
		setTitle("Teacher SignUp Page");
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
			
			PreparedStatement st=con.prepareStatement("insert into teachers values(?,?,?,?,?)");
			st.setString(1,p1);
			st.setString(2,p2);
			st.setString(3,p3);
			st.setString(4,p4);
			st.setString(5,p5);
			//st.setString(6,p6);
			
			int i=st.executeUpdate();
			st.close();
		}
		catch(Exception e){
			
		}
		dispose();
	}
}














