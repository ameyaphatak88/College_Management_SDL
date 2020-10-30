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

public class PrincipalSection
{
	PrincipalFrame p = new PrincipalFrame();
}

class PrincipalFrame extends JFrame implements ActionListener
{
	JButton b1;
	JLabel l1,l2;
	JTextField t1,t2;
	
	public PrincipalFrame()
	{
		setLayout(new FlowLayout());
		
		l1=new JLabel("Username:");
		t1=new JTextField(); 
		t1.setColumns(20);
		
		l2=new JLabel("Password:");
		t2=new JTextField(); 
		t2.setColumns(20);
		
		b1 = new JButton("Continue");
		
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
		setTitle("Principal Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	    
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String id = t1.getText().toString();
		String pass = t2.getText().toString();
		
		System.out.println(id + " " + pass);
		
		if(id.contentEquals("admin") && pass.contentEquals("admin"))
		{			
			CorrCred wc = new CorrCred();
		}
		else
		{
			WrongCred wc = new WrongCred();
		}
	}
	
}

class WrongCred extends JFrame
{
	JLabel l1;
	
	public WrongCred()
	{
		setLayout(new FlowLayout());
		
		l1 = new JLabel("Wrong credentials!!");
		
		add(l1);
		
		setVisible(true);
		setSize(250,100);
		setTitle("Wrong cred!");	
	}
}

class CorrCred extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b1,b2,b3,b4;
	
	public CorrCred()
	{
		setLayout(new FlowLayout());
		
		l1 = new JLabel("Corr credentials!!");
		
		b1 = new JButton("View all students");
		b2 = new JButton("View all teachers");
		b3 = new JButton("Assign timetable");
		b4 = new JButton("Exit");
		
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
		setSize(250,700);
		setTitle("Corr cred!");	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			ShowTableFrame2 t = new ShowTableFrame2();
		}
		if(ae.getSource()==b2)
		{
			ShowTableFrame3 t = new ShowTableFrame3();
		}
		if(ae.getSource()==b3)
		{
			
		}
		if(ae.getSource()==b4)
		{
			dispose();
		}
	}
}

class ShowTableFrame2 extends JFrame
{ 
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	
	public ShowTableFrame2()
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

class ShowTableFrame3 extends JFrame
{ 
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	
	public ShowTableFrame3()
	{
		String[] columnNames = {"First Name", "Last Name", "ID", "Password", "CC"};
		JFrame frame1 = new JFrame("Database Search Result");
        //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        String sdiv = "";
        String spass = "";
        
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdldatabase","ostechnix","Password123#@!");
			
			 pst = con.prepareStatement("select * from teachers");
			 rs = pst.executeQuery();
	         
			 int i = 0;

	         while (rs.next()) {
	                fname = rs.getString("firstName");
	                lname = rs.getString("lastName");
	                sid = rs.getString("teacherId");
	                sdiv = rs.getString("ccof");
	                spass = rs.getString("teacherPass");
	                
	                model.addRow(new Object[]{fname, lname, sid, spass, sdiv});
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








