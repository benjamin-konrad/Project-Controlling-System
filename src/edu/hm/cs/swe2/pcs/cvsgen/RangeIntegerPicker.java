package edu.hm.cs.swe2.pcs.cvsgen;

import java.util.Random;

public class RangeIntegerPicker implements Picker<Integer> {

	public final int amount;
	
	public final int min;
	
	public final Random random;
	
	public RangeIntegerPicker(int min, int max){
		this.amount = max - min;
		this.min = min;
		this.random = new Random();
	}
	
	@Override
	public Integer giveValue() {
		int number = random.nextInt(amount);
		return this.min + number;
	}

}
