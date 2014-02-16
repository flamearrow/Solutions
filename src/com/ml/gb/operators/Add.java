package com.ml.gb.operators;

public class Add implements Operator {
	Operator x, y;

	public Add(Operator x, Operator y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int operate() {
		return x.operate() + y.operate();
	}

}
