package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class fund {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund", "root", "19189390");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return con;
	}

	public String readfund() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>name</th><th>amount</th>" +
	 "<th>pnumber</th>" + 
	 "<th>nic</th>" +
	 "<th>city</th>" +
	 "<th>Disc</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from fund"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String fundid = Integer.toString(rs.getInt("fundid")); 
	 String name = rs.getString("name"); 
	 String amount = rs.getString("amount"); 
	 String pnumber = rs.getString("pnumber");
	 String nic = rs.getString("nic"); 
	 String city = rs.getString("city"); 
	 String desc = rs.getString("desc"); 
				 // Add into the html table
				 output += "<tr><td><input id='hidfundidUpdate' "
				 		+ "name='hidfundidUpdate' "
				 		+ "type='hidden' value='" + fundid
				 		+ "'>" + name + "</td>"; 
				 output += "<td>" + amount + "</td>"; 
				 output += "<td>" + pnumber + "</td>"; 
				 output += "<td>" + nic + "</td>";
				 output += "<td>" + city + "</td>";
				 output += "<td>" + desc + "</td>"; 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-fundId='" +  fundid + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-fundId='" + fundid + "'></td></tr>";
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}

	public String insertfund(String name, String amount, String pnumber, String nic, String city,String desc) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into fund (`fundid`,`name`,`amount`,`pnumber`,`nic`,`city`,`desc`)"
	 + " values (?, ?, ?, ?, ?,?,?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, amount); 
	 preparedStmt.setString(4, pnumber); 
	 preparedStmt.setString(5,nic); 
	 preparedStmt.setString(6, city); 
	 preparedStmt.setString(7, desc); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			String newItems = readfund(); 
			output = "{\"status\":\"success\", \"data\": \""
					+ "" +newItems + "\"}"; 
			} 
			catch (Exception e) 
			{ 
				System.err.println("ERORR : "+e.getMessage());
				output = "{\"status\":\"error\", \"data\":"
					 + "\"Error while inserting the fund.\"}"; 
				 
			} 
			return output;
		 }

	public String updatefund(String fundid,String name, String amount, String pnumber, String nic, String city,String desc)
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE fund SET name=?,amount=?,pnumber=?,nic=?,city=?, desc=? WHERE fundid=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, name); 
	 preparedStmt.setString(2, amount); 
	 preparedStmt.setString(3, pnumber); 
	 preparedStmt.setString(4, nic); 
	 preparedStmt.setString(5, city);
	 preparedStmt.setString(6, desc);
	 preparedStmt.setInt(7, Integer.parseInt(fundid)); 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newItems = readfund(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": "
			 		+ "\"Error while updating the fund.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output;
	}

	public String deletefund(String fundid) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from fund where fundid=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(fundid)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newItems = readfund(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": "
			 		+ "\"Error while deleting the fund.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output;
	}
}
