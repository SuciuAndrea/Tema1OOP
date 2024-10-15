package ro.emanuel.oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main1 {
	
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
	String name = "Lantisor";
	String type = "Simplu";
	String material="Argint";
	int price= 205;
		
		String sqlInsert= "insert into accessories(name, type, material, price)values (? ,?, ?,? )";
		PreparedStatement ps=conn.prepareStatement(sqlInsert);
		
		ps.setString(1, name);
		ps.setString(2, type);
		ps.setString(3, material);
		ps.setInt(4, price);
		
		int rowsAff= ps.executeUpdate();
		System.out.println(rowsAff);
		
		
//		//UPDATE
		String update="UPDATE accessories SET price = ? WHERE name = ? ";
		PreparedStatement psu= conn.prepareStatement(update);
		psu.setInt(1, price);
		psu.setString(2, "Lantisor");
		psu.executeUpdate();
//		
//		//DELETE
		String deleteValue="DELETE FROM accessories WHERE price =?";
		PreparedStatement ps3 = conn.prepareStatement(deleteValue);
		ps3.setInt(1, 205);
		int result=ps3.executeUpdate();
		System.out.println(result);
		
		
		//get result
		ResultSet rs =stmt.executeQuery("select * from accessories");
				
		//map results
		while (rs.next()) {
			String accessoriesname =rs. getString("name");
			String accessoriestype =rs. getString("type");
			String accessoriesmaterial =rs. getString("material");
			int accessoriesprice= rs. getInt("price");
					
			System.out.println( "|" + accessoriesname + "|" + accessoriestype + "|" + accessoriesmaterial + "|" + accessoriesprice);
					
			}
		conn.close();		
	}
}
