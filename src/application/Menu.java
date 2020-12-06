package application;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.FactionsDao;
import entity.Factions;

public class Menu {
	
	private Scanner scanner = new Scanner(System.in);
	private String[] menuOpts = { //I like how you can just keep adding more options as you build the menu. 
			"Read All Factions", //Being able to add options as I work on them in groups is a little more  
			"Add a Faction",     //how I handle tasks so It's a little easier than just full top down approach.
			"Update a Faction",
			"Delete a Faction",
			"-1 to end operations" 
			};
	
	private FactionsDao factionsDao = new FactionsDao();

	//Method that lists the menu
	private void printMenu() {
		System.out.println("------------");
		System.out.println("Welcome Warrior. What would you like to do today?");
		for ( int i = 0; i < menuOpts.length; i++) {
			System.out.println((i + 1) + ") " + menuOpts[i]);
		}
	}

	//Primary Start Method and Menu Logic
		public void start() {
			String selection = "";
			
			do {
				printMenu();
				selection = scanner.nextLine();
				
				//process choice
				try {
					if ( selection.equals( "1" ) ) {
						displayFactions();
					} else if (selection.equals( "2" ) ) {
						addFactions();
					} else if (selection.equals("3")) {
						updateFactions();
					} else if (selection.equals("4")) {
						deleteFactions();
					}
				} catch ( SQLException e ) {
					e.printStackTrace();
					
				}
				
			if (selection != "-1") {
				System.out.println("Press enter to Continue...");
				scanner.nextLine();
			}
				
			} while (!selection.equals("-1"));	
		}	
		
	//Method that will list all the factions
	private void displayFactions() throws SQLException {
		List<Factions> theFactions = factionsDao.getAllFactions();
		for ( Factions f : theFactions) {
			System.out.println(f.getId() + " - " + f.getFactions());
		}
	}
	
	//Method for adding to the Faction
	private void addFactions() throws SQLException {
		System.out.println("Enter the name of the Faction you wish to create: ");
		String factionName = scanner.nextLine();
		if (factionName.length() > 0) {
			factionsDao.insertFaction( factionName );
		}
	}
	
	//Method for updating a faction
	private void updateFactions() throws SQLException {
		System.out.println("Enter the name of the Faction name you wish to update: ");
		String factionNameOriginal = scanner.nextLine();
		System.out.println("What would you like to change it to: ");
		String factionNameUpdate = scanner.nextLine();
		if (factionNameOriginal.length() > 0 & factionNameUpdate.length() > 0) {
			factionsDao.updateFaction(factionNameOriginal, factionNameUpdate);
		}
	}
	
	//Method for removing a faction
	private void deleteFactions() throws SQLException {
		System.out.println("Enter the name of the Faction you wish to Remove: ");
		String factionName = scanner.nextLine();
		if (factionName.length() > 0) {
			factionsDao.deleteFaction( factionName );
		}
	}	
}
