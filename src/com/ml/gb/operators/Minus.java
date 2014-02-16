package com.ml.gb.operators;

public class Minus implements Operator {
	Operator x, y;

	public Minus(Operator x, Operator y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int operate() {
		return x.operate() - y.operate();
	}

}
