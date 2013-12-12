package candy;

//There are N children standing in a line. Each child is assigned a rating value.
//
//You are giving candies to these children subjected to the following requirements:
//
//   Each child must have at least one candy.
//   Children with a higher rating get more candies than their neighbors.
//
//What is the minimum candies you must give? 
public class Solution {
	// reason why this works:
	// when going from left to right, once ratings[right] > ratings[left], candies[right] would be >candies[left]
	// when going from right to left, once ratings[left] > ratings[right], candies[left] would be >candies[right]
	// 	and once candies[left]'s origin value is bigger then the value calculated by the 2nd run
	// 	we maintain the bigger value, in this case candies[left] must be > candies[left-1] 
	//						   candies[left] must be > candies[right]
	public int candy(int[] ratings) {
		int candies[] = new int[ratings.length];
		candies[0] = 1;
		for (int i = 1; i < ratings.length; i++) {
			candies[i] = ratings[i] > ratings[i - 1] ? candies[i - 1] + 1 : 1;
		}
		int ret = candies[ratings.length - 1];
		for (int i = ratings.length - 1; i > 0; i--) {
			candies[i - 1] = (ratings[i - 1] > ratings[i])
					&& (candies[i] + 1 > candies[i - 1]) ? candies[i] + 1
					: candies[i - 1];
			ret += candies[i - 1];
		}
		return ret;
	}

	public int candyNaive(int[] ratings) {
		int[] candies = new int[ratings.length];
		candies[0] = 1;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candies[i] = candies[i - 1] + 1;
			} else {
				int k = i;
				candies[k] = 1;
				while (k > 0 && ratings[k] < ratings[k - 1]
						&& candies[k] == candies[k - 1]) {
					candies[k - 1]++;
					k--;
				}
			}
		}
		int min = 0;
		for (int i : candies) {
			min += i;
		}
		return min;
	}

	public static void main(String[] args) {
		int[] ratings = { 1, 2 };
		System.out.println(new Solution().candy(ratings));
	}
}
