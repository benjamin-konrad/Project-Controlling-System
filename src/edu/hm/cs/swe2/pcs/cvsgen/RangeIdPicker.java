package edu.hm.cs.swe2.pcs.cvsgen;

import java.util.HashSet;
import java.util.Set;

public class RangeIdPicker extends RangeIntegerPicker {

	private Set<Integer> usedValues;
	
	public RangeIdPicker(int min, int max) {
		super(min, max);
		usedValues = new HashSet<>();
	}

	@Override
	public Integer giveValue() {
		int value = super.giveValue();
		int index = value - this.min;
		if(usedValues.contains(index))
			value = giveValue();
		else {
			usedValues.add(index);
		}
		return value;
	}
	
	public void addUsedValue(int id){
		usedValues.add(id);
	}
}
