package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import utill.dbconnect;

public class User {
	
	 // create object of dbconnect
	  dbconnect dbconn = new dbconnect();
	  
	// Implementing insert User
	  public String createUser(String acntNumber, String fullName, String email, String NIC, String address, String mobileNumber, String landpNumber, String userName, String password, String confirmPassword) {  

		  String output = "";
		    try
		    {
		    	
		    // create connection object & call the connection method
		    Connection con = dbconn.connect();
		    
		    if (con == null) {
		         return "Error while connecting to the database for inserting user.";
		    }
		    
		    // create a prepared statement
		    String query = " INSERT INTO user (`userID`,`acntNumber`,`fullName`,`email`,`NIC`,`address`,`mobileNumber`,`landpNumber`,`userName`,`password`,`confirmPassword`)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    
		    // binding values
		      preparedStmt.setInt(1, 0);
		      preparedStmt.setInt(2, Integer.parseInt(acntNumber));
		      preparedStmt.setString(3, fullName);
		      preparedStmt.setString(4, email);
		      preparedStmt.setString(5, NIC);
		      preparedStmt.setString(6, address);
		      preparedStmt.setInt(7, Integer.parseInt(mobileNumber));
		      preparedStmt.setInt(8, Integer.parseInt(landpNumber));
		      preparedStmt.setString(9, userName);
		      preparedStmt.setString(10, password);
		      preparedStmt.setString(11, confirmPassword);
		      
		      // execute the statement
		      preparedStmt.execute();
		      
		      // close the connection
		      con.close();
		      
		      output = "User inserted successfully.";

		      } catch (Exception e) {

		      output = "Error while inserting the User.";
		      System.err.println(e.getMessage());

		      }

		      return output;
		      
	}
	  
	  
	// Retrieve Operation for Display all the Users
	 public String DisplayUsers() {
		 
		 String output = "";
		    
		 try 
		 {
			 
	      // create connection object & call the connection method
		  Connection con = dbconn.connect(); 
		  
		  if (con == null) {
		         return "Error while connecting to the database for Display Users.";
		  }
		  
		  // prepare the user table to be displayed
	      output = "<center><table border='1' width='100%'><tr><th colspan='8'>User Management</th></tr>"
	    		  +"<tr><th>User ID</th><th>Account Number</th><th>Full Name</th><th>Email</th><th>NIC</th>"
	    		  +"<th>Address</th><th>Mobile Number</th><th>Land Phone Number</th><th>Username</th></tr>";
	      
	      String query = "SELECT * FROM user";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);
	      
	      // iterate through the rows in the result set
	      while (rs.next()) 
	      {
	      String userID = Integer.toString(rs.getInt("userID"));
	      String acntNumber = Integer.toString(rs.getInt("acntNumber"));
	      String fullName = rs.getString("fullName");
	      String email = rs.getString("email");
	      String NIC = rs.getString("NIC");
	      String address = rs.getString("address");
	      String mobileNumber = Integer.toString(rs.getInt("mobileNumber"));
	      String landpNumber = Integer.toString(rs.getInt("landpNumber"));
	      String userName = rs.getString("userName");
	      
	      // display users in a tabular manner
	        output += "<tr> <td> " + userID + "</td><td>" + acntNumber + "</td><td> " + fullName + "</td><td>" 
	        + email + "</td><td>" + NIC + "</td><td>" + address + "</td><td>"
	            + mobileNumber + "</td><td>" + landpNumber + "</td><td> " + userName + "</td> </tr>";
	        
	       /* // buttons
	         output += "<tr> <td colspan='2'><center><br/><input name='update' type='button' value='Update User'> <br/><br/>"
	         + "<form method='post' action='#'>"
	         + "<input name='remove' type='submit' value='Remove User'>"+ "<input name='itemID' type='hidden' value='" + userName 
	         + "'>" + "</form></center></td> </tr>"; */
	         
	       }
	      
	       con.close();

	       output += "</table> <br/> ***</center>";

	       } catch (Exception e) {

	       output = "Error while Displaying Users.";
	       System.err.println(e.getMessage());

	       }
	       return output;

	   }
	 
	     
	   // Retrieve Operation for display single user
	    public String DisplayUser(String userName) {

	      String output = "";

	      try {
	    	  
	      
	     // create connection object & call the connection method
	     Connection con = dbconn.connect();

	     if (con == null) {
	         return "Error while connecting to the database for display user.";
	          
	     }
	     
	     // prepare the user to display
	     output = "<center><table border='1' width='100%'><tr><th colspan='8'>User Details</th></tr>"
	    		  +"<tr><th>User ID</th><th>Account Number</th><th>Full Name</th><th>Email</th><th>NIC</th>"
	    		  +"<th>Address</th><th>Mobile Number</th><th>Land Phone Number</th><th>Username</th></tr>";
	     
	     String query = "SELECT userID,acntNumber,fullName,email,NIC,address,mobileNumber,landpNumber,userName FROM user WHERE userName=?";

	        // create a prepared statement
	        PreparedStatement preparedStmt = con.prepareStatement(query);

	        // binding values
	        preparedStmt.setString(1, userName);

	        // execute the statement
	        ResultSet rs = preparedStmt.executeQuery();

	        // iterate through the rows in the result set
	        while (rs.next()) 
	        {
	        	
	        	String userID = Integer.toString(rs.getInt("userID"));
	            String acntNumber = Integer.toString(rs.getInt("acntNumber"));
	            String fullName = rs.getString("fullName");
	            String email = rs.getString("email");
	            String NIC = rs.getString("NIC");
	            String address = rs.getString("address");
	            String mobileNumber = Integer.toString(rs.getInt("mobileNumber"));
	            String landpNumber = Integer.toString(rs.getInt("landpNumber"));
	            String usrName = rs.getString("userName");
	            
	            
	         // display user in a tabular manner
		        output += "<tr> <td> " + userID + "</td><td>" + acntNumber + "</td><td> " + fullName + "</td><td>" 
		        + email + "</td><td>" + NIC + "</td><td>" + address + "</td><td>"
		            + mobileNumber + "</td><td>" + landpNumber + "</td><td> " + usrName + "</td> </tr>";
		        
		     // buttons
		         output += "<tr> <td colspan='2'><center><br/><input name='update' type='button' value='Update User'> <br/><br/>"
		         + "<form method='post' action='#'>"
		         + "<input name='remove' type='submit' value='Remove User'>"+ "<input name='itemID' type='hidden' value='" + userName 
		         + "'>" + "</form></center></td> </tr>";
	        }
	        
	        
	        con.close();

	        // close the displaying
	        output += "</table> <br/> ***</center>";

	        } catch (Exception e) {

	        output = "Error while Displaying User.";
	        System.err.println(e.getMessage());

	        }
	        return output;

	    }
	    
	    
	    // implementing Update user method
	    public String updateUser(String acntNumber, String fullName, String email, String NIC, String address, String mobileNumber, String landpNumber, String userName) {
		 
	    	
	    	 String output = "";

	         try {
	        	 
	        	// create connection object & call the connection method
	             Connection con = dbconn.connect();

	             if (con == null) {
	               return "Error while connecting to the database for updating user.";
	             
	         }
	             
	          // create a prepared statement
	             String query = "UPDATE user SET acntNumber=?, fullName=?, email=?, NIC=?, address=?, mobileNumber=?, landpNumber=? WHERE userName=? ";
	             PreparedStatement preparedStmt = con.prepareStatement(query);
	             
	          // binding values
	             preparedStmt.setInt(1, Integer.parseInt(acntNumber));
	             preparedStmt.setString(2, fullName);
	             preparedStmt.setString(3, email);
	             preparedStmt.setString(4, NIC);
	             preparedStmt.setString(5, address);
	             preparedStmt.setInt(6, Integer.parseInt(mobileNumber));
	             preparedStmt.setInt(7, Integer.parseInt(landpNumber));
	             preparedStmt.setString(8, userName);
	             
	             
	          // execute the statement
	             preparedStmt.execute();

	             // close the connection
	             con.close();

	             output = "User Updated successfully";
	           }

	           catch (Exception e) {
	             output = "Error while Updating the user.";
	             System.err.println(e.getMessage());

	           }

	           return output;

	         }
	    
	    
	    // implementing Delete user method
	    public String deleteUser(String userName) {

	      String output = "";
	      try {
	        // create connection object & call the connection method
	        Connection con = dbconn.connect();

	        if (con == null) {
	          return "Error while connecting to the database for deleting user.";
	        }

	        // create a prepared statement
	        String query = "DELETE FROM user WHERE userName=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);

	        // binding values
	        preparedStmt.setString(1, userName);;

	        // execute the statement
	        preparedStmt.execute();

	        // close the connection
	        con.close();

	        output = "User deleted successfully";

	      } catch (Exception e) {
	        output = "Error while deleting the User.";
	        System.err.println(e.getMessage());

	      }

	      return output;

	    }		      
		      
}
