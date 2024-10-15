package ro.emanuel.oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main2 {
	
	public static void main (String[] args) throws SQLException {
		
		Properties connectionProps = new Properties();
		connectionProps.put("user","root");
		connectionProps.put("password","");
	
		//open connection
		Connection conn= DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/oop2024", 
				connectionProps);
	
		//open statement
		Statement stmt=conn.createStatement();
		
		
//		//insert
	String name = "Sosete";
	int size = 3;
	String color="Albe";
	int price= 25;
		
		String sqlInsert= "insert into fashion_item(name, size, color, price)values (? ,?, ?, ? )";
		PreparedStatement ps=conn.prepareStatement(sqlInsert);
		
		ps.setString(1, name);
		ps.setInt(2, size);
		ps.setString(3, color);
		ps.setInt(4, price);
		
		int rowsAff= ps.executeUpdate();
		System.out.println(rowsAff);
		
		
//		//UPDATE
		String update="UPDATE fashion_item SET price = ? WHERE name = ? ";
		PreparedStatement psu= conn.prepareStatement(update);
		psu.setInt(1, price);
		psu.setString(2, "Sosete");
		psu.executeUpdate();
//		
//		//DELETE
		String deleteValue="DELETE FROM fashion_item WHERE price =?";
		PreparedStatement ps3 = conn.prepareStatement(deleteValue);
		ps3.setInt(1, 25);
		int result=ps3.executeUpdate();
		System.out.println(result);
//		
//		
		//get result
		ResultSet rs =stmt.executeQuery("select * from fashion_item");
				
		//map results
		while (rs.next()) {
			String fashion_itemname =rs. getString("name");
			int fashion_itemsize =rs. getInt("size");
			String fashion_itemcolor =rs. getString("color");
			int fashion_itemprice= rs. getInt("price");
					
			System.out.println( "|" + fashion_itemname + "|" + fashion_itemsize + "|" + fashion_itemcolor + "|" + fashion_itemprice);
					
			}
		conn.close();		
	}
}

