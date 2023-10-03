package com.enforce.ConnectionDB;
import java.sql.*;
public class ConnectDB 
{
	 static Connection con = null;
	 public static Connection connect()
	 {
		 if(con==null)
		 {
			 try
			 {
				 Class.forName("com.mysql.jdbc.Driver");
				 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS","root","");
				 System.out.println("Connection Established");
			 }
			 catch(Exception e)
			 {
				 System.err.println("Error");
				 e.printStackTrace();
			 }
		 }
		 return con;
	 }
	
}
