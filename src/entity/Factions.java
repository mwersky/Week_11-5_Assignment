package entity;

public class Factions {

	
	private int id;
	private String factions;
	
	public Factions( int id, String factions) {
		this.setId( id );
		this.setFactions( factions );
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFactions() {
		return factions;
	}

	public void setFactions(String factions) {
		this.factions = factions;
	}
}
