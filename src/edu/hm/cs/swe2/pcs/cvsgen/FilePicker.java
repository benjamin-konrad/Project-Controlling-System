package edu.hm.cs.swe2.pcs.cvsgen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FilePicker implements Picker<String> {

	private final List<String> values;
	
	private final Random random;

	public FilePicker(String path) {
		values = new ArrayList<>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null)
				values.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		random = new Random();
	}

	@Override
	public String giveValue() {
		int randomNumber = random.nextInt(values.size());
		return values.get(randomNumber);
	}

}
