package edu.hm.cs.swe2.pcs.cvsgen;

import java.util.Random;

public class DatePicker implements Picker<String> {

	private final int yearAmount;
	private final Random random;
	private final int minYear;

	public DatePicker(int minYear, int maxYear) {
		yearAmount = maxYear - minYear;
		this.minYear = minYear;
		this.random = new Random();
	}

	@Override
	public String giveValue() {
		int year = this.random.nextInt(this.yearAmount) + this.minYear;
		int month = this.random.nextInt(12) + 1;
		StringBuilder output = new StringBuilder();
		if (month < 10)
			output.append("0");
		output.append(month).append("-").append(year);
		return output.toString();
	}

}
