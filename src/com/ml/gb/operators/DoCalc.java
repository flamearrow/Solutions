package com.ml.gb.operators;

// AKA interpreter pattern
public class DoCalc {
	// calculate 1 + (2 * 3)
	public static void main(String[] args) {
		int rst = new Expression("1 2 3 * +").operate();
		System.out.println(rst);
	}
}
