package problems;

import java.util.ArrayList;
import java.util.List;

public class Problem403Main {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("param: [list of stone units]");
			return;
		}
		
		String[] values = args[0].split(",");
		int[] stones = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			stones[i] = Integer.parseInt(values[i].trim());
		}
		
		if (!validInitialPosition(stones)) {
			System.out.println("cannot complete the jump");
			return;
		}
		
		List<Integer> positions = new ArrayList<Integer>();
		positions.add(stones[0]);
		positions.add(stones[1]);
		
		attemptJumpRecursive(stones, 1, positions);
		
		if (!completeJump(stones, positions)) {
			System.out.println("cannot complete the jump");
			return;
		}
		
		System.out.println("initial position: " + positions.get(0));
		for (int i = 1; i < positions.size(); i++) {
			System.out.println("jump " + (stones[positions.get(i)] - stones[positions.get(i - 1)]) 
								+ " steps to " + stones[positions.get(i)]);
		}
	}
	
	private static void attemptJumpRecursive(int[] stones, int lastJump, List<Integer> positions) {
		if ((stones.length -1) == positions.get(positions.size() - 1)) {
			return;
		}
		
		int jump = lastJump;
		if (findJump(stones, jump, positions)) {
			attemptJumpRecursive(stones, jump, positions);
			if (completeJump(stones, positions)) {
				return;
			} else {
				positions.remove(positions.size() - 1);
			}
		}
		
		if (lastJump > 1) {
			jump = lastJump - 1;
			if (findJump(stones, jump, positions)) {
				attemptJumpRecursive(stones, jump, positions);
				if (completeJump(stones, positions)) {
					return;
				} else {
					positions.remove(positions.size() - 1);
				}
			}
		}
		
		jump = lastJump + 1;
		if (findJump(stones, jump, positions)) {
			attemptJumpRecursive(stones, jump, positions);
			if (completeJump(stones, positions)) {
				return;
			} else {
				positions.remove(positions.size() - 1);
			}
		}
	}
	
	private static boolean findJump(int[] stones, int jump, List<Integer> positions) {
		int lastPosition = positions.get(positions.size() - 1);
		int nextUnit = stones[lastPosition] + jump;
		for (int i = lastPosition + 1; i < stones.length; i++) {
			if (stones[i] == nextUnit) {
				positions.add(i);
				return true;
			}
			if (stones[i] > nextUnit) {
				return false;
			}
		}
		
		return false;
	}
	
	private static boolean validInitialPosition(int[] stones) {
		return (stones[1] - stones[0]) == 1; 
	}
	
	private static boolean completeJump(int[] stones, List<Integer> positions) {
		return positions.size() > 0 && (positions.get(positions.size() - 1) == (stones.length - 1));
	}
}
