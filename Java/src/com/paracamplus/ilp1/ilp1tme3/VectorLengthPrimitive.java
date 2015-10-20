package com.paracamplus.ilp1.ilp1tme3;

import com.paracamplus.ilp1.interpreter.Interpreter;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.UnaryPrimitive;

public class VectorLengthPrimitive extends UnaryPrimitive {

	public VectorLengthPrimitive(String name) {
		super(name);
	}

	@Override
	public Object apply(Interpreter interpreter, Object[] argument)
			throws EvaluationException {
		if (argument.length == 1)
			return apply(argument[0]);
		throw new EvaluationException("Cannot evaluate length with "
				+ argument.length + " argument(s).");
	}

	@Override
	public Object apply(Object arg1) throws EvaluationException {
		if (arg1 instanceof VectorObejct)
			return ((VectorObejct) arg1).getTaille();

		throw new EvaluationException(
				"On ne peut pas appeler length sur n'importe quoi.");
	}

}
