package edu.hm.cs.swe2.pcs.cvsgen.model;

public class Worker {

	private final int maID;
	
	public Worker(int maID, String name, int level) {
		super();
		this.maID = maID;
		this.name = name;
		this.level = level;
	}

	private final String name;
	
	private final int level;

	public int getMaID() {
		return maID;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}
	
}
