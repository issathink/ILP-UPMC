package com.paracamplus.ilp1.ilp1tme3;

import java.math.BigInteger;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class MakeVectorPrimitive extends BinaryPrimitive {

	public MakeVectorPrimitive(String name) {
		super(name);
	}

	@Override
	public Object apply(Object arg1, Object arg2) throws EvaluationException {
		// System.out.println(arg1.getClass() + "; " + arg2.getClass());
		if (arg1 instanceof BigInteger) {
			int size = ((BigInteger) arg1).intValue();
			if (size > 0)
				return new VectorObejct(size, arg2);
			throw new EvaluationException("Length of the table should be greater than 0.");
		}
		throw new EvaluationException("First argument should be an integer.");
	}
}
