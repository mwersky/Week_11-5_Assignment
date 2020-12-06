package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import entity.Factions;

public class FactionsDao {
	
	private Connection connection;
	
	public FactionsDao() {
		connection = DBConnection.getInstance().getConnection(); //note the change in how we connect to the DB with .getInstance		
	}	
	
	//Create Operation WORKING
	public void insertFaction(String factionName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO factions(faction) VALUES (?)" );
		ps.setString(1, factionName );
		ps.executeUpdate();
		System.out.println(factionName + " created. Welcome to the fray.");
		
		}
	
	//Read Operation WORKING
		public List<Factions> getAllFactions() throws SQLException { //To get all our Factions
			List<Factions> result = new ArrayList<Factions>(); //recommendation to use out for methods that have return statements. helps organize
			
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery( "SELECT * FROM Factions"); //Need to study the ResultSet function more. Would like to better understand
																	  // what it is I'm doing here.
			
			while (rs.next() ) {
				result.add( new Factions( rs.getInt( "id" ), rs.getString( "faction" )));
			}
			
			return result;
		}
	
	//Update Operation TESTING SHOULD WORK HOPEFULLY
		public void updateFaction(String factionNameNew, String factionNameOld) throws SQLException {
			String sqlUpdate = "UPDATE Factions SET faction=? where faction=?";
			PreparedStatement statement = connection.prepareStatement(sqlUpdate);
			statement.setString(1, factionNameOld);
			statement.setString(2, factionNameNew);
			
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Faction updated to " + factionNameNew + "!");
			}
		}
		
		
	//Delete Operation FINALLY WORKING (HURRAY!)
	public void deleteFaction(String factionName) throws SQLException {
		String sql = "DELETE FROM Factions where faction=?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, factionName); //I hate this part. It's annoying how you have to make sure to assign your variables into the query.
		
		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println(factionName + " was deleted Successfully!");
		}
		}
	
}
