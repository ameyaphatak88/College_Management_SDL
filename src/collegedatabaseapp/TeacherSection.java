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

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TeacherSection
{
	//TeacherFrame tf = new TeacherFrame();
	ShowTableFrame stf = new ShowTableFrame();
}

class ShowTableFrame extends JFrame
{ 
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	
	public ShowTableFrame()
	{
		String[] columnNames = {"First Name", "Last Name", "ID", "Year", "Div", "Password"};
		JFrame frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        
        JTable table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        //from = (String) c1.getSelectedItem();
        		
        String fname = "";
        String lname = "";
        String sid = "";
        String syear = "";
        String sdiv = "";
        String spass = "";
        
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdldatabase","ostechnix","Password123#@!");
			
			 pst = con.prepareStatement("select * from students");
			 rs = pst.executeQuery();
	         
			 int i = 0;

	         while (rs.next()) {
	                fname = rs.getString("firstName");
	                lname = rs.getString("lastName");
	                sid = rs.getString("studentId");
	                syear = rs.getString("year");
	                sdiv = rs.getString("division");
	                spass = rs.getString("studentPass");
	                
	                model.addRow(new Object[]{fname, lname, sid, syear, sdiv, spass});
	                i++;
	            }
	         
        }
        catch(Exception e)
        {
        	
        }
        
        frame1.add(scroll);

        frame1.setVisible(true);

        frame1.setSize(700, 500);
	}
}

class TeacherFrame extends JFrame implements ActionListener
{
	JButton b1,b2,b3,b4,b5,b6;	
	
	  public TeacherFrame()
	   {

	     setLayout(new FlowLayout());

	     b1=new JButton("SignUp");
	     b2=new JButton("Login");
	     b3=new JButton("Exit");

	     add(b1);
	     add(b2);
	     add(b3);     
	     
	     b1.addActionListener(this);
	     b2.addActionListener(this);
	     b3.addActionListener(this);

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
			  TeacherLoginFrame obj3 = new TeacherLoginFrame();
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



//-------------------------------------------------------------------------------------------------------
class TeacherLoginFrame extends JFrame implements ActionListener
{
	JButton b1,b4;
	JLabel l1,l2,l3,l4;
	JTextField t1,t2,t3;
	String sid,spass,sfname,slname,sdiv;
	
	public TeacherLoginFrame()
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
		setTitle("Teacher Login Page");
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
			
			String queryCheck = "SELECT * from teachers WHERE teacherId= '" + id + "'";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(queryCheck);
			int flag;
			
			//-----------------------------------------
			
			
			//----------------------
			
			char inner_flag = 'q';
			
			if(rs.absolute(1)) {
				System.out.println("Exists");
				flag = 1;
				
				String selsql = "select * from teachers where teacherId = '" + id + "'";
				PreparedStatement pst = con.prepareStatement(selsql);
				ResultSet rsr = pst.executeQuery(selsql);
				ResultSetMetaData rsmd = rsr.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				while(rsr.next())
				{
					sfname = rsr.getString(1);
					slname = rsr.getString(2);
					sid = rsr.getString(3);
					spass = rsr.getString(4);
					sdiv = rsr.getString(5);
					
				}
				String delsql1 = "delete from curr_teacher";
				st.executeUpdate(delsql1);
				
				/*String insql1 = "insert into curr_teacher values('" + sfname + "','" + slname + "','" + sid + "','" + spass + "','" + sdiv + "')";
				st.executeUpdate(insql1);*/
				
				
				String insql = "insert into curr_teacher values('" + sfname + "','" + slname + "','" + sid + "','" + spass + "','" + sdiv + "')";
				st.executeUpdate(insql);
				
				
				
				
				
				SepTeacherFrame ssf = new SepTeacherFrame();
			}
			else {
				System.out.println("Not exists");
				flag = 0;
				
				TeacherNotFound1 snf = new TeacherNotFound1();
			}
		}
		catch(Exception e)
		{
			System.out.println("In catch");
		}
		
	}
}

class TeacherNotFound1 extends JFrame
{
	JLabel l1;
	
	public TeacherNotFound1()
	{
		setLayout(new FlowLayout());
		
		l1=new JLabel("Teacher not found");
		add(l1);
		
		setVisible(true);
		setSize(250,250);
		setTitle("Teacher Not found");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

class SepTeacherFrame extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b1,b2,b3,b4;
	String sid,spass,sfname,slname,sdiv;
	
	public SepTeacherFrame()
	{
		setLayout(new FlowLayout());

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdldatabase","ostechnix","Password123#@!");
			
			String selsql = "select * from curr_teacher";
			PreparedStatement pst = con.prepareStatement(selsql);
			ResultSet rsr = pst.executeQuery(selsql);
			ResultSetMetaData rsmd = rsr.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rsr.next())
			{
				sfname = rsr.getString(1);
				slname = rsr.getString(2);
				sid = rsr.getString(3);
				spass = rsr.getString(4);
				sdiv = rsr.getString(5);
			}
			System.out.println(sfname + " " + slname + " " + sid + " " + spass + " " + sdiv);
			
		}
		catch(Exception e)
		{
			
		}
		
		l1=new JLabel("Welcome " + sfname + " " + slname);
		b1 = new JButton("View Students");
		b2 = new JButton("View your timetable");
		b3 = new JButton("Chat");
		b4 = new JButton("Send Assignment");
		
		add(l1);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		setVisible(true);
		setSize(200,700);
		setTitle("Student page");
		
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		/*if(ae.getSource()==b1)
		{
			Subjects sbj = new Subjects();
		}
		
		if(ae.getSource()==b2)
		{
			MarksDis mds = new MarksDis();
		}*/
	}
	
}














