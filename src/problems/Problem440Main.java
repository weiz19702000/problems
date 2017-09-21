package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem440Main {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("param: [n] [k]");
			return;
		}
		
		int totalNumber = Integer.parseInt(args[0]);
		int k = Integer.parseInt(args[1]);
		List<LexicographicalInteger> numbers = new ArrayList<LexicographicalInteger>();
		
		for (int i = 0; i < totalNumber; i++) {
			numbers.add(new LexicographicalInteger(i + 1));
		}		
		Collections.sort(numbers);
		
		System.out.println(numbers.get(k - 1).number);
	}
	
	static class LexicographicalInteger implements Comparable<LexicographicalInteger> {
		public int number;
		
		public LexicographicalInteger(int number) {
			this.number = number;
		}
		
		private String getNumberString() {
			return Integer.toString(number);
		}

		@Override
		public int compareTo(LexicographicalInteger o) {
			String number1 = this.getNumberString();
			String number2 = o.getNumberString();
			
			int length = Math.min(number1.length(), number2.length());
			for (int i = 0; i < length; i++) {
				if (number1.charAt(i) == number2.charAt(i)) {
					continue;
				}
				
				return number1.charAt(i) < number2.charAt(i)? -1 : 1;
			}
			
			return number1.length() < number2.length()? -1 : 1;
		}		
	}
}
