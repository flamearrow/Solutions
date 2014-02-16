package com.ml.gb.operators;

public class Number implements Operator {
	int n;

	public Number(int n) {
		this.n = n;
	}

	@Override
	public int operate() {
		return n;
	}

}
