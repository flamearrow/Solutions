package epi.xor;

public class Solution {
	public static void main(String[] args) {
		int a = 0;
		int b = 2;
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a);
		System.out.println(b);
	}
}
