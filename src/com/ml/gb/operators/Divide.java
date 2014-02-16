package com.ml.gb.operators;

public class Divide implements Operator {
	Operator x, y;

	public Divide(Operator x, Operator y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int operate() {
		return x.operate() / y.operate();
	}

}
