package com.code.jdbc;
import java.sql.*;  

public class Mysql {

	public static void main(String[] args) throws Exception{
		
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqldb","root","Krishna@26");  
		
			Statement stmt=con.createStatement(); 
			//String s="INSERT INTO TEST VALUES(3,'SHIVA',3)";
			String s="UPDATE TEST SET NAME='SHANKAR' WHERE ID=3";
			stmt.execute(s);  
			 
			con.close();  
			System.out.println("Query executed");
			}  

	}

