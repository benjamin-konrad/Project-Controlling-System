package edu.hm.cs.swe2.pcs.cvsgen;

import java.util.Random;

public class RangeFloatPicker implements Picker<Float> {

	public final float amount;

	public final float min;

	public final Random random;

	public RangeFloatPicker(float min, float max) {
		this.amount = max - min;
		this.min = min;
		this.random = new Random();
	}

	@Override
	public Float giveValue() {
		float number = random.nextFloat() * this.amount;
		return this.min + number;
	}

}
