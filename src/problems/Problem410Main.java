package problems;

import java.util.ArrayList;
import java.util.List;

public class Problem410Main {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("param: [values] [number of splits]");
			return;
		}
		
		String[] values = args[0].split(",");
		int[] numbers = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			numbers[i] = Integer.parseInt(values[i].trim());
		}
		
		int totalSplits = Integer.parseInt(args[1]);
		List<List<Integer>> splits = getSplitArrays(numbers, totalSplits);
		SplitResult result = getBestSplit(numbers, splits);
		
		int index = 0;
		for (int i = 0; i < result.split.size(); i++) {
			String value = getSplitString(numbers, index, result.split.get(i));
			index += result.split.get(i);
			System.out.print(value + " ");
		}
		System.out.println();
		System.out.println("max sum: " + result.maxSum);
	}
	
	private static String getSplitString(int[] numbers, int index, int numberCount) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(numbers[index]);
		for (int i = 1; i < numberCount; i++) {
			sb.append(",");
			sb.append(numbers[index + i]);
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	private static List<List<Integer>> getSplitArrays(int[] numbers, int splits) {
		int totalDistributable = numbers.length - splits;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> curSplit = new ArrayList<Integer>();
		createSplits(totalDistributable, splits, curSplit, result);
		
		return result;
	}
	
	private static void createSplits(int totalDistributable, int splits, List<Integer> curSplit, List<List<Integer>> result) {
		if (splits == 0) {
			List<Integer> split = new ArrayList<Integer>(curSplit);
			result.add(split);
			
			return;
		}
		
		if (totalDistributable == 0) {
			curSplit.add(1);
			createSplits(0, splits - 1, curSplit, result);
			curSplit.remove(curSplit.size() - 1);
		} else if (splits == 1) {
			curSplit.add(totalDistributable + 1);
			createSplits(0, splits - 1, curSplit, result);
			curSplit.remove(curSplit.size() - 1);
		} else {
			for (int i = 0; i <= totalDistributable; i++) {
				curSplit.add(i + 1);
				createSplits(totalDistributable - i, splits - 1, curSplit, result);
				curSplit.remove(curSplit.size() - 1);
			}
		}
	}
	
	private static SplitResult getBestSplit(int[] numbers, List<List<Integer>> splits) {
		SplitResult result = new SplitResult();
		for (List<Integer> split : splits) {
			int sum = getMaxSum(numbers, split);
			if (sum < result.maxSum) {
				result.maxSum = sum;
				result.split = split;
			}
		}
		
		return result;
	}
	
	private static int getMaxSum(int[] numbers, List<Integer> split) {
		int index = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < split.size(); i++) {
			int sum = getSum(numbers, index, split.get(i)); 
			index += split.get(i);
			if (sum > maxSum) {
				maxSum = sum; 
			}
		}
		
		return maxSum;
	}
	
	private static int getSum(int[] numbers, int index, int length) {
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += numbers[index + i];
		}
		
		return sum;
	}
	
	static class SplitResult {
		public List<Integer> split = null;
		public Integer maxSum = Integer.MAX_VALUE;
	}
}
