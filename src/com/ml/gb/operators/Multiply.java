package com.ml.gb.operators;

public class Multiply implements Operator {
	Operator x, y;

	public Multiply(Operator x, Operator y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int operate() {
		return x.operate() * y.operate();
	}

}
