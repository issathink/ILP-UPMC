package com.paracamplus.ilp1.ilp1tme3;

import java.math.BigDecimal;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.UnaryPrimitive;

public class Sinus extends UnaryPrimitive {

	public Sinus(String name) {
		super(name);
	}

	@Override
	public Object apply(Object arg1) throws EvaluationException {
		BigDecimal big = (BigDecimal) arg1;
		return (Object) BigDecimal.valueOf(Math.sin(big.doubleValue()));
	}

}
